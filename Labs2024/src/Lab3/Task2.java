package Lab3;
// We are given N tasks with estimated completion time and the amount we can earn from that task.
// Write an algorithm that finds the maximum earnings we can have for 40 hours. Note that we can have a partial earning from a partially completed task.
//
// Input: The first number in the input is the number of tasks N,
// then in the next N lines are the time needed for the task and the amount of money we can earn from it.
// Output: The maximum amount we can earn in 40 hours



import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] time = new int[n];
        int[] profit = new int[n];

        for (int i = 0; i < n; i++) {
            time[i] = sc.nextInt();
            profit[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (profit[i] * 1.0 / time[i]  < profit[j] * 1.0 / time[j]) {
                    int tempP = profit[i];
                    int tempT = time[i];
                    profit[i] = profit[j];
                    time[i] = time[j];
                    profit[j] = tempP;
                    time[j] = tempT;
                }
            }
        }
        int sum = 0;
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (sum + time[i] > 40) {
                double a = 1 - (sum + time[i] - 40) * 1.0 / time[i];
                result += a * profit[i];
                break;
            } else {
                sum += time[i];
                result += profit[i];
            }
        }

        System.out.println(result);
    }
}
/*
Input:
3
10 60
20 100
30 120
Output: 200

we have 40 hours max ->
10 hours + 60 profit -> 40 - 10 -> 30 hours left
20 hours + 100 profit -> 160 profit, 30 - 20 -> 10 hours left
30 hours + 120 profit -> since we have 10 hours left -> ((120 / 30 = 4) * 10) = 40 + 160 -> 200 profit

Input:
1
10 60
Output: 60
*/
