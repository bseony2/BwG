package baekjoon.dynamic_programming;

import java.util.Scanner;

public class B2579_계단오르기 {
    static Integer dp[]; // 결과값 저장할 배열(메모이제이션)
    static int arr[]; // 입력값(계단의 점수)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 계단의 개수
        arr = new int[n + 1];
        dp = new Integer[n + 1];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        // dp 고정값 세팅(아래 조건에서 걸리지 않도록 -> dp[] == null ?)
        dp[0] = arr[0]; // dp의 초기값은 null이므로 0으로 초기화
        dp[1] = arr[1]; // dp 첫 번째 값은 arr 첫 번째 값과 동일(시작점)

        // n = 1로 주어지는 경우 방어로직?!??
        if (n >= 2) {
            dp[2] = arr[1] + arr[2];
        }

        System.out.println(stairs(n));
    }

    static int stairs(int n) {
        // 아직 탐색 전이면 재귀호출(dp[n]에 값이 할당됐으면 마지막 계단까지 오른 것)
        if (dp[n] == null) {
            // arr[n] = 마지막 계단 더하기(무조건 밟아야 함)
            // n - 2 and n - 3, arr[n - 1]을 합한 값으로 비교
            dp[n] = Math.max(stairs(n - 2), stairs(n - 3) + arr[n - 1]) + arr[n];
        }

        return dp[n];
    }
}
