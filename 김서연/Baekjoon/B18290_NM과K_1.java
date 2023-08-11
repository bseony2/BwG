package baekjoon.backtraking;

/**
 *  Baekjoon 18290 NM과 K(1)
 *  - Silver 1
 *  - Backtracking, Bruteforce, Permutation
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  1. 인접 chk
 *  2. 최대값?!
 */
public class B18290_NM과K_1 {
    static boolean[][] isVisited;
    static int[][] arr;
    static int n, m, k, answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        isVisited = new boolean[n + 1][m + 1];
        answer = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0, 0);
        System.out.println(answer);
    }

    static void dfs(int x, int y, int cnt, int sum) {
        if (cnt == k) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = x; i < n; i++) {
            for (int j = y; j < m; j++) {
                if (!isVisited[i][j]) {
                    if (chkAdjcnt(i, j)) {
                        isVisited[i][j] = true;
                        dfs(x, y, cnt + 1, sum + arr[i][j]);
                        isVisited[i][j] = false;
                    }
                }
            }
        }
    }

    static boolean chkAdjcnt(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (isVisited[nx][ny]) { // *****
                   return false;
                }
            }
        }
        return true;
    }
}
