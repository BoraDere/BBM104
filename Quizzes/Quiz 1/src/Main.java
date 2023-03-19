import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Main {
    public static String[] readFile(String path) {
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
            e.printStackTrace();
            return null;
        }
    }

    static void fileWrite(String n) {
        try {
            FileWriter writer = new FileWriter("output.txt", true);
            writer.write(n);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isPrime(int n) {
        for (int i = 2; i < n; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindrome(int n) {
        int r, sum = 0;
        int temp = n;
        while (n > 0) {
            r = n % 10;
            sum = (sum * 10) + r;
            n = n / 10;
        }
        return temp == sum;
    }

    static boolean isEmirp(int n) {
        int r, sum = 0;
        while (n > 0) {
            r = n % 10;
            sum = (sum * 10) + r;
            n = n / 10;
        }
        return isPrime(sum);
    }

    static int findDivisorsSum(int n) {
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    static void armstrong(String n) {
        List<Integer> armList = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(n); i++) {
            int remainder, result = 0, number = i;
            while (number != 0) {
                remainder = number % 10;
                result += Math.pow(remainder, String.valueOf(i).length());
                number /= 10;
            }
            if (result == i) {
                armList.add(i);
            }
        }
        for (Integer a : armList) {
            fileWrite(a + " ");
        }
        fileWrite("\n\n");
    }

    static void emirp(String n) {
        List<Integer> emirpList = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(n); i++) {
            if (isPrime(i) && !isPalindrome(i) && isEmirp(i)) {
                emirpList.add(i);
            }
        }
        for (Integer a : emirpList) {
            fileWrite(a + " ");
        }
        fileWrite("\n\n");
    }

    static void abundant(String n) {
        List<Integer> abList = new ArrayList<>();
        for (int i = 1; i <= Integer.parseInt(n); i++) {
            int sum = findDivisorsSum(i);
            if (sum > i) {
                abList.add(i);
            }
        }
        for (Integer a : abList) {
            fileWrite(a + " ");
        }
        fileWrite("\n\n");
    }

    static void ascendingSorting(List<String> n) {
        List<Integer> sortList = new ArrayList<>();
        for (int i = 0; i < n.size(); i++) {
            for (int j = 0; j <= i; j++) {
                sortList.add(Integer.parseInt(n.get(j))); //stringi int yap
            }
            Collections.sort(sortList);
            for (Integer a : sortList) {
                fileWrite(a + " ");
            }
            fileWrite("\n");
            sortList.clear();
        }
        fileWrite("\n");
    }

    static void descendingSorting(List<String> n) {
        List<Integer> sortList = new ArrayList<>();
        for (int i = 0; i < n.size(); i++) {
            for (int j = 0; j <= i; j++) {
                sortList.add(Integer.parseInt(n.get(j)));
            }
            sortList.sort(Collections.reverseOrder());
            for (Integer a : sortList) {
                fileWrite(a + " ");
            }
            fileWrite("\n");
            sortList.clear();
        }
        fileWrite("\n");
    }

    public static void main(String[] args) {
        String fileName = args[0];
        String[] lines = readFile(fileName);
        String command = "";
        List<String> nums = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(":") && line.contains(command)) {
                command = line;
            }
            else if (line.contains(":") || line.equals("Exit")) {
                fileWrite(command);
                if (command.contains("Armstrong")) {
                    fileWrite(" " + nums.get(0) + "\n");
                    armstrong(nums.get(0));
                }
                if (command.contains("Emirp")) {
                    fileWrite(" " + nums.get(0) + "\n");
                    emirp(nums.get(0));
                }
                if (command.contains("Abundant")) {
                    fileWrite(" " + nums.get(0) + "\n");
                    abundant(nums.get(0));
                }
                if (command.contains("Ascending")) {
                    fileWrite("\n");
                    ascendingSorting(nums);
                }
                if (command.contains("Descending")) {
                    fileWrite("\n");
                    descendingSorting(nums);
                }
                if (line.equals("Exit")) {
                    fileWrite("Finished...");
                }
                nums.clear();
                command = line;
            }
            else if (!line.equals("-1")) {
                nums.add(line);
            }
        }
    }
}
