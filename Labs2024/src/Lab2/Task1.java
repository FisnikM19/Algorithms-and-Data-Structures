package Lab2;

import java.util.Scanner;

public class Task1 {
    //TODO For a given doubly-linked list with N integers, you need to find the number of elements such that the average
    // of all elements before it is bigger than the average of all elements after it in the list.
    // Input: The first number in the input is the number of integers in the list N, then in the next line the elements are given, separated by spaces.
    // Output: The number of elements that satisfy the condition.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        DLL<Integer> arr = new DLL<Integer>();
        for (int i = 0; i < n; i++) {
            arr.insertLast(sc.nextInt());
        }

//        System.out.println(arr);
        DLLNode<Integer> current = arr.getFirst();
        DLLNode<Integer> forward = arr.getFirst();
        DLLNode<Integer> backward = arr.getFirst();
        double sumLeft = 0, sumRight = 0;
        int countLeft = 0, countRight = 0;
        int sol = 0;
        current = current.succ; // let's go to the element 2 to compare left side and right side
        while (current != null) {
            forward = current;
            while (forward.succ != null) {
                sumRight += forward.succ.element;
                forward = forward.succ;
                countRight++;
            }
            while (backward != null) {
                sumLeft += backward.element;
                backward = backward.pred;
                countLeft++;
            }
            if (sumLeft / countLeft > sumRight / countRight) {
                sol++;
            }
            backward = current;
            current = current.succ;
            sumLeft = 0;
            sumRight = 0;
            countRight = 0;
            countLeft = 0;
        }

        System.out.println(sol);

    }
}

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> pred, succ;

    public DLLNode(E element, DLLNode<E> pred, DLLNode<E> succ) {
        this.element = element;
        this.pred = pred;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}

class DLL<E> {
    private DLLNode<E> first, last;

    public DLL() {
        // Construct an empty DLL
        this.first = null;
        this.last = null;
    }

    public void insertFirst(E o) {
        DLLNode<E> ins = new DLLNode<>(o, null, first);
        if (first == null) {
            last = ins;
        } else {
            first.pred = ins;
        }

        first = ins;
    }

    public void insertLast(E o) {
        if (first == null) {
            insertFirst(o);
        } else {
            DLLNode<E> ins = new DLLNode<>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }

    public void insertAfter(E o, DLLNode<E> after) {
        if (after == last) {
            insertLast(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<>(o, after, after.succ);
        after.succ.pred = ins;
        after.succ = ins;
    }

    public void insertBefore(E o, DLLNode<E> before) {
        if (before == first) {
            insertFirst(o);
            return;
        }
        DLLNode<E> ins = new DLLNode<>(o, before.pred, before);
        before.pred.succ = ins;
        before.pred = ins;
    }

    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> temp = first;
            first = first.succ;
            if (first != null) {
                first.pred = null;
            } else {
                last = null;
            }
            return temp.element;
        } else {
            return null;
        }
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null) {
                return deleteFirst();
            } else {
                DLLNode<E> temp = last;
                last = last.pred;
                last.succ = null;

                return temp.element;
            }
        } else {
            return null;
        }
    }

    public E delete(DLLNode<E> node) {
        if (node == first) {
            return deleteFirst();
        }
        if (node == last) {
            return deleteLast();
        }
        node.pred.succ = node.succ;
        node.succ.pred = node.pred;

        return node.element;
    }

    public DLLNode<E> find(E o) {
        if (first != null) {
            DLLNode<E> temp = first;
            while (!temp.element.equals(o) && temp.succ != null) {
                temp = temp.succ;
            }
            if (temp.element.equals(o)) {
                return temp;
            } else {
                System.out.println("The element does not exist in the list!");
            }
        } else {
            System.out.println("The list is empty!");
        }

        return null;
    }

    public void deleteList() {
        first = null;
        last = null;
    }

    public int getSize() {
        int listSize = 0;
        DLLNode<E> temp = first;
        while (temp != null) {
            listSize++;
            temp = temp.succ;
        }

        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            DLLNode<E> temp = first;
            ret += temp.toString();
            while (temp.succ != null) {
                temp = temp.succ;
                ret += "<->" + temp.toString();
            }
        } else {
            ret = "Empty list!";
        }

        return ret;
    }

    public String toStringReverse() {
        String ret = new String();
        if (last != null) {
            DLLNode<E> temp = last;
            ret += temp.toString();
            while (temp.pred != null) {
                temp = temp.pred;
                ret += "<->" + temp.toString();
            }
        } else {
            ret = "Empty list!";
        }

        return ret;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
    }
}

