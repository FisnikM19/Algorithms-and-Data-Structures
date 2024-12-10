package Lab6;

// Следните класи веќе се импортирани, не е дозволено копирање на класи овде, директно користејте ги како кога се достапни во други локални фајлови:
// The following classes are already imported, copying classes here is not allowed, use them directly as when they are available in other local files:

// CBHT, OBHT, MapEntry, SLLNode веќе се импортирани
// CBHT, OBHT, MapEntry, SLLNode are already imported
import java.util.Scanner;

// Овде креирајте ги помошните класи за клуч и вредност
// Исполнете ги барањата од текстот за toString методите
// Дополнително осигурете се дека вашата клуч класа ќе ги имплементира потребните
// hashCode и equals методи

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
    int hourSalary;

    Project(int workingTime, int hourSalary) {
        this.workingTime = workingTime;
        this.hourSalary = hourSalary;
    }

    int getTotalSalary() {
        return workingTime * hourSalary;
    }

    @Override
    public String toString() {
        return "<" + workingTime + ", " + hourSalary + ">";
    }
}

public class Solution {
    public static void main(String[] args) {
        // Креирајте ја табелата според барањата
        // Create the table as requested
        CBHT<Person, Project> table = new CBHT<>(10);
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        

        // Прочитајте ги податоците од влезот и пополнете ја табелата
        // Read the input data and fill the table

        for (int i = 0; i < N; i++) {
            String name = sc.next();
            int age = sc.nextInt();
            Person person = new Person(name, age);

            int time = sc.nextInt();
            int rate = sc.nextInt();
            Project project = new Project(time, rate);

            SLLNode<MapEntry<Person, Project>> existingOfferNode = table.search(person);
            if (existingOfferNode == null) {

                table.insert(person, project);
            } else {

                Project existingProject = existingOfferNode.element.value;
                if (project.getTotalSalary() > existingProject.getTotalSalary()) {
                    table.insert(person, project);
                }
            }
        }

        // отпечатете ја вашата табела
        // print your table
        System.out.println(table);
    }
}


