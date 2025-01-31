package Task3;
/*
We are given N tasks with estimated completion time and the amount we can earn from that task.
Write an algorithm that finds the maximum earnings we can have for 40 hours. Note that we can have a partial earning from a partially completed task.
Input: The first number in the input is the number of tasks N, then in the next N lines are the time needed for the task and the amount of money we can earn from it.
Output: The maximum amount we can earn in 40 hours

Input:
3
10 60
20 100
30 120
Output:
200

Explanation:
40-10 = 30 -> amount += 60
30-20 = 10 -> amount += 60 + 100
10-30 = we can't
30/10 = 3 -> 120/3 = 40 -> amount += 160 + 40 = 200
 */

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] time = new int[n];
        int[] amount = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
            amount[i] = sc.nextInt();
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((double)amount[i]/time[i] <= (double) amount[j]/time[j]) {
                    int tempA = amount[i];
                    amount[i] = amount[j];
                    amount[j] = tempA;

                    int tempT = time[i];
                    time[i] = time[j];
                    time[j] = tempT;
                }
            }
        }

        int hours = 40;
        int i = 0;
        int sum = 0;
        while (i < amount.length) {
            hours -= time[i];
            if (hours > 0) {
                sum += amount[i];
            } else {
                hours += time[i];
                time[i] /= hours;
                sum += (amount[i] / time[i]);
                break;
            }
            i++;
        }

        System.out.println(sum);
    }
}
