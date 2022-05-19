
public class Assignment2 {
    public static void main(String[] args) {
        MarvelEnterprise A = new MarvelEnterprise(350, 35);
        MarvelEnterprise B = new MarvelEnterprise(420, 47);
        MarvelEnterprise C = new MarvelEnterprise(510, 73);
        A.totalPay();
        B.totalPay();
        C.totalPay();
    }

    private static class MarvelEnterprise {
        private final double basePay, hours;

        MarvelEnterprise(int pay, int h) {
            this.basePay = pay;
            this.hours = h;
        }

        void totalPay() {
            if (this.hours > 60) {
                System.out.println("Error: the number of hours is greater than 60,");
            } else if (this.basePay < 400) {
                System.out.println("Error: The Indian Department of Labor requires that hourly employees be paid at least 400Rs.");
            } else {
                double tot = basePay * this.hours + (basePay * (this.hours - 40)) / 2;
                System.out.println("Total Pay: " + tot);
            }
        }
    }
}