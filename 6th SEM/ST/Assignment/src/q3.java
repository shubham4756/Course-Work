
import java.util.Scanner;

public class q3 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Please Enter a String : ");
        String s = sc.nextLine();
        s = s.replace(' ', '&');
        System.out.println("String after replacing spaces : " + s);
    }
}