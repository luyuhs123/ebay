import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static int calculate(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); ) {
            char c = expression.charAt(i);
            if (Character.isDigit(c)) {
                int num = Character.getNumericValue(c);
                while (i + 1 < expression.length() && Character.isDigit(expression.charAt(i + 1))) {
                    num = num * 10 + Character.getNumericValue(expression.charAt(++i));
                }
                values.push(num);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && isFirst(operators.peek(), c)) {
                    values.push(operator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
            }
            i++;
        }

        while (!operators.isEmpty()) {
            values.push(operator(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private static boolean isFirst(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        return false;
    }

    private static int operator(char operator, int b, int a) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b != 0) {
                    return a / b;
                } else {
                    throw new IllegalArgumentException("分母不能为0");
                }
            default:
                throw new IllegalArgumentException("非法操作符");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.next();
        int result = calculate(str);
        System.out.println(result);
    }
}