/*
At the input of this task you will be given rows with the following format:
First_name Last_name budget ip_address time city price
Example
Jovan Todorov 1000 10.73.112.200 15:30 Bitola 760
Which means that the person with First name and Last name Jovan Todorov, has a budget of 1000 denars, has an IP address with network 10.73.112 and host number 200, and joined at 15:30 to buy a ticked to Bitola which costs 760 denars.

You will be given N such rows, followed by an empty row and M more rows of the same format, which we will use for testing.

From the test row you need to extract the city and then answer the following question with this city:

Of the N people at the input, from the ones who buy a ticket to the same city
how many of them had enough budget to buy the ticket; and
of these, which one paid the highest amount?

(if there are more with the same highest amount then which one is the first one in the input?) (there will always be at least one such)


You will need to do this for all M rows for testing!

Recommendation, use OBHT and/or CBHT.
*/

package Lab6;

import java.util.Objects;
import java.util.Scanner;

class Persons{
    String name;
    String surname;
    Integer budget;
    String ip;
    String time;
    String city;
    Integer price;

    public Persons(String name, String surname, Integer budget, String ip, String time, String city, Integer price) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.ip = ip;
        this.time = time;
        this.city = city;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persons person = (Persons) o;
        return Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(budget, person.budget) && Objects.equals(ip, person.ip) && Objects.equals(time, person.time) && Objects.equals(city, person.city) && Objects.equals(price, person.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, budget, ip, time, city, price);
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getBudget() {
        return budget;
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return time;
    }

    public String getCity() {
        return city;
    }

    public Integer getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    @Override
    public String toString() {
        return name+" " + surname + " with salary " + budget + " from address "+ ip +" who spent "+ price;
    }
}
public class Solution2 {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        scanner.nextLine();


        CBHT<Persons, Boolean> hashTable = new CBHT<>(n);
        CBHT<String, Integer> c=new CBHT<>(n);
        CBHT<String, Persons> themost=new CBHT<>(n);

        CBHT<String,String>dict=new CBHT<>(n);
        for (int i = 0; i < n; i++) {
            String name=scanner.next();
            String surname=scanner.next();
            Integer budget= scanner.nextInt();
            String ip=scanner.next();
            String time=scanner.next();
            String city=scanner.next();
            Integer price=scanner.nextInt();
            scanner.nextLine();
            Persons p=new Persons(name,surname,budget,ip,time,city,price);
            hashTable.insert(p,true);
            SLLNode<MapEntry<String,Integer>> ci=c.search(city);
            SLLNode<MapEntry<String,Persons>> tm=themost.search(city);
            if(ci==null){
                if(budget>=price)
                    c.insert(city,1);
            }
            else{
                // c.insert(city,ci.element.value+1);
                if(budget>=price)
                    ci.element.value=ci.element.value+1;
            }
            if(tm==null){
                if(budget>=price)
                    themost.insert(city,p);
            }
            else{
                // c.insert(city,ci.element.value+1);
                if(budget>=price) {
                    if(price>tm.element.value.getPrice())
                        themost.insert(city,p);

                }
            }

        }
        scanner.nextLine();
        int m=scanner.nextInt();


        for(int i=0;i<m;i++){
            String name=scanner.next();
            String surname=scanner.next();
            Integer budget= scanner.nextInt();
            String ip=scanner.next();
            String time=scanner.next();
            String city=scanner.next();
            Integer price=scanner.nextInt();

            Persons p=new Persons(name,surname,budget,ip,time,city,price);
            SLLNode<MapEntry<Persons, Boolean>>test=hashTable.search(p);
            SLLNode<MapEntry<String,Integer>> ci=c.search(city);
            SLLNode<MapEntry<String,Persons>> tm=themost.search(city);
            System.out.println("City: "+city+ " has the following number of customers:"+"\n"+ci.element.value);
            System.out.println("The user who spent the most purchasing for that city is: ");
            System.out.println(tm.element.value.name+" " + tm.element.value.surname + " with salary " + tm.element.value.budget + " from address "+ tm.element.value.ip +" who spent "+ tm.element.value.price+"\n");
        }

    }
}