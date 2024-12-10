package Lab1;

import java.util.Scanner;
// For a given array with N integers, all the elements that are lower than the average of the whole array need to be deleted.
// For example for the array 1, 2, 3, 4, 5 the average is (1 + 2 + 3 + 4 + 5) / 5 =
// 15 / 5 = 3 which means that the elements 1 and 2 should be deleted, so the array after the transformation will be 3, 4, 5.
// Input:            Output:
// 5                 {1, 2, 3, 4, 5}
// 1 2 3 4 5         {3, 4, 5}
public class Task1 {

    public static double Average(int[] nums, int size) {
        double sum = 0.0;
        for (int i = 0; i < size; i++) {
            sum += nums[i];
        }

        return sum / size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("{");
        for (int i = 0; i < n-1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[n-1] + "}");
        System.out.println();

        double average = Average(arr, n);

        int[] newArray = new int[n];
        int j = 0;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] >= average) {
                newArray[j++] = arr[i];
            } else {
                count++;
            }
        }

        int newSize = n - count;

        System.out.print("{");
        for (int i = 0; i < newSize - 1; i++) {
            System.out.print(newArray[i] + ",");
        }
        System.out.print(newArray[newSize - 1] + "}");

    }
}
