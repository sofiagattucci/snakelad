package utilities;

import java.io.File;

/**
 * It's an interface for a generic FileManager.
 */
public interface FileManager {

    /**
     * Method to read a file.
     * @param f
     *     The file to read.
     * @return
     *     an element which represents the content of the file.
     */
    Object read(File f);


    /**
     * Method to write into s file.
     * @param f
     *     The file i want to override.
     */
    void write(File f);

}