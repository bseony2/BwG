import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class 다리놓기 {

    static int[][] dp = new int[31][31];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        // for(int i=0; i<T; i++) {
        //     StringTokenizer st = new StringTokenizer(br.readLine());

        //     int N = Integer.parseInt(st.nextToken());
        //     int M = Integer.parseInt(st.nextToken());

        //     System.out.println(combination(M, N));
        // }

        for (int i = 0; i < 30; i++) {
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for (int i = 2; i < 30; i++) {
            for (int j = 1; j < 30; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dp[M][N]);
        }
    }

    static int combination(int m, int n) {
        
        if(dp[m][n] > 0) return dp[m][n];

        if(m == n || n == 0) {
            return dp[m][n] = 1;
        }

        return combination(m - 1, n - 1) + combination(m - 1, n);
    }
}
