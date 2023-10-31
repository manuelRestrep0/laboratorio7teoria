import java.util.ArrayList;
import java.util.Stack;

public class Parser {
    public static double parseExpression(ArrayList<String> tokens) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                numbers.push(Double.parseDouble(token));
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && !token.equals("(") && hasHigherPrecedence(operators.peek(), token)) {
                    performOperation(numbers, operators);
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.isEmpty() && !operators.peek().equals("(")) {
                    performOperation(numbers, operators);
                }
                if (!operators.isEmpty() && operators.peek().equals("(")) {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Expresión no válida: paréntesis desequilibrados");
                }
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek().equals("(")) {
                throw new IllegalArgumentException("Expresión no válida: paréntesis desequilibrados");
            }
            performOperation(numbers, operators);
        }

        if (!numbers.isEmpty()) {
            return numbers.pop();
        } else {
            throw new IllegalArgumentException("Expresión no válida");
        }
    }

    private static boolean isNumber(String token) {
        return token.matches("\\d+\\.\\d+|\\d+");
    }

    private static boolean isOperator(String token) {
        return token.matches("\\+|\\-|\\*|\\/");
    }

    private static boolean hasHigherPrecedence(String operator1, String operator2) {
        return (operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"));
    }

    private static void performOperation(Stack<Double> numbers, Stack<String> operators) {
        String operator = operators.pop();
        if (operator.equals("(")) {
            throw new IllegalArgumentException("Expresión no válida: paréntesis desequilibrados");
        }
        double operand2 = numbers.pop();
        double operand1 = numbers.pop();
        double result = applyOperator(operand1, operand2, operator);
        numbers.push(result);
    }

    private static double applyOperator(double operand1, double operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new ArithmeticException("División por cero no permitida");
                }
            default:
                throw new IllegalArgumentException("Operador no válido: " + operator);
        }
    }
}