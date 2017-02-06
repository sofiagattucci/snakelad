package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This class handles read/write operations relatives to a file which contains the data of a scenery.
 * It's designed using Singleton pattern.
 */
public final class SceneryDataManager implements FileManager {

    private static final SceneryDataManager SINGLETON = new SceneryDataManager();
    private static final int SEPARATOR = 0;

    // private constructor
    private SceneryDataManager() { }


    /**
     * Static method which returns a DataManager unique instance.
     * @return a DataManager unique instance.
     */
    public static SceneryDataManager get() {
        return SINGLETON;
    }

    @Override
    public List<Integer> readFromFile(final String path) {

        final File file = new File(path);
        final List<Integer> data = new LinkedList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            Optional<String> line = Optional.empty();
            line = Optional.ofNullable(bf.readLine());
            while (line.isPresent()) {
                while (!line.get().startsWith("#")) {
                    data.add(Integer.parseInt(line.get()));
                    line = Optional.ofNullable(bf.readLine());
                }
                data.add(SceneryDataManager.SEPARATOR);
                line = Optional.ofNullable(bf.readLine());
            }
        } catch (IOException exception) {
            ConsoleLog.get().print("Error...Failed to read scenery from data file located at: " + path);
            exception.printStackTrace();
        }
        return data;
    }

    @Override
    public void writeToFile(final String path) {

    }

}
