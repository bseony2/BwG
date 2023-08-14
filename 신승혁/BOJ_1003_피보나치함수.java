import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class BOJ_1003_피보나치함수 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new long[41];
        dp[0] = 0L;
        dp[1] = 1L;
        for (int i = 2; i < 41; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                System.out.println(1 + " " + 0);
                continue;
            }
            System.out.println(dp[N - 1] + " " + dp[N]);
        }
    }
}
