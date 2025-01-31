package Task2;
// We are given N trains. Write an algorithm that finds the minimum number of platforms needed to schedule those trains.
//Input: The first number in the input is the number of trains N, then in the next N lines are the arrival and departure minutes for each of the trains.
//Output: The minimum number of needed platforms to accommodate the trains.

/*
Input:
5
1 2
1 2
5 10
11 14
15 20
Output:
2

 */

import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = sc.nextInt();
            end[i] = sc.nextInt();
        }

        // Sort start and end times
        Arrays.sort(start);
        Arrays.sort(end);

        int platformsNeeded = 0;
        int maxPlatforms = 0;
        int i = 0, j = 0;

        // Use two pointers to find overlapping trains
        while (i < n && j < n) {
            if (start[i] <= end[j]) {
                platformsNeeded++;
                maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
                i++;
            } else {
                platformsNeeded--;
                j++;
            }
        }

        System.out.println(maxPlatforms);
    }
}
