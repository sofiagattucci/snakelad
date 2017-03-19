package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * This class handles read/write operations relatives to a file which contains the data of a scenery in the game.
 * It's designed using Singleton pattern.
 */
public final class SceneryDataManager implements FileManager {

    private static final SceneryDataManager SINGLETON = new SceneryDataManager();
    private static final String LINE_NOT_TO_CONSIDER = "#";
    private static final int SEPARATOR = 0;

    // private constructor
    private SceneryDataManager() {

    }

    /**
     * Method which returns the SceneryDataManager unique instance.
     * @return the SceneryDataManager unique instance.
     */
    public static SceneryDataManager get() {
        return SINGLETON;
    }

    @Override
    public List<Integer> readFromFile(final String path) {

        final List<Integer> dataList = new LinkedList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(System.class.getResource(path).getPath()))) {
            Optional<String> line;
            line = Optional.ofNullable(bf.readLine());
            while (line.isPresent()) {
                while (!line.get().startsWith(LINE_NOT_TO_CONSIDER)) {
                    dataList.add(Integer.parseInt(line.get()));
                    line = Optional.ofNullable(bf.readLine());
                }
                dataList.add(SEPARATOR);
                line = Optional.ofNullable(bf.readLine());
            }
            dataList.remove(0);
        } catch (IOException exception) {
            ConsoleLog.get().print("Error...Failed to read scenery from data file located at: " + path);
            exception.printStackTrace();
        } catch (Exception e) {
            System.out.println("ciao");
        }
        return dataList;
    }

    @Override
    public void writeToFile(final String path) {

    }

}
