package Task4;
/*
We are given N scheduled meetings. Write an algorithm that finds the minimum number of meeting rooms needed to schedule those meetings.
Input: The first number in the input is the number of meetings N, then in the next N lines are the start and end time for each of the meetings.
Output: The minimum number of needed meeting rooms to schedule the meetings.

Input:
5
1 2
2 3
3 4
4 5
5 6

Output:
2
 */

import java.util.Arrays;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int i = 0, j = 0;
        int count = 0, max = 0;

        while (i < n && j < n) {
            if (start[i] <= end[j]) {
                count++;
                max = Math.max(max, count);
                i++;
            } else {
                count--;
                j++;
            }
        }

        System.out.println(max);
    }
}
