
import java.util.Scanner;

public class q1 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please Enter base radius of the cone : ");
        double r = sc.nextDouble();
        System.out.println("Please Enter height  of the cone : ");
        double h = sc.nextDouble();
        double area = (Math.PI * r * r * h) / 3;
        System.out.println("Total Area of Cone : " + area);
    }
}