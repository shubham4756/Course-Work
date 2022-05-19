
import java.util.Scanner;

public class q8 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Please Enter Input : ");
        String str = sc.nextLine();
        char c = str.charAt(0);
        if (str.length() != 1) {
            System.out.println("Invalid Input");
        } else if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
            char vowel[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
            boolean check = false;
            for (int i = 0; i < vowel.length; i++) {
                if (vowel[i] == c)
                    check = true;
            }
            if (check) {
                System.out.println("Vowel");
            } else {
                System.out.println("Consonant");
            }
        } else {
            System.out.println("Input is not a letter");
        }
    }
}