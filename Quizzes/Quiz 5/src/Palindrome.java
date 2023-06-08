import java.util.Locale;

public class Palindrome {
    /**
     * This method check the given stack and decides if the expression is a palindrome.
     * @param stack is the stack used.
     * @return is a bool which lets us know if given expression is a palindrome.
     */
    public static boolean stackChecker(Structure<String> stack) {
        if (stack.size() % 2 != 0) { // If size is an odd number, middle term will not matter. Thus, not needed.
            stack.remove((stack.size() - 1) / 2);
        }
        while (!stack.isEmpty()) {
            if (stack.get(0).equals(stack.get(stack.size()-1))) {
                stack.remove(0);
                stack.remove(stack.size() - 1);
            }

            else {
                System.out.println(stack.get(0));
                System.out.println(stack.get(stack.size()-1));
                return false;
            }
        }
        return true;
    }

    /**
     * This method creates the required stack, so it can be checked in the method above.
     * @param str is the expression given.
     * @return is a stack which has only the letters.
     */
    public static String palindromeChecker(String str) {
        Structure<String> stack = new Structure<>();

        for (int i = 0; i < str.length(); i++) {
            if (String.valueOf(str.charAt(i)).matches("[a-zA-Z]")) { // catching only the letters
                stack.push(String.valueOf(str.charAt(i)).toLowerCase(Locale.US));
            }
        }

        if (stackChecker(stack)) {
            return String.format("\"%s\" is a palindrome.", str);
        }
        else {
            return String.format("\"%s\" is not a palindrome.", str);
        }
    }
}
