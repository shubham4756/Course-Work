
import java.util.Scanner;

public class q5 {
    static Scanner sc = new Scanner(System.in);
    final static int NUMBER_OF_YEARS = 20; // Number of years to display

    public static double futureInvestmentValue(
            double investmentAmount, double rate, int years) {
        return investmentAmount * Math.pow(1 + rate, years * 12);
    }

    public static void main(String[] args) {
        System.out.print("\nThe amount invested: ");
        double amount = sc.nextDouble();
        System.out.print("interest rate: ");
        double rate = sc.nextDouble();
        rate /= 100;

        System.out.println("Years     Future Value");
        for (int years = 1; years <= NUMBER_OF_YEARS; years++) {
            System.out.printf("%-10d", years);
            System.out.printf("%11.2f\n", futureInvestmentValue(amount, rate, years));
        }
    }
}