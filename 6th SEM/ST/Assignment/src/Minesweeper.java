
import java.util.Scanner;

public class Minesweeper {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Please Enter M: ");
        int m = sc.nextInt();
        System.out.print("Please Enter N: ");
        int n = sc.nextInt();
        System.out.print("Please Enter P: ");
        double p = sc.nextDouble();

        boolean[][] matrix = new boolean[m + 2][n + 2];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                matrix[i][j] = (Math.random() < p);
                if (matrix[i][j]) System.out.print("* ");
                else System.out.print("- ");
            }
            System.out.println();
        }

        int[][] res = new int[m + 2][n + 2];
        int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};
        int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; k < 8; k++)
                    if (matrix[i + dx[k]][j + dy[k]])
                        res[i][j]++;
            }
        }

        System.out.println("--------------------------------");
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i][j])
                    System.out.print("* ");
                else
                    System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }
}