package utilities;

import java.io.FileInputStream;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

     /**
     * This class is used to load images. 
     * Used Singleton Pattern.
     */
public final class ImageLoader {

    private static final ImageLoader SINGLETON_LOADER = new ImageLoader();

    private ImageLoader() { }

    /**
     * Getter of the image loader.
     * @return 
     *          The image loader
     */
    public static ImageLoader get() {
        return SINGLETON_LOADER;
    }

    /**
     * The method loads the image form the file system and then returns an Image containing it. 
     * @param path
     *     Where the image is located in the file system
     * @return
     *     An Image containing the image. If an error occurs it returns null.
     */
    public Image getImage(final String path) {

    try (FileInputStream in = new FileInputStream(path)) {
        return new Image(in);
    } catch (Exception e) {
        ConsoleLog.get().print("Errore durante il caricamento dell' immagine " + path);
    }
    return null;
    }

    /**
     * The method loads the image form the file system and then returns an ImageView containing it. 
     * @param path
     *     Where the image is located in the file system
     * @return
     *     An ImageView containing the image. If an error occurs it returns null.
     */
    public ImageView getImageView(final String path) {
        return new ImageView(this.getImage(path));
    }
}