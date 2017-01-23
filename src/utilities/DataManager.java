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
 * Used Singleton Pattern.
 */
public final class DataManager implements FileManager {

    private static final int SEPARATOR = 0;
    private static final DataManager DATA_MANAGER = new DataManager();

    //private constructor
    private DataManager() { }


    /**
     * Getter of the DataManeger.
     * @return
     *     The DataManager.
     */
    public static DataManager get() {
        return DATA_MANAGER;
    }


    @Override
    public List<Integer> read(final File f) {

        final List<Integer> data = new LinkedList<>();

        try (BufferedReader bf = new BufferedReader(new FileReader(f))) {
            Optional<String> line = Optional.empty();
            line = Optional.ofNullable(bf.readLine());
            while (line.isPresent()) {
                while (!line.get().startsWith("#")) {
                    data.add(Integer.parseInt(line.get()));
                    line = Optional.ofNullable(bf.readLine());
                }
                data.add(DataManager.SEPARATOR);
                line = Optional.ofNullable(bf.readLine());
            }
        } catch (IOException e) {
            ConsoleLog.get().print("Error...Failed to read from data file");
        }
        return data;
    }


    @Override
    public void write(final File f) {

    }

}
