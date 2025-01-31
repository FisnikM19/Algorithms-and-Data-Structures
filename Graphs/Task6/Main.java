package Graphs.Task6;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Map<String, Integer> map = new HashMap<>();
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            String element = sc.next();
            int value = sc.nextInt();
            map.put(element, value);
            
            arr[i] = element;
        }

        int M = sc.nextInt();
        String[] source = new String[M];
        String[] destination = new String[M];

        for (int i = 0; i < M; i++) {
            source[i] = sc.next();
            destination[i] = sc.next();
        }

        if (M != 0) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < M; i++) {
                int sum = 0;
                for (int j = i; j < M; j++) {
                    if (i == j) {
                        sum += map.get(source[i]) + map.get(destination[i]);
                    }

                    if (source[i].equals(destination[j])) {
                        sum += map.get(source[j]);
                    }
                }
                max = Math.max(max, sum);
            }

            System.out.println(max);
        } else {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < N; i++) {
                max = Math.max(max, map.get(arr[i]));
            }

            System.out.println(max);
        }
    }
}
