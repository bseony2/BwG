import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Math;

public class 퇴사 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] T = new int[N+1];
        int[] P = new int[N+1];

        for(int n=0; n<N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            T[n] = Integer.parseInt(st.nextToken());
            P[n] = Integer.parseInt(st.nextToken());
        }

        for(int n=0; n<N; n++) {
            if(n + T[n] < N+1) {
                dp[n + T[n]] = Math.max(dp[n] + P[n], dp[n + T[n]]);
            }
            
            dp[n+1] = Math.max(dp[n+1], dp[n]);
        }

        System.out.println(dp[N]);
    }
}