package Task8;
/*
What is the minimum number of times we need to subtract the square of an integer for a number X to become 0?
Input: Integer X from 1 to 10^5.
Output: The desired result.

Examples:
Input	Output	Explanation
13
2
13 - 2*2  = 9 ;   9 - 3*3  = 0. (we subtracted 2 times)
21
3
21 - 2*2  = 17;  17 - 4*4 = 1;  1 - 1*1 = 0. (we subtracted 3 times)
25
1
25 - 5*5 = 0 . (we subtracted 1 time)
32
16
32 - 4*4 = 16;  16 - 4*4 = 0. (we subtracted 2 times)

 */
import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int X = input.nextInt();
        int result;

        // vashiot kod ovde / your code goes here

        int[] dp = new int[X + 1];
        dp[0] = 0;
        for (int i = 1; i <= X; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int square = 1; square * square <= i; square++) {
                dp[i] = Math.min(dp[i], dp[i - square * square] + 1);
            }
        }

        result = dp[X];
        System.out.println(result);
    }
}
