package Task7;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
For a given array of integers, find the maximum product formed by multiplication of the numbers of an increasing subarray of the given array.
Input: The first line of the input contains N, the number of elements of the array. The second line of the input contains the array.
Output: The maximum product formed in the described way.

Example:
Input:
6
3 100 4 5 150 6

Output: 45000
The maximum product is formed from the increasing subarray 3, 100, 150. Note that the longest increasing subarray is 3, 4, 5, 6.
 */

public class Task7 {
    static final long MOD = 2144509291L; // Use prime modulo larger than expected answer

    public static void main(String[] args) throws Exception {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);
        int arr[] = new int[N];
        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(pomniza[i]);
        }

        // dp[i] stores the maximum product ending at index i
        long[] dp = new long[N];

        // Initialize dp array with single element values
        for (int i = 0; i < N; i++) {
            dp[i] = arr[i];
        }

        long maxProduct = dp[0];

        // For each position, consider it as end of sequence
        for (int i = 1; i < N; i++) {
            // Check all previous positions
            for (int j = 0; j < i; j++) {
                // If current number is larger, we can extend the sequence
                if (arr[i] > arr[j]) {
                    // Calculate new product with modulo to prevent overflow
                    long newProduct = (dp[j] * arr[i]) % MOD;
                    dp[i] = Math.max(dp[i], newProduct);
                }
            }
            maxProduct = Math.max(maxProduct, dp[i]);
        }

        System.out.println(maxProduct);
    }
}