package baekjoon.backtraking;

import java.util.Scanner;

public class B15650_N과_M_2 {
    static boolean[] isVisited;
    static int[] arr;
    static int n, m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        isVisited = new boolean[n + 1];
        arr = new int[m];

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

                for (int j = i + 1; j <= n; j++) {
                    isVisited[j] = false;
                }
            }
        }
    }
}
