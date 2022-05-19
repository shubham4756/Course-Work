
import java.util.Scanner;

public class q9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 3;
        System.out.println("Enter Three numbers: ");
        int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
        if (a <= b && b <= c) {
            System.out.println("increasing");
        } else if (a >= b && b >= c) {
            System.out.println("decreasing");
        } else {
            System.out.println("Neither increasing or decreasing order");
        }
    }
}