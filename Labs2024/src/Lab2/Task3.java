package Lab2;
// You are given a doubly-linked list of integers. Additionally, there is one more integer M and a natural number k.
// You need to find the first occurrence of M in the list and move that node k times to the right.

// Input: The first number in the input is the number of integers in the list - N, then in the next line the elements are given, separated by spaces.
// Then, in the last two lines, the integer M and the natural number k are given.

// Output: The list before and after the transformation.

// Input:
// 5
// 1 2 3 4 5
// 3
// 2
//
// Output: 1<->2<->3<->4<->5
//         1<->2<->4<->5<->3

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DLL<Integer> list = new DLL<>();

        for(int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        int M = sc.nextInt();
        int k = sc.nextInt();

        System.out.println(list);

        DLLNode<Integer> node = list.getFirst();
        DLLNode<Integer> nodeM = list.find(M);
        boolean flag = true;

        while (node != null && flag) {
            if (node.succ == nodeM) {
                list.delete(nodeM);
                for (int i = 0; i < k; i++) {
                    if (node.succ != null) {
                        node = node.succ;
                    } else {
                        node = list.getFirst();
                        k--;
                    }
                }
                list.insertAfter(nodeM.element, node);
                flag = false;
            } else {
                node = node.succ;
            }

        }

        System.out.println(list);
    }
}
