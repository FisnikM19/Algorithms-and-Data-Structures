package One_Dimensional_Data_Structures.Task3;

import java.util.NoSuchElementException;
import java.util.Scanner;

class Student {
    String name;
    int type1;  // Science
    int type2;  // Science Fiction
    int type3;  // History

    public Student(String name, int type1, int type2, int type3) {
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();

        LinkedQueue<Student> q1 = new LinkedQueue<>();
        LinkedQueue<Student> q2 = new LinkedQueue<>();
        LinkedQueue<Student> q3 = new LinkedQueue<>();
        LinkedQueue<String> output = new LinkedQueue<>();

        for (int i = 0; i < n; i++) {
            Student s = new Student(sc.nextLine(), sc.nextInt(), sc.nextInt(), sc.nextInt());
            sc.nextLine();

            if (s.type1 == 1) {
                q1.enqueue(s);
            } else if (s.type2 == 1) {
                q2.enqueue(s);
            } else if (s.type3 == 1) {
                q3.enqueue(s);
            }
        }

        while (!q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty()) {
            int count = 0;
            while (!q1.isEmpty() && count < 2) {
                Student s = q1.dequeue();

                if (s.type2 == 1) {
                    q2.enqueue(s);
                } else if (s.type3 == 1) {
                    q3.enqueue(s);
                } else {
                    output.enqueue(s.name);
                }
                count++;
            }

            if (!q2.isEmpty()) {
                Student s = q2.dequeue();

                if (s.type3 == 1) {
                    q3.enqueue(s);
                } else {
                    output.enqueue(s.name);
                }
            }
            count = 0;
            while (!q3.isEmpty() && count < 2) {
                Student s = q3.dequeue();
                output.enqueue(s.name);
                count++;
            }
        }

        // Print output
        while (!output.isEmpty()) {
            System.out.println(output.dequeue());
        }
    }
}
class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}


class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}


interface Queue<E> {
    // Elementi na redicata se objekti od proizvolen tip.
    // Metodi za pristap:
    public boolean isEmpty();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size();
    // Ja vrakja dolzinata na redicata.

    public E peek();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear();
    // Ja prazni redicata.

    public void enqueue(E x);
    // Go dodava x na kraj od redicata.

    public E dequeue();
    // Go otstranuva i vrakja pochetniot element na redicata.
}