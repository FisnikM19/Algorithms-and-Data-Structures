package One_Dimensional_Data_Structures.Task1;

import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String line = "";
        Stack<String> stack = new Stack<>();

        while (!line.equals("x")) {
            line = sc.next();
            char firstChar = line.charAt(0);

            if (firstChar == 'f') {
                stack.push(line);
            } else if (firstChar == 'e') {
                if (stack.isEmpty()) {
                    System.out.println("Invalid");
                    return;
                }
                char lastChar = line.charAt(line.length()-1);
                String element = stack.pop();
                char lastCharElement = element.charAt(element.length()-1);

                if (lastChar != lastCharElement) {
                    System.out.println("Invalid");
                    return;
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
    }
}
