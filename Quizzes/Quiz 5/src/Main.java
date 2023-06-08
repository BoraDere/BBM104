public class Main {

    /**
     * This is the driver method. I had my score deducted for my past assignments because my main method was
     * too "crowded". This was a comment made by purely looking at the line count of my main method, not the complexity
     * of it. You are on the same page with me about the main method-usage, so I thank you again specially.
     * @param args are given from the terminal.
     */
    public static void main(String[] args) {
        String[] lines = ReadFile.readFile(args[0], false, false);
        for (String line : lines) {
            String[] splits = line.split("\t");
            if (line.startsWith("Convert from Base 10 to Base 2:")) {
                FileWrite.fileWrite(DecimalToBinary.binaryConversion(Integer.parseInt(splits[1])), args[1]);
                FileWrite.fileWrite("\n", args[1]);
            }
            else if (line.startsWith("Count from 1 up to n in binary:")) {
                BinaryCounting.countUpToBinary(Integer.parseInt(splits[1]), args[1]);
                FileWrite.fileWrite("\n", args[1]);
            }
            else if (line.startsWith("Check if following is palindrome or not:")) {
                FileWrite.fileWrite(Palindrome.palindromeChecker(splits[1]), args[1]);
                FileWrite.fileWrite("\n", args[1]);
            }
            else if (line.startsWith("Check if following expression is valid or not:")) {
                FileWrite.fileWrite(BalancedParenthesis.expressionChecker(splits[1]), args[1]);
                FileWrite.fileWrite("\n", args[1]);
            }
        }
    }
}