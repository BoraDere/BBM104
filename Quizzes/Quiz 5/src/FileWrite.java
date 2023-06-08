import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    /**
     * A very simple method to write output.
     * @param n is the string tro be written.
     * @param fileName is the name of the output file.
     */
    static void fileWrite(String n, String fileName) {
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
