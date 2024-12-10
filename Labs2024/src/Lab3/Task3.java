/* We are given N scheduled meetings. Write an algorithm that finds the minimum number of meeting rooms needed to schedule those meetings.

Input: The first number in the input is the number of meetings N, then in the next N lines are the start and end time for each of the meetings.

Output: The minimum number of needed meeting rooms to schedule the meetings.

For example:
Input
5
1 2
1 2
5 10
11 14
15 20
Result
2

Input
5
1 2
2 3
3 4
4 5
5 6
Result
2

*/

package Lab3;
import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        int[] starts = new int[N];
        int[] ends = new int[N];

        for (int i = 0; i < N; i++) {
            starts[i] = sc.nextInt();
            ends[i] = sc.nextInt();
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int start = 0;
        int end = 0;
        int num = 0;

        while (start < N) {
            if (starts[start] <= ends[end]) {
                num++;
                start++;
            } else {
                end++;
                start++;
            }
        }

        System.out.println(num);


    }
}