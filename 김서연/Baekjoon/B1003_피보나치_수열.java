package baekjoon.dynamic_programming;

import java.util.Scanner;

public class B1003_피보나치_수열 {

    // null 체크 위해 Integer 배열로 선언
    static Integer[][] dp = new Integer[41][2]; // N은 40보다 작거나 같은 자연수 또는 0이다.

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // dp배열 0, 1 고정값 할당
        dp[0][0] = 1; // N = 0 일때 0 호출 횟수
        dp[0][1] = 0; // N = 0 일때 1 호출 횟수
        dp[1][0] = 0; // N = 1 일때 0 호출 횟수
        dp[1][1] = 1; // N = 1 일때 1 호출 횟수

        int t = sc.nextInt(); // testcase

        StringBuilder sb = new StringBuilder();

        while (t-- > 0) {
            int n = sc.nextInt();
            fibonacci(n);
            sb.append(dp[n][0] + " " + dp[n][1]).append('\n');
        }
        System.out.println(sb);
    }

    static Integer[] fibonacci(int n) {
        if (dp[n][0] == null || dp[n][1] == null) {
            // 0, 1 호출 횟수에 대해 피보나치 재귀함수 호출
            dp[n][0] = fibonacci(n - 1)[0] + fibonacci(n - 2)[0];
            dp[n][1] = fibonacci(n - 1)[1] + fibonacci(n - 2)[1];
        }
        // n에 대한 0, 1 호출값을 담은 [n]번째 행 반환
        return dp[n];
    }
}
