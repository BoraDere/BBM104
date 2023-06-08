import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
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
