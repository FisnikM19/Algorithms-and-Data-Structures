package Task4;

import java.util.Scanner;

public class Task4 {
    private static int najdolgaOpagackaSekvenca(int[] a) {

        // Vasiot kod tuka
        int n = a.length;
        int[] dp = new int[a.length];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }
        int maks = 1;
        dp[0] = 1;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
                maks = Math.max(maks, dp[j]);
            }
        }

        return maks;
    }

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);

        int n = stdin.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = stdin.nextInt();
        }
        System.out.println(najdolgaOpagackaSekvenca(a));
    }
}


// Find the longest descending sequence in an array. The numbers in the sequence do not
// have to be at consecutive indices in the array.
// Input:
// 8
// 1 2 3 4 5 6 7 8
// Output:
// 1
// Input:
// 11
// -16 -23 234 234 -234 -23 -57 5 -235 453 5645
// Output:
// 4
//
// Starting from -16, let's find the longest descending subsequence:
//
// Array: [-16, -23, 234, 234, -234, -23, -57, 5, -235, 453, 5645]
//
// Start at -16.
// Move to the next descending element: -23.
// From -23, go to the next descending element: -234.
// From -234, go to -235.
// The subsequence here is [-16, -23, -234, -235], which has a length of 4.