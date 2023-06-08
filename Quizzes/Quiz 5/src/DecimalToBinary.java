public class DecimalToBinary {
    /**
     * The simplest binary converting algorithm.
     * @param num is the decimal integer given.
     * @return is the binary equivalent of the given number in requested format.
     */
    public static String binaryConversion(int num) {
        int number = num; // To use returning the requested format, since num changes in the algorithm.
        Structure<Integer> stack = new Structure<>();
        String binaryString = "";

        if (num == 0) {
            binaryString += "0";
        }

        while (num > 0) {
            int remainder = num % 2;
            stack.push(remainder);
            num /= 2;
        }

        while (!stack.isEmpty()) {
            binaryString += stack.get(0);
            stack.remove(0);
        }

        return String.format("Equivalent of %d (base 10) in base 2 is: %s", number, binaryString);
    }
}
