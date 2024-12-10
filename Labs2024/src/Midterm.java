import java.util.Scanner;

public class Midterm {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] names = new String[n];
        int[] req = new int[n];
        for (int i = 0; i < n; i++) {
            names[i] = sc.next();
            req[i] = sc.nextInt();
        }

        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                if (req[i] > req[j]) {
                    int temp = req[j];
                    req[j] = req[i];
                    req[i] = temp;

                    String tempN = names[j];
                    names[j] = names[i];
                    names[i] = tempN;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(names[i] + " " + req[i]);
            System.out.println();
        }

    }
}
