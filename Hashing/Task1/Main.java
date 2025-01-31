package Hashing.Task1;

import java.util.Scanner;

// Create the helper classes for key and value here
// Fulfill the requirements from the text for the toString methods
// Additionally, make sure that your key class will implement the required
// hashCode and equals methods
class Person {
    // поставете ги потребните полиња овде
    // declare the required fields here
    String name;
    int age;

    // имплементирајте соодветен конструктор
    // implement the constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        // имплементирајте го toString методот според барањето во текстот
        // implement the toString method as requested in the text
        return "<" + name + ", " + age + ">";
    }


    // имплементирајте ги следните два методи за да работи табелата правилно
    // implement the following two methods to make the table work properly
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return age == person.age && name.equals(person.name);
    }
    @Override
    public int hashCode() {
        return age * (int)name.charAt(0);
    }
}

class Project {
    int workingTime;
    int salary;

    Project(int workingTime, int salary) {
        this.workingTime = workingTime;
        this.salary = salary;
    }


    @Override
    public String toString() {
        return "<" + workingTime + ", " + salary + ">";
    }

    public int getTotalSalary() {
        return workingTime * salary;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // Креирајте ја табелата според барањата
        // Create the table as requested
        CBHT<Person, Project> table = new CBHT<>(10);

        // Прочитајте ги податоците од влезот и пополнете ја табелата
        // Read the input data and fill the table
        for (int i = 0; i < N; i++) {
            Person person = new Person(sc.next(), sc.nextInt());
            Project project = new Project(sc.nextInt(), sc.nextInt());

            SLLNode<MapEntry<Person, Project>> node = table.search(person);
            if (node == null) {
                table.insert(person, project);
            } else {
                Project nodeProject = node.element.value;
                if (project.getTotalSalary() > nodeProject.getTotalSalary()) {
                    table.insert(person, project);
                }
            }
        }

        // отпечатете ја вашата табела
        // print your table
        System.out.println(table);
    }
}
class CBHT<K, E> {
    // An object of class CBHT is a closed-bucket hash table, containing
    // entries of class MapEntry.
    private SLLNode<MapEntry<K, E>>[] buckets;

    @SuppressWarnings("unchecked")
    public CBHT(int m) {
        // Construct an empty CBHT with m buckets.
        buckets = (SLLNode<MapEntry<K, E>>[]) new SLLNode[m];
    }

    private int hash(K key) {
        // Translate key to an index of the array buckets.
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public SLLNode<MapEntry<K, E>> search(K targetKey) {
        // Find which if any node of this CBHT contains an entry whose key is equal to targetKey.
        // Return a link to that node (or null if there is none).
        int b = hash(targetKey);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(targetKey)) return currNode;
            else currNode = currNode.succ;
        }
        return null;
    }

    public void insert(K key, E val) {
        // Insert the entry <key, val> into this CBHT.
        // If entry with same key exists, overwrite it.
        MapEntry<K, E> newEntry = new MapEntry<>(key, val);
        int b = hash(key);
        SLLNode<MapEntry<K, E>> currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                // Make newEntry replace the existing entry ...
                currNode.element = newEntry;
                return;
            } else currNode = currNode.succ;
        }
        // Insert newEntry at the front of the SLL in bucket b ...
        buckets[b] = new SLLNode<>(newEntry, buckets[b]);
    }

    public void delete(K key) {
        // Delete the entry (if any) whose key is equal to key from this CBHT.
        int b = hash(key);
        SLLNode<MapEntry<K, E>> predNode = null, currNode = buckets[b];
        while (currNode != null) {
            MapEntry<K, E> currEntry = currNode.element;
            if (currEntry.key.equals(key)) {
                if (predNode == null) buckets[b] = currNode.succ;
                else predNode.succ = currNode.succ;
                return;
            } else {
                predNode = currNode;
                currNode = currNode.succ;
            }
        }
    }

    public String toString() {
        String temp = "";
        for (int i = 0; i < buckets.length; i++) {
            temp += i + ":";
            SLLNode<MapEntry<K, E>> curr = buckets[i];
            while (curr != null) {
                temp += curr.element.toString() + " ";
                curr = curr.succ;
            }
            temp += "\n";
        }
        return temp;
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

class MapEntry<K, E> {
    // Each MapEntry object is a pair consisting of a key
    // and a value (an arbitrary object).
    K key;
    E value;

    public MapEntry(K key, E val) {
        this.key = key;
        this.value = val;
    }

    public String toString() {
        return "<" + key + "," + value + ">";
    }
}

