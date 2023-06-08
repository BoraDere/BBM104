public class BalancedParenthesis {
    /**
     * This method check the given stack and decides if the expression is a valid one.
     * @param stack is the stack used.
     * @return is a bool which lets us know if given expression is valid.
     */
    public static boolean stackChecker(Structure<String> stack) {
        while (!stack.isEmpty()) {
            if (stack.get(0).equals("}") && stack.get(stack.size()-1).equals("{")) { // Catching only the correct cases.
                stack.remove(0);
                stack.remove(stack.size()-1);
            }
            else if (stack.get(0).equals(")") && stack.get(stack.size()-1).equals("(")) {
                stack.remove(0);
                stack.remove(stack.size()-1);
            }
            else if (stack.get(0).equals("]") && stack.get(stack.size()-1).equals("[")) {
                stack.remove(0);
                stack.remove(stack.size()-1);
            }
            else {
                return false;
            }
        }
        return true;
    }

    /**
     * This method creates the required stack, so it can be checked in the method above.
     * @param expression is the expression given.
     * @return is a stack which has only the parenthesis characters.
     */
    public static String expressionChecker(String expression) {
        Structure<String> stack = new Structure<>();

        for (int i = 0; i < expression.length(); i++) {
            if (String.valueOf(expression.charAt(i)).matches(".*[(){}\\[\\]].*")) {
                stack.push(String.valueOf(expression.charAt(i)));
            }
        }

        if (stack.size() % 2 == 1) { // Simple workaround. An expression with odd amount of terms will have an unclosed or
                                     // unopened parenthesis.
            return String.format("\"%s\" is not a valid expression.", expression);
        }
        else {
            if (stackChecker(stack)) {
                return String.format("\"%s\" is a valid expression.", expression);
            }
            else {
                return String.format("\"%s\" is not a valid expression.", expression);
            }
        }
    }
}
