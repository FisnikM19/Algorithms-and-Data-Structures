package Task9;
import java.util.Scanner;
import static java.util.Arrays.sort;


public class Task9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int element = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        sort(arr);
        int l = 0;
        int r = n;
        boolean flag = false;

        while (l < r) {
            int mid = (l + r) / 2;

            if (arr[mid] == element) {
                System.out.println(mid);
                flag = true;
                break;
            } else if (arr[mid] < element) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        if (!flag) {
            System.out.println("Ne postoi");
        }

    }
}
