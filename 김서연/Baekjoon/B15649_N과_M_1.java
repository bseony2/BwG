package baekjoon.backtraking;

import java.util.Scanner;

/**
 *  Baekjoon 18290 NM과 K(1)
 *  - Silver 1
 *  - Backtracking, Bruteforce, Permutation
 */
public class B15649_N과_M_1 {
    static boolean[] isVisited;
    static int[] arr;
    static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        isVisited = new boolean[n + 1];
        arr = new int[n];

        permutation(0);
    }

    static void permutation(int idx) {
        if (idx == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!isVisited[i]) {
                isVisited[i] = true;
                arr[idx] = i;
                permutation(idx + 1);
                isVisited[i] = false;
            }
        }
    }
}
