import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int overallHours = 40;
        double overallAmount = 0;
        while(n>0){
            int hours = sc.nextInt();
            int amount = sc.nextInt();
            if(hours <= overallHours){
                overallAmount += amount;
                overallHours -= hours;
            }else{
                overallAmount += amount * ((double) overallHours / hours);
                overallHours = 0;
            }
            n--;
        }
        System.out.println((int)overallAmount);

    }
}