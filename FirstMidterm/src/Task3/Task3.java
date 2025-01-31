package Task3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // adults
        int M = Integer.parseInt(br.readLine()); // children

        br.close();

        // Vasiot kod tuka

        int min, max;
        int price = 100;

        if (N <= M) {
            min = (price * N) + ((M - N) * price);
            max = (price * N) + ((M - 1) * price);
        } else {
            min = (price * N);
            if (M != 0) {
                max = (price * N) + (price * M) - price; // -price because for one child is free
            } else {
                max = price * N;
            }
        }

        System.out.println(min);
        System.out.println(max);
    }
}
// There were N adults and M children that wanted to travel from the bus station in FinTown to
// the neighbouring city MinTown.
// The price of one ticket is 100 den. If an adult wants to travel with K children, he would need
// to pay one ticket for him and K-1 tickets for the children (the ticket for one of the children is
// free). The adults can also travel by themselves, in which case they only pay one ticket.
// Additionally we know that the children can't travel without being accompanied by an adult.
// In the first row the number N is given, and then in the second row the number M. You need
// to calculate the minimum and the maximum value in den. that the passengers can pay for
// their tickets, and print them in two separate lines. There will be at least one adult in the bus.
// Input:
// 4
// 10
// Output:
// 1000
// 1300
// Input:
// 80236
// 14868
// Output:
// 8023600
// 9510300