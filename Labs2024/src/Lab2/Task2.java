package Lab2;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        DLL<Integer> list = new DLL<>();
        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        int k = sc.nextInt();
        System.out.println(list);

        DLLNode<Integer> node = list.getFirst();

        for (int i = 0; i < k; i++) {
            int temp = node.element;
            list.deleteFirst();
            list.insertLast(temp);
            node = list.getFirst();
        }

        System.out.println(list);
    }
}
