
import java.util.Scanner;

public class q2 {
    static Scanner sc = new Scanner(System.in);

    static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.print("Enter First Number: ");
        int a = sc.nextInt();
        System.out.print("Enter Second Number: ");
        int b = sc.nextInt();
        int gc = gcd(a, b);
        int lcm = (a * b) / gc;
        System.out.println("Lcm of " + a + " and " + b + " is : " + lcm);
    }
}
