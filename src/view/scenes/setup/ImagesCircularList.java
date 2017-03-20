package view.scenes.setup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import view.LanguageStringMap;

/**
 * This class manages a circular list of elements and its elements in the GUI.
 * @param <X>
 *     The type of a parameter used in this class.
 */
public abstract class ImagesCircularList<X> {

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
    private X typeParam;

    /**
     * Constructor of this class.
     * @param n
     *     The number of elements of the list
     * @param s
     *     The string to put in the title label
     * @param dim
     *     The dimension of the image shown in the GUI
     * @param type
     *     A starting value for the parameter.
     * @param next
     *     A node in the GUI graph whose visibility depends on this class. Actually it' s set visible by this class
     */
    public ImagesCircularList(final int n, final String s, final double dim, final X type, final Node next) {

        this.typeParam = type;
        this.titleLabel.setText(LanguageStringMap.get().getMap().get(s));
        this.image.setFitWidth(dim);
        this.image.setFitHeight(dim);
        this.titleLabel.setFont(new Font(FONT_SIZE));
        this.descLabel.setFont(new Font(FONT_SIZE));

        IntStream.range(0, n)
                 .forEach(i -> {
                     this.setParameter(i);
                     this.list.add(this.getImage());
                     this.counter++;
                 });
        this.prev.setOnAction(e -> {
            this.counter--;
            if (this.counter < 0) {
                this.counter = n - 1;
            }
            this.image.setImage(this.list.get(this.counter));
            this.updateDescLabel(this.counter);
        });

        this.next.setOnAction(e -> {
            this.counter++;
            if (this.counter >= n) {
                this.counter = 0;
            }
            this.image.setImage(this.list.get(this.counter));
            this.updateDescLabel(this.counter);
        });

        this.ok.setOnAction(e -> {
            this.prev.setDisable(true);
            this.next.setDisable(true);
            this.setParameter(this.counter);
            this.ok.setDisable(true);
            next.setVisible(true);
        });
        this.reset();
    }

    /**
     * It resets the circular list and its objects in the GUI.
     */
    public final void reset() {
        this.prev.setDisable(false);
        this.next.setDisable(false);
        this.ok.setDisable(false);
        this.counter = 0;
        this.image.setImage(this.list.get(this.counter));
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
    protected abstract void setParameter(int n);

    /**
     * It gets the image to put in the image view.
     * @return
     *     The image to show in the GUI
     */
    protected abstract Image getImage();
 
    /**
     * Getter of the description label of this class, a label that shows info about the selected element.
     * @return
     *     The description label
     */
    protected Label getDescLabel() {
        return this.descLabel;
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

    /**
     * Getter of a list of the nodes (managed by this class) shown in the image view.
     * @return
     *     A (unmodifiable) list of the nodes shown in the GUI
     */
    public List<Node> getNodes() {
        return Collections.unmodifiableList(
                new ArrayList<>(Arrays.asList(this.titleLabel, this.prev, this.image, this.next, this.ok, this.descLabel)));
    }

    /**
     * Getter of the parameter of the selected type.
     * @return
     *     The value of the parameter 
     */
    public X getParameterValue() {
        return this.typeParam;
    }

    /**
     * Setter of the parameter of the selected type.
     * @param newP
     *     The new value of the parameter
     */
    public void setParameterValue(final X newP) {
        this.typeParam = newP;
    }

    /**
     * It updates the language of the elements of this class.
     * @param newTitle
     *     The new text to put in the title label
     */
    protected void updateLanguage(final String newTitle) {
        this.getTitleLabel().setText(LanguageStringMap.get().getMap().get(newTitle));
    }
}
