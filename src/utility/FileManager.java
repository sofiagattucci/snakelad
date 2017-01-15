package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

    /**
     * This class handles read/write operations relatives to files.
     * Used Singleton Pattern.
     */
public final class FileManager {

    private static final FileManager FILE_MANAGER = new FileManager();

    private FileManager() { }

    /**
     * Getter of the FileManeger.
     * @return
     *     The fileManager
     */
    public static FileManager get() {
        return FILE_MANAGER;
    }

    /**
     * Method to read a file.
     * @param f
     *     The file to read
     * @return
     *     A Text element which contains the text of the read file
     */
    public String read(final File f) {

        String text = "";
        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            String line = null;
            line = bf.readLine();
            while (line != null) {
                text = text.concat(line + "\n");
                line = bf.readLine();
            }
        } catch (IOException e) {
            Log.get().print("Error...Failed to load instructions from file");
        }
        return text;
    }

    /**
     * Method to write into s file.
     * @param f
     *     The file i want to override.
     */
    public void write(final File f) {

    }
}
