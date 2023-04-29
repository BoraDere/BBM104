import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * ReadFile class does the required reading operations at the beginning of the code.
 */
public class ReadFile {
    /**
     * @param path is completed with args[0] and gives the full path of the file to be read.
     * @param discardEmptyLines boolean variable which controls this method's empty-line-discarding process.
     * @param trim boolean variable which controls this method's trimming process.
     * @return returns the content of the file
     */
    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) { // Thanks for the code hocam :)
        try {
            List<String> lines = Files.readAllLines(Paths.get(path)); //Gets the content of file to the list.
            if (discardEmptyLines) { //Removes the lines that are empty with respect to trim.
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) { //Trims each line.
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        }
        catch (IOException e) { //Returns null if there is no such a file.
            e.printStackTrace();
            return null;
        }
    }
}

