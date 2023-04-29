import java.io.FileWriter;
import java.io.IOException;

/**
 * This class is responsible for writing operations.
 */
public class FileWrite {
    /**
     * @param n is the output to be written.
     * @param fileName is the name of the output file.
     */
    static void fileWrite(String n, String fileName) { // a very simple method to write output.
        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(n);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
