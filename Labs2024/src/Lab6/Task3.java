package Lab6;

/*
You are supposed to make an automated translator, that translates from English to Macedonian.

Input: In the first line you are given a single integer N. In the following N lines you are given two words separated with a single white space.
The first one is on Macedonian and the second is on English. Use the pairs of words to create, a dictionary. After this you are given a single English word in
every line, until the word "KRAJ" is read.

Output: On the standard output you should print the Macedonian translation of the English words, using the dictionary you previously created. If you can't find
a word in the dictionary print "/".

Note: Use open buckets hash table. You are supposed to define the hash table and write the hash function by yourself.
Class name: Translator
 */

import java.util.Scanner;

class Translator {
    String mk;
    String en;

    public Translator(String mk, String en) {
        this.mk = mk;
        this.en = en;
    }
}

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        CBHT<Translator, Boolean> table = new CBHT<>(N);
        CBHT<String, String> dict = new CBHT<>(N);
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String mk = parts[0];
            String en = parts[1];

            Translator translator = new Translator(mk, en);
            table.insert(translator, true);
            dict.insert(en, mk);
        }

        while (true) {
            String word = sc.nextLine();
            if (word.equals("KRAJ")) {
                break;
            }
            SLLNode<MapEntry<String, String>> d = dict.search(word);
            if (d == null) {
                System.out.println("/");
            } else {
                System.out.println(d.element.value);
            }
        }
    }
}
