package Hashing.Task5;

import java.util.Scanner;

class Person {
    String name;
    String surname;
    int budget;
    String ip;
    String time;
    String city;
    int price;

    public Person(String name, String surname, int budget, String ip, String time, String city, int price) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.ip = ip;
        this.time = time;
        this.city = city;
        this.price = price;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        CBHT<Person, Boolean> table = new CBHT<>(N);
        CBHT<String, Integer> count = new CBHT<>(N);
        CBHT<String, Person> theMost = new CBHT<>(N);

        for (int i = 0; i < N; i++) {
            Person person = new Person(sc.next(), sc.next(), sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextInt());
            table.insert(person, true);

            String[] points = person.ip.split("\\.");
            String IP = points[0] + "." + points[1] + "." + points[2];

            SLLNode<MapEntry<String, Integer>> ci = count.search(IP);
            SLLNode<MapEntry<String, Person>> thm = theMost.search(IP);

            if (ci == null) {
                if (person.budget >= person.price) {
                    count.insert(IP, 1);
                    theMost.insert(IP, person);
                }
            } else {
                if (person.budget >= person.price) {
                    count.insert(IP, ci.element.value + 1);

                    if (thm == null || person.price > thm.element.value.price) {
                        theMost.insert(IP, person);
                    }
                }
            }

        }

        int M = sc.nextInt();

        for (int i = 0; i < M; i++) {
            Person person = new Person(sc.next(), sc.next(), sc.nextInt(), sc.next(), sc.next(), sc.next(), sc.nextInt());
            String[] points = person.ip.split("\\.");
            String IP = points[0] + "." + points[1] + "." + points[2];
            SLLNode<MapEntry<String, Integer>> cities = count.search(IP);
            SLLNode<MapEntry<String, Person>> nodeMost = theMost.search(IP);

            if (nodeMost != null && cities != null) {
                System.out.println("IP network: " + IP + " has the following number of users:\n" + cities.element.value);
                System.out.println("The user who spent the most from that network is:");
                System.out.println(nodeMost.element.value.name + " " + nodeMost.element.value.surname + " with salary " + nodeMost.element.value.budget + " from address " + nodeMost.element.value.ip + " who spent " + nodeMost.element.value.price);
                System.out.println();
            }
        }
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

