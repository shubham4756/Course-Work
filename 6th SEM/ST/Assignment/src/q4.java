import java.util.Scanner;

public class q4 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        char vowel[] = {'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'};
        int[] count = new int[10];
        while (true) {
            System.out.print("Please Enter a String : ");
            String s = sc.nextLine();
            s.trim();
            if (s.equals("quit"))
                break;
            int[] temp = new int[10];
            for (int i = 0; i < vowel.length; i++) {
                for (int idx = 0; idx < s.length(); idx++) {
                    if (s.charAt(idx) == vowel[i]) {
                        temp[i]++;
                        count[i]++;
                    }
                }
                System.out.println("Number of " + vowel[i] + "'s : " + temp[i]);
            }
            System.out.println("   ----------------------    ");
        }
        for (int i = 0; i < vowel.length; i++) {
            System.out.println("Total Number of " + vowel[i] + "'s : " + count[i]);
        }
    }
}