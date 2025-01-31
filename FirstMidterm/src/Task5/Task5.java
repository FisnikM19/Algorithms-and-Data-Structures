package Task5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
// Write an algorithm that will calculate (evaluate) a mathematical expression that consists of numbers and operations
// for adding (*) and multiplying (*).
// Note: The operation of multiplying has precedence before the operation of adding.
// Input:
// 2+2*2*2*2*2*2+2*2
// Output:
// 70

public class Task5 {

    public static int evaluateExpression(String expression) {
        Stack<Integer> nums = new Stack<>();
        char lastOperator = '+'; // Keeps track of the last operator

        int currentNumber = 0;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = currentNumber * 10 + (c - '0'); // Handle multi-digit numbers
            }

            if (c == '+' || c == '*' || i == expression.length() - 1) {
                // Process the last operator
                if (lastOperator == '+') {
                    nums.push(currentNumber);
                } else if (lastOperator == '*') {
                    nums.push(nums.pop() * currentNumber); // Evaluate multiplication immediately
                }
                lastOperator = c;
                currentNumber = 0;
            }
        }

        // Evaluate the stack with addition
        int result = 0;
        while (!nums.isEmpty()) {
            result += nums.pop();
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(evaluateExpression(input.readLine()));
    }
}