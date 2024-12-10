package Lab4;
import java.util.Scanner;


public class Task2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();

        // Initialize dp array
        int[] dp = new int[X + 1];

        // Set dp[0] = 0 because no subtraction is needed for 0
        dp[0] = 0;

        // Calculate minimum subtractions for each number from 1 to X
        for (int i = 1; i <= X; i++) {
            dp[i] = Integer.MAX_VALUE; // Start with a large value
            for (int square = 1; square * square <= i; square++) {
                dp[i] = Math.min(dp[i], dp[i - square * square] + 1);
            }
        }

        // The result is stored in dp[X]
        System.out.println(dp[X]);

    }
}