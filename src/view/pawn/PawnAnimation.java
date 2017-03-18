package view.pawn;

import javafx.application.Platform;
import view.Dimension;
import view.ViewImpl;

/**
 * This class handles the movement of a pawn.
 */
public class PawnAnimation implements Runnable {

    private static final double STEP = 1;

    private final Pawn pawnClass;
    private final int nMoves;
    private int finalPos = 1;
    private boolean jumpBool;
    private boolean gameOver;

    /**
     * Constructor of this class.
     * 
     * @param moves
     *            The number of box the pawn must move
     * @param pawn
     *            The pawn to be moved.
     */
    public PawnAnimation(final Pawn pawn, final int moves) {
        this.nMoves = moves;
        this.pawnClass = pawn;
    }

    /**
     * Constructor of this class.
     * 
     * @param moves
     *            The number of box the pawn must move
     * @param pawn
     *            The pawn to be moved.
     * @param finalPosition
     *            The position after the jump
     */
    public PawnAnimation(final Pawn pawn, final int moves, final int finalPosition) {
        this(pawn, moves);
        this.finalPos = finalPosition;
        this.jumpBool = true;
    }

    private void movePawn() {

        if (nMoves < 0) {
            this.goBack(-nMoves);
        }
        for (int i = 0; i < nMoves; i++) {
            synchronized (this.pawnClass) {
                if ((this.pawnClass.getRow() == (this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1))
                        && (this.pawnClass.getPositionInRow() == (this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1))) {
                    this.goBack(nMoves - i);
                    break;
                }
                if (this.pawnClass.getPositionInRow() == (this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1)) {
                    this.resetPositionInRow();
                    this.pawnClass.setRow(this.pawnClass.getRow() + 1);
                    this.pawnClass.changeDirection();
                    this.moveUp();
                    continue;
                }
                this.pawnClass.setPositionInRow(this.pawnClass.getPositionInRow() + 1);
                if (this.pawnClass.getDirection() == Direction.RIGHT) {
                    this.moveRight();
                } else {
                    this.moveLeft();
                }
                if ((this.pawnClass.getRow() == (this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1))
                        && (this.pawnClass.getPositionInRow() == (this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1))
                        && (i == nMoves - 1)) {
                    Platform.runLater(() -> ViewImpl.getPlayScene().gameOver());
                    this.gameOver = true;
                    break;
                }
           }
        }
        if (!this.gameOver) {
            synchronized (this.pawnClass) {
                if (this.jumpBool) {
                    Platform.runLater(() -> ViewImpl.getObserver().startClipJump());
                    this.jump(finalPos);
                }
            }
            Platform.runLater(() -> ViewImpl.getPlayScene().endTurn());
        }
    }

    private void jump(final int finalPosition) {

        final int nX;
        final int nY = finalPosition / this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
        final int change = nY % 2;
        if (change == 0) {
            this.pawnClass.setPositionInRow(finalPosition % this.pawnClass.getParentScene().getBoard().getBoxesPerRow());
            if (this.pawnClass.getDirection() != Direction.RIGHT) {
                this.pawnClass.changeDirection();
            }
            nX = this.pawnClass.getPositionInRow();
        } else {
            if (this.pawnClass.getDirection() != Direction.LEFT) {
                this.pawnClass.changeDirection();
            }
            nX = this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1 
                    - finalPosition % this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
            this.pawnClass.setPositionInRow(this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1 - nX);
        }
        Platform.runLater(() -> this.pawnClass.getPawn().setX(
            this.pawnClass.getIniPos().getFirst() + (Dimension.BOARD_H
                    / this.pawnClass.getParentScene().getBoard().getBoxesPerRow()) * nX));
        Platform.runLater(() -> this.pawnClass.getPawn().setY(
            this.pawnClass.getIniPos().getSecond() - (Dimension.BOARD_H
                    / this.pawnClass.getParentScene().getBoard().getBoxesPerRow()) * nY));
        this.pawnClass.setRow(nY);
        this.checkCollision();
    }

    private void checkCollision() {
        synchronized (ViewImpl.getPlayScene()) {
            Platform.runLater(() -> ViewImpl.getPlayScene().removeItem());
        }
    }

    private void move(final Runnable r) {
        Platform.runLater(r);
        this.checkCollision();
        try {
            Thread.sleep(4);
        } catch (InterruptedException e) {
        }
    }

    private void moveUp() {
        final double startPos = this.pawnClass.getPawn().getY();
        final double finalPos = startPos - Dimension.BOARD_H / this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
        while (this.pawnClass.getPawn().getY() > finalPos) {
            this.move(() -> this.pawnClass.getPawn().setY(this.pawnClass.getPawn().getY() - STEP));
        }
    }

    private void moveRight() {
        final double startPos = this.pawnClass.getPawn().getX();
        final double finalPos = startPos + Dimension.BOARD_H / this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
        while (this.pawnClass.getPawn().getX() < finalPos) {
            this.move(() -> this.pawnClass.getPawn().setX(this.pawnClass.getPawn().getX() + STEP));
        }
    }

    private void moveLeft() {
        final double startPos = this.pawnClass.getPawn().getX();
        final double finalPos = startPos - Dimension.BOARD_H / this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
        while (this.pawnClass.getPawn().getX() > finalPos) {
            this.move(() -> this.pawnClass.getPawn().setX(this.pawnClass.getPawn().getX() - STEP));
        }
    }

    private void moveDown() {
        final double startPos = this.pawnClass.getPawn().getY();
        final double finalPos = startPos + Dimension.BOARD_H / this.pawnClass.getParentScene().getBoard().getBoxesPerRow();
        while (this.pawnClass.getPawn().getY() < finalPos) {
            this.move(() -> this.pawnClass.getPawn().setY(this.pawnClass.getPawn().getY() + STEP));
        }
    }

    private void goBack(final int nMoves) {
        for (int i = 0; i < nMoves; i++) {
            synchronized (this.pawnClass) {
                if (this.pawnClass.getPositionInRow() == 0) {
                    if (this.pawnClass.getRow() == 0) {
                        break;
                    }
                    this.moveDown();
                    this.pawnClass.changeDirection();
                    this.pawnClass.setPositionInRow(this.pawnClass.getParentScene().getBoard().getBoxesPerRow() - 1);
                    this.pawnClass.setRow(this.pawnClass.getRow() - 1);
                    continue;
                }
                this.pawnClass.setPositionInRow(this.pawnClass.getPositionInRow() - 1);
                if (this.pawnClass.getDirection() == Direction.LEFT) {
                    this.moveRight();
                } else {
                    this.moveLeft();
                }
            }
        }
    }

    private void resetPositionInRow() {
        this.pawnClass.setPositionInRow(0);
    }

    @Override
    public void run() {
        this.movePawn();
    }
}
