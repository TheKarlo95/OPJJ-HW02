package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Command-line application which accepts a single command-line argument: a
 * postfix expression which should be evaluated(for example: "-1 8 2 / +").
 * 
 * @author TheKarlo95
 * @version 1.0
 */
public class StackDemo {

    /**
     * Starting point of a program.
     * 
     * @param args
     *            Command-line argument
     */
    public static void main(String[] args) {
        ObjectStack stack = new ObjectStack();

        String[] arguments = args[0].trim().split(" ");
        for (String arg : arguments) {
            if (isNumber(arg)) {
                stack.push(Integer.parseInt(arg));
                continue;
            } else {
                int rightOperand = (int) stack.pop();
                int leftOperand = (int) stack.pop();

                try {
                    stack.push(doOperation(leftOperand, rightOperand, arg));
                } catch (IllegalArgumentException e) {
                    System.err.println("Argument " + arg + " is not a number or"
                            + " valid operator(+, -, *, / or %)");
                }
            }
        }

        if (stack.size() != 1) {
            System.err.println("Error!");
        } else {
            System.out.println("Expression evaluates to " + stack.pop());
        }
    }

    /**
     * Returns true if this string represents integer.
     * 
     * @param str
     *            String which is going to be tested
     * @return True if this string represents integer and false otherwise
     */
    private static boolean isNumber(String str) {
        return str.matches("^-?[0-9]+(\\.[0-9]+)?$");
    }

    /**
     * Does an operation on left and right operand according to the operator.
     * This method can do addition, subtraction, multiplication, division and
     * modulus.
     * 
     * @param leftOperand
     *            Left operand
     * @param rightOperand
     *            Right operand
     * @param operator
     *            Operator
     * @return Result of the operation
     */
    private static int doOperation(int leftOperand, int rightOperand, String operator) {
        switch (operator) {
            case "+":
                return leftOperand + rightOperand;
            case "-":
                return leftOperand - rightOperand;
            case "*":
                return leftOperand * rightOperand;
            case "/":
                return leftOperand / rightOperand;
            case "%":
                return leftOperand % rightOperand;
            default:
                throw new IllegalArgumentException();
        }
    }

}
