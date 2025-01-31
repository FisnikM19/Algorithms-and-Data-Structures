package Task1;
// We are given N digits. Write an algorithm that composes the largest possible number from those digits.
//Input: The first number in the input is the number of digits N, then in the next line are the given digits.
//Output: The maximum possible number containing those digits

// Input:
// 5
// 1 2 3 4 5
// Output:
// 54321

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        mergeSort(arr, 0, arr.length - 1);

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i]);
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = (l + r) / 2;
            mergeSort(arr, l, mid);
            mergeSort(arr, mid + 1, r);

            merge(arr, l, mid, r);
        }
    }

    private static void merge(int[] arr, int l, int mid, int r) {
        int sizeL = mid - l + 1;
        int sizeR = r - mid;

        int[] lArray = new int[sizeL];
        int[] rArray = new int[sizeR];

        for (int i = 0; i < sizeL; i++) {
            lArray[i] = arr[i + l];
        }
        for (int i = 0; i < sizeR; i++) {
            rArray[i] = arr[i + mid + 1];
        }

        int i = 0, j = 0, k = l;

        while (i < sizeL && j < sizeR) {
            if (lArray[i] >= rArray[j]) {
                arr[k] = lArray[i];
                i++;
            } else {
                arr[k] = rArray[j];
                j++;
            }
            k++;
        }

        while (i < sizeL) {
            arr[k] = lArray[i];
            i++;
            k++;
        }
        while (j < sizeR) {
            arr[k] = rArray[j];
            j++;
            k++;
        }
    }
}
