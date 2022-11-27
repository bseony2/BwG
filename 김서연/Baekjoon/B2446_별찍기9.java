package baekjoon_automata_basic;

import java.util.Scanner;

public class B2446_별찍기9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = 2*N - 1; i > 0; i--) {
            if (i < 2*N && i >= N) {
                for (int j = 1; j < 2*N - i ; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < i - (2*N - i- 1); j++) {
                    System.out.print("*");
                }
                System.out.println();
            }

            if (i > 0 && i < N) {
                for (int j = i - 1; j > 0; j--) {
                    System.out.print(" ");
                }

                for (int j = 0; j < (N - i) * 2 + 1; j++) {
                    System.out.print("*");
                }

                System.out.println();
            }
        }
    }
}
