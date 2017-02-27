package view.pawn;

import javafx.application.Platform;
import view.Dimension;
import view.ViewImpl;
import view.gameboard.GameBoardImpl;

/**
 * This class handles the movement of a pawn.
 */
public class PawnAnimation implements Runnable {

    private static final double STEP = 6;

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
                if ((this.pawnClass.getRow() == (GameBoardImpl.getBoxesPerRaw() - 1))
                        && (this.pawnClass.getPositionInRow() == (GameBoardImpl.getBoxesPerRaw() - 1))) {
                    this.goBack(nMoves - i);
                    break;
                }
                if (this.pawnClass.getPositionInRow() == (GameBoardImpl.getBoxesPerRaw() - 1)) {
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
                if ((this.pawnClass.getRow() == (GameBoardImpl.getBoxesPerRaw() - 1))
                        && (this.pawnClass.getPositionInRow() == (GameBoardImpl.getBoxesPerRaw() - 1))
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
                    this.jump(finalPos);
                }
            }
            Platform.runLater(() -> ViewImpl.getPlayScene().endTurn());
        }
    }

    private void jump(final int finalPosition) {

        final int nX;
        final int nY = finalPosition / GameBoardImpl.getBoxesPerRaw();
        final int change = nY % 2;
        if (change == 0) {
            this.pawnClass.setPositionInRow(finalPosition % GameBoardImpl.getBoxesPerRaw());
            if (this.pawnClass.getDirection() != Direction.RIGHT) {
                this.pawnClass.changeDirection();
            }
            nX = this.pawnClass.getPositionInRow();
        } else {
            if (this.pawnClass.getDirection() != Direction.LEFT) {
                this.pawnClass.changeDirection();
            }
            nX = GameBoardImpl.getBoxesPerRaw() - 1 - finalPosition % GameBoardImpl.getBoxesPerRaw();
            this.pawnClass.setPositionInRow(GameBoardImpl.getBoxesPerRaw() - 1 - nX);
        }
        Platform.runLater(() -> this.pawnClass.getPawn().setX(
            this.pawnClass.getIniPos().getFirst() + (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw()) * nX));
        Platform.runLater(() -> this.pawnClass.getPawn().setY(
            this.pawnClass.getIniPos().getSecond() - (Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw()) * nY));
        this.pawnClass.setRow(nY);
    }

    private void moveUp() {
        final double startPos = this.pawnClass.getPawn().getY();
        final double finalPos = startPos - Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw();
        while (this.pawnClass.getPawn().getY() > finalPos) {
            Platform.runLater(() -> this.pawnClass.getPawn().setY(this.pawnClass.getPawn().getY() - STEP));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    private void moveRight() {
        final double startPos = this.pawnClass.getPawn().getX();
        final double finalPos = startPos + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw();
        while (this.pawnClass.getPawn().getX() < finalPos) {
            Platform.runLater(() -> this.pawnClass.getPawn().setX(this.pawnClass.getPawn().getX() + STEP));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    private void moveLeft() {
        final double startPos = this.pawnClass.getPawn().getX();
        final double finalPos = startPos - Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw();
        while (this.pawnClass.getPawn().getX() > finalPos) {
            Platform.runLater(() -> this.pawnClass.getPawn().setX(this.pawnClass.getPawn().getX() - STEP));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    private void moveDown() {
        final double startPos = this.pawnClass.getPawn().getY();
        final double finalPos = startPos + Dimension.BOARD_H / GameBoardImpl.getBoxesPerRaw();
        while (this.pawnClass.getPawn().getY() < finalPos) {
            Platform.runLater(() -> this.pawnClass.getPawn().setY(this.pawnClass.getPawn().getY() + STEP));
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
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
                    this.pawnClass.setPositionInRow(GameBoardImpl.getBoxesPerRaw() - 1);
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
