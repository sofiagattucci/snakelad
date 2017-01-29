package utilities;

/**
 * It's an interface for a generic FileManager.
 */
public interface FileManager {

    /**
     * Method to read a file in a specific location.
     * @param path
     *     The path on the file system in which the file to read is located.
     * @return an Object which represents the content of the specified file.
     */
    Object readFromFile(String path);


    /**
     * Method to write into a file in a specific location.
     * @param path
     *     The path on the file system in which the file to write is located.
     */
    void writeToFile(String path);

}