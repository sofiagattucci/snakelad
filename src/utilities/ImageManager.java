package utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

 /**
 * This class is used to load images. 
 * It's designed using Singleton pattern.
 */
public final class ImageManager implements FileManager {

    private static final ImageManager SINGLETON = new ImageManager();

    // private constructor
    private ImageManager() {

    }

    /**
     * Static method which returns the ImageManager unique instance.
     * @return the ImageManager unique instance.
     */
    public static ImageManager get() {
        return SINGLETON;
    }

    /**
     * The method loads the image from the file system, returning an ImageView containing it. 
     * @param path
     *     Where the image is located in the file system.
     * @return an ImageView containing the image loaded.
     */
    public ImageView getImageView(final String path) {
        return new ImageView(this.readFromFile(path));
    }

    @Override
    public Image readFromFile(final String path) {
        return new Image(ImageManager.class.getResourceAsStream("/" + path));
    }
/*
        try (FileInputStream in = new FileInputStream(path)) {
            return new Image(in);
        } catch (IOException exception) {
            ConsoleLog.get().print("ERROR occurred during loading image located at: " + path);
            exception.printStackTrace();
        }
        return null;
    }
*/
    @Override
    public void writeToFile(final String path) {

    }

}