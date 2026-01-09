import java.util.Scanner;
import java.util.Stack;

public class Postfix {

    public static void main(String[] args) {
        System.out.println("Enter a postfix expression");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        Number result = evaluateExpression(input);
        if (result != null) {
            System.out.println("Expression: " + input + " --> " + result);
        }
    }

    public static char checkValidOperator(String operator) {
        switch(operator) {
            case "+":
                return '+';
            case "-":
                return '-';
            case "*":
                return '*';
            case "/":
                return '/';
        }
        return '?';
    }

    public static int execute(Stack<Integer> stack, char operator) {
        int rightOperand = stack.pop();
        int leftOperand = stack.pop();
        switch(operator) {
            case '+':
                return stack.push(leftOperand + rightOperand);
            case '-':
                return stack.push(leftOperand - rightOperand);
            case '*':
                return stack.push(leftOperand * rightOperand);
            case '/':
                return stack.push(leftOperand / rightOperand);
        }
        return stack.peek();
    }

    public static Number evaluateExpression(String expr) {
        String tooFewOperands = "Too few operands";
        String unknownOperator = "Unknown operator: ";
        String tooManyOperands = "Too many operands.";
        Stack<Integer> stack = new Stack<>();
        String errMsg = null;
        Scanner scan = new Scanner(expr);

        while (scan.hasNext()) {
            if (scan.hasNextInt()) {
                int operand = scan.nextInt();
                System.out.println("Operand read: " + operand);
                stack.push(operand);
            } else {
                String token = scan.next();
                char operator = checkValidOperator(token);
                if (operator != '?') {
                    System.out.println("Operator read: " + operator);
                    if (stack.size() >= 2) {
                        execute(stack, operator);
                    } else {
                        errMsg = tooFewOperands;
                    }
                } else {
                    errMsg = unknownOperator + token;
                }
            }
            System.out.println("------ Stack state -----");
            System.out.println(stack.toString());
        }
        if (errMsg != null) {
            System.out.println("Failed evaluation of |" + expr + "|\n" + errMsg);
            return null;
        }
        if (stack.size() > 1) {
            System.out.println("Failed evaluation of |" + expr + "|\n" + tooManyOperands + stack.toString());
            return null;
        }
        return stack.peek();
    }
}
