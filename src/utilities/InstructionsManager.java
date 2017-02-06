package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class handles read/write operations relatives to a file which contains the game instructions.
 * It's designed using Singleton pattern.
 */
public final class InstructionsManager implements FileManager {

    private static final InstructionsManager SINGLETON = new InstructionsManager();

    // private constructor
    private InstructionsManager() { }

    /**
     * Static method which returns an InstructionsManager unique instance.
     * @return an InstructionsManager unique instance.
     */
    public static InstructionsManager get() {
        return SINGLETON;
    }

    @Override
    public String readFromFile(final String path) {

        final File file = new File(path);
        String text = "";

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String line = null;
            line = bf.readLine();
            while (line != null) {
                text = text.concat(line + "\n");
                line = bf.readLine();
            }
        } catch (IOException exception) {
            ConsoleLog.get().print("Error...Failed to read instructions from file located at: " + path);
            exception.printStackTrace();
        }

        return text;
    }

    @Override
    public void writeToFile(final String path) {

    }

}