package Task6;
/*
You are given a binary string S. A binary string is a string that contains only '1's and '0's.
A substring of a binary string is called positive if the number of '1's in the substring is strictly greater than the number of '0's.
Print the number of positive substrings for the given string.

Input: The first line of the input contains the length of the string N. The second line contains the binary string S.
5
10011
Output: The output should print the number of positive substrings in S
6

Explanation: For the given string 10011, the positive substrings are:
1(position 0)
1(position 3)
1 (position 4)
11 (positions 3 и 4)
011 (positions from 2 to 4)
10011 (the entire string)
 */

import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();

        int count = 0;
        // Try all possible substrings
        for (int i = 0; i < n; i++) {
            int ones = 0;
            int zeros = 0;
            // For each starting position i, try all possible endings j
            for (int j = i; j < n; j++) {
                // Count 1s and 0s in current substring
                if (s.charAt(j) == '1') {
                    ones++;
                } else {
                    zeros++;
                }
                // If number of 1s is greater than 0s, we found a positive substring
                if (ones > zeros) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
