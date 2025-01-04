package Lab10.Task3;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InputMismatchException{
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Map<String, Integer> map = new HashMap<>();

        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            String s = sc.next();
            int value = sc.nextInt();
            map.put(s, value);

            arr[i] = s;
        }

        int M = sc.nextInt();

        String[] source = new String[M];
        String[] destination = new String[M];

        for (int i = 0; i < M; i++) {
            source[i] = sc.next();
            destination[i] = sc.next();
        }

        int max = 0;

        for (int i = 0; i < M; i++) {
            int sum = 0;

            for (int j = i; j < M; j++) {
                int value1 = map.get(source[i]);
                int value2 = map.get(destination[i]);

                if (i == j) {
                    sum += value1 + value2;
                }

                if (destination[j].equals(source[i])) {
                    int value3 = map.get(source[j]);
                    sum += value3;
                }
            }

            max = Math.max(sum, max);
        }

        if (M != 0) {
            System.out.println(max);
        } else {
            for (int i = 0; i < N; i++) {
                int value = map.get(arr[i]);

                max = Math.max(value, max);
            }
            System.out.println(max);
        }

    }
}
