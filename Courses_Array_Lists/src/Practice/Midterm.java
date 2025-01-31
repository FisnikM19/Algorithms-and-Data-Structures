package Practice;
// Given a single linked list of integers, you should transform it so that it is a zig-zag sequence. That means that after every positive element a negative element should follow, and vice versa.
// Additionally, the list shouldn't contain any 0's. This transformation needs to be made by iterating the list and doing the following for each pair of consecutive elements:

// - If both elements are positive, delete the second one.
// - If both elements are negative, insert a new element between them, containing the absolute value of the first one.
// - If any of the elements is 0, it should be deleted (if both elements are 0, both should be deleted).
//
// Note 1: An empty list or a list containing of only 1 element is a zig-zag sequence by itself.
// Note 2: You shouldn't make any changes in the main function, but only in void makeZigZag(SLL<Integer> list).
//
// Input: In the first line the number of elements in the list is given, and then in the next line the elements themselves, separated by spaces.
// Output: First the original list is printed, then the transformed zig-zag list.
//
// Example:
// Input:
// 8
// 4 -3 -6 0 7 7 -2 5
//
// Output:
// 4 -> -3 -> -6 -> 0 -> 7 -> 7 -> -2 -> 5
// 4 -> -3 -> 3 -> -6 -> 7 -> -2 -> 5

import java.util.Scanner;

public class Midterm {
    private static void makeZigZag(SLL<Integer> list) {
        SLLNode<Integer> tmp1 = list.getFirst();

        while (tmp1 != null && tmp1.succ != null) {
            SLLNode<Integer> tmp2 = tmp1.succ;

            if (tmp1.element > 0 && tmp2.element < 0 || tmp1.element < 0 && tmp2.element > 0) {
                // Correct zig-zag pattern, move to the next pair
                tmp1 = tmp1.succ;
            } else if (tmp1.element < 0 && tmp2.element < 0) {
                // Both are negative, insert absolute value of the first element between them
                SLLNode<Integer> newNode = new SLLNode<>(Math.abs(tmp1.element), tmp2);
                tmp1.succ = newNode;
                tmp1 = tmp2;
            } else if (tmp1.element > 0 && tmp2.element > 0) {
                // Both are positive, delete the second element
                list.delete(tmp2);
            } else if (tmp1.element == 0) {
                // Delete zero at tmp1
                list.delete(tmp1);
                tmp1 = list.getFirst();  // Start from the beginning after a deletion
            } else if (tmp2.element == 0) {
                // Delete zero at tmp2
                tmp1.succ = tmp2.succ;
            } else {
                tmp1 = tmp1.succ;
            }
        }

        System.out.println(list);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        SLL<Integer> list = new SLL<>();

        for (int i = 0; i < n; i++) {
            list.insertLast(sc.nextInt());
        }

        System.out.println(list);

        makeZigZag(list);
    }
}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
//            MKD version
//            ret = "Prazna lista!!!";
            ret = "Empty list!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
//            MKD version
//            System.out.println("Dadenot jazol e null");
            System.out.println("Given node is null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
                return null;
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
//                MKD version
//                System.out.println("Elementot ne postoi vo listata");
                System.out.println("Element does not exist in the list");
            }
        } else {
//            MKD version
//            System.out.println("Listata e prazna");
            System.out.println("The list is empty");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}