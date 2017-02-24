package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

/**
 * This class manages a circular list of elements and its elements in the GUI.
 */
public abstract class ImagesCircularList {

    private static final String OK = "OK";
    private static final int FONT_SIZE = 20;

    private final List<Image> list = new ArrayList<>();
    private final ImageView image = new ImageView();
    private final Label descLabel = new Label();
    private final Label titleLabel = new Label();
    private final Button next = new Button(">");
    private final Button prev = new Button("<");
    private final Button ok = new Button(OK);
    private int counter;

    /**
     * Constructor of this class.
     * @param n
     *     The number of elements of the list
     * @param s
     *     The string to put in the title label
     * @param dim
     *     The dimension of the image shown in the GUI 
     */
    public ImagesCircularList(final int n, final String s, final double dim) {

        this.titleLabel.setText(LanguageStringMap.get().getMap().get(s));
        this.image.setFitWidth(dim);
        this.image.setFitHeight(dim);
        this.titleLabel.setFont(new Font(FONT_SIZE));
        this.descLabel.setFont(new Font(FONT_SIZE));

        for (int i = 0; i < n; i++) {
            this.list.add(this.getImage());
        }

        this.prev.setOnAction(e -> {
            this.counter--;
            if (this.counter < 0) {
                this.counter = n - 1;
            }
            this.image.setImage(this.getImage());
            this.updateDescLabel(this.counter);
        });

        this.next.setOnAction(e -> {
            this.counter++;
            if (this.counter >= n) {
                this.counter = 0;
            }
            this.image.setImage(this.getImage());
            this.updateDescLabel(this.counter);
        });

        this.ok.setOnAction(e -> {
            this.prev.setDisable(true);
            this.next.setDisable(true);
            this.setScenery(this.counter);
            this.nextToShow().setVisible(true);
        });
        this.reset();
    }

    /**
     * It resets the circular list and its objects in the GUI.
     */
    public final void reset() {
        this.prev.setDisable(false);
        this.next.setDisable(false);
        this.counter = 0;
        this.image.setImage(this.getImage());
        this.updateDescLabel(this.counter);
    }

    /**
     * It updates the description label when this method is invoked.
     * @param n
     *     The index of the selected element in the list
     */
    protected abstract void updateDescLabel(int n);

    /**
     * It updates the element selected.
     * @param n
     *     The index of the selected element in the list
     */
    protected abstract void setScenery(int n);

    /**
     * It gets the image to put in the image view.
     * @return
     *     The image to show in the GUI
     */
    protected abstract Image getImage();

    /**
     * It specifies the next node of the layout graph to show in the GUI. 
     * @return
     *     The next node in the layout graph to show
     */
    protected abstract Node nextToShow();

    /**
     * It gets a list of the nodes shown in the image view.
     * @return
     *     A list of the nodes shown in the GUI
     */
    public List<Node> getNodes() {
        return new ArrayList<>(Arrays.asList(this.titleLabel, this.prev, this.image, this.next, this.ok, this.descLabel));
    }
 
    /**
     * Getter of the description label of this class, a label that shows info about the selected element.
     * @return
     *     The description label
     */
    protected Label getDescLabel() {
        return this.descLabel;
    }

    /**
     * It inserts the selected element in the list.
     * @param im
     *     The element to add in the list
     */
    protected void insertElem(final Image im) {
        this.list.add(im);
    }

    /**
     * Getter of the counter.
     * @return
     *     The counter that specifies a position in the list
     */
    protected int getCounter() {
        return this.counter;
    }

    /**
     * Getter of the title label, a label with some info about the elements of the list.
     * @return
     *     The title label
     */
    protected Label getTitleLabel() {
        return this.titleLabel;
    }
}
