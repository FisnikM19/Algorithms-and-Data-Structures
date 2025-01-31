package Task1;

import java.util.Scanner;

public class Task1 {

    private static void print(int[] arr, int n) {
        System.out.print("{");
        for (int i = 0; i < n - 1; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.print(arr[n - 1] + "}");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        print(arr, n);
        System.out.println();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
        }

        double average = (double) sum / n;
        int[] newArr = new int[n];
        int j = 0, count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] >= average) {
                newArr[j++] = arr[i];
            } else {
                count++;
            }
        }

        int size = n - count;

        print(newArr, size);
    }
}
