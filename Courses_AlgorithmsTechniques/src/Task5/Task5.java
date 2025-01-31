package Task5;
/*
We are given a street with N possible positions on which we can put a light.
A single light will illuminate its own position and 2 more positions to the left and 2 more to the right of it.
Our task is to illuminate the street with the minimum possible lights. Not all possible positions must contain light.

Input: The first number in the input is the number of possible positions to put a light bulb N and the length of the street M,
then in the next line are the possible positions on which we can put a light.
Output: The minimum lights we need to illuminate the street.

Input:
5 5
1 2 3 4 5
Output:
1
 */

import java.util.Arrays;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of possible positions
        int m = sc.nextInt(); // street length
        int[] positions = new int[n];
        for (int i = 0; i < n; i++) {
            positions[i] = sc.nextInt();
        }
        System.out.println(minimumLights(positions, m));
    }

    public static int minimumLights(int[] positions, int streetLength) {
        Arrays.sort(positions);
        int lights = 0;
        int i = 0;
        int currentPos = 1; // start from position 1

        while (currentPos <= streetLength) {
            // Find the rightmost position that can cover currentPos
            int bestPos = -1;
            while (i < positions.length && positions[i] <= currentPos + 2) {
                bestPos = positions[i];
                i++;
            }

            // If no position can cover currentPos, return -1 (impossible)
            if (bestPos == -1) {
                return -1;
            }

            // Add a light at bestPos
            lights++;
            // Skip all positions that this light covers (bestPos + 2)
            currentPos = bestPos + 3;
        }

        return lights;
    }
}
