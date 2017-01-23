package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class handles read/write operations relatives to a file which contains the game instructions.
 * Used Singleton Pattern.
 */
public final class InstructionsManager implements FileManager {

    private static final InstructionsManager INSTRUCTIONS_MANAGER = new InstructionsManager();

    //private constructor
    private InstructionsManager() { }

    /**
     * Getter of the InstructionsManager.
     * @return
     *     The InstructionsManager
     */
    public static InstructionsManager get() {
        return INSTRUCTIONS_MANAGER;
    }

    @Override
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
            ConsoleLog.get().print("Error...Failed to load instructions from file");
        }

        return text;
    }

    @Override
    public void write(final File f) {

    }

}