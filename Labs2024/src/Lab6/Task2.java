package Lab6;

/*

You are supposed to simulate a log-in system.
The user enters a username and password. If such a user exists in the system, you are supposed to print "Najaven" to the standard output;
otherwise, print "Nenajaven" and give the user another prompt for credentials.
This stops when the user enters a username and password that match a certain user credential in the system.

Input:
In the first line of the input, you are given a single integer N. In the following N lines, you are given usernames and passwords separated with one white space.
These are the users that exist in the system.
After this, you are supposed to read usernames and passwords from the standard input until a user can be successfully logged in.

Output:
Print "Nenajaven" to the standard output for every failed log-in try until we get a successful log-in. Then you have to print "Najaven".

Example:
Input:
3
ana banana
pero zdero
trpe trpi
ana ana
ana bannana
trpe trpi
KRAJ

Output:
Nenajaven
Nenajaven
Najaven
 */

import java.util.Scanner;

class Lozinki {
    String username;
    String password;

    public Lozinki(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "<" + username + ", " + password + ">";
    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result * password.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Lozinki lozinki = (Lozinki) obj;
        return username.equals(lozinki.username) && password.equals(lozinki.password);
    }
}

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        
        CBHT<Lozinki, Boolean> hashTable = new CBHT<>(N);

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String username = parts[0];
            String password = parts[1];

            Lozinki lozinki = new Lozinki(username, password);
            hashTable.insert(lozinki, true);
        }

        while (true) {
            String line = sc.nextLine();
            if (line.equals("KRAJ")) {
                break;
            }

            String[] parts = line.split(" ");
            String username = parts[0];
            String password = parts[1];

            Lozinki loginAttempt = new Lozinki(username, password);
            if (hashTable.search(loginAttempt) != null) {
                System.out.println("Najaven");
            } else {
                System.out.println("Nenajavane");
            }
        }
    }
}
