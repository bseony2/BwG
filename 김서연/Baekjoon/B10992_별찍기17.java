package baekjoon_automata_basic;

import java.util.Scanner;

public class B10992_별찍기17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        if (n == 1) {
            System.out.println("*");
        } else {
            for (int i = 1; i <= n - 1; i++) { // 마지막줄 제외
                for (int j = 1; j <= n - i; j++) {
                    System.out.print(" ");
                }
                System.out.print("*");

                for (int k = 1; k <= 2 * (i - 1) - 1; k++ ) {
                    System.out.print(" ");
                }

                if (i >= 2) {
                    System.out.print("*");
                }

                System.out.println();
            }

            for (int i = 1; i <= 2 * n - 1; i++) {
                System.out.print("*");
            }
        }
    }
}
