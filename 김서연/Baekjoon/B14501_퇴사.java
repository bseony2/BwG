package baekjoon.dynamicprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  Baekjoon 14501 퇴사
 *  - Silver 3
 *  - Dynamic Programming, Brute Force
 */
public class B14501_퇴사 {
    static int[] t;
    static int[] p;

    static int[] dp; // n일에 얻을 수 있는 최대 수익 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        t = new int[n];
        p = new int[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        // dp : n일에 얻을 수 있는 최대 수익
        dp = new int[n + 1];

        for (int i = 0; i < n; i++) {
            if (i + t[i] <= n) {
                // 날짜 + 소요시간이 범위를 넘어가지 않는 경우만 dp배열 최대값 할당
                dp[i + t[i]] = Math.max(dp[i + t[i]], dp[i] + p[i]);
            }
            // 일할 수 없는 날, 이전까지의 최대 수당 할당
            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
        }

        // 마지막 최대값 출력
        System.out.println(dp[n]);
    }
}
