package baekjoon_automata_basic;

import java.util.Scanner;

public class B2443_별찍기6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        for (int i = N; i > 0; i--) {
            for (int j = 0; j < N - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2*i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
