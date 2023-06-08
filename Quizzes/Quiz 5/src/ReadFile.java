import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReadFile {
    /**
     * Thanks for the code hocam :)
     * @param path is the input file path.
     * @param discardEmptyLines is to choose if empty lines should be discarded.
     * @param trim is to choose if lines should be trimmed.
     * @return is the array includes the input data.
     */
    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); // Gets the content of file to the list.
            if (discardEmptyLines) { // Removes the lines that are empty with respect to trim.
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) { // Trims each line.
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        }
        catch (IOException e) { // Returns null if there is no such a file.
            e.printStackTrace();
            return null;
        }
    }
}

