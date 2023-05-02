import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
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

    static String[] readFile(String path) {
        try {
            int i = 0;
            int length = Files.readAllLines(Paths.get(path)).size();
            String[] results = new String[length];
            for (String line : Files.readAllLines(Paths.get(path))) {
                results[i++] = line;
            }
            return results;
        }
        catch (IOException e) {
            fileWrite("There should be an input file in the specified path\n", "output.txt");
            return null;
        }
    }

    static boolean regex(String n) {
        return !n.matches("[a-zA-Z ]*");
    }

    public static void main(String[] args) {
        boolean cont = true;
        String[] file = readFile(args[0]);
        try {
            String nop = args[1];
            fileWrite("There should be only 1 paremeter\n", "output.txt");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            try {
                if (file.length == 0) {
                    fileWrite("The input file should not be empty\n", "output.txt");
                }
                else {
                    for (String i : file) {
                        if (regex(i)) {
                            fileWrite("The input file should not contains unexpected characters\n", "output.txt");
                            cont = false;
                        }
                    }
                    if (cont) {
                        for (String i : file) {
                            fileWrite(i + "\n", "output.txt");
                        }
                    }
                }
            }
            catch (NullPointerException t) {
                String nop = null;
            }
        }
    }
}
