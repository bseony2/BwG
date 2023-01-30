/**
 * BOJ - 돌려 돌려 돌림판! (11504번)
 * https://www.acmicpc.net/problem/11504
 * 간단한 시뮬레이션 문제.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p11504 {

    private static int T, N, M, X, Y;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[];
        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; ++testCase) {
            inputValueSetting(br);
            System.out.println(solution());
        }

    }

    private static int solution() {
        int ans = 0;
        for(int i = 0; i < N; i++) {
            int num = 0;
            for(int j = 0; j < M; j++) {
                num *= 10;
                num += numbers[(i + j) % N];
            }
            if(X<= num && num <= Y) ans++;
        }
        return ans;
    }

    private static void inputValueSetting(BufferedReader br) throws IOException {

        String[] line;

        line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);
        numbers = new int[N];

        // X
        int x = 0;
        line = br.readLine().split(" ");
        for (String digit : line) {
            x *= 10;
            x += Integer.parseInt(digit);
        }
        X = x;

        // Y
        int y = 0;
        line = br.readLine().split(" ");
        for (String digit : line) {
            y *= 10;
            y += Integer.parseInt(digit);
        }
        Y = y;

        // numbers Setting
        line = br.readLine().split(" ");
        for(int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(line[i]);
        }
    }
}
