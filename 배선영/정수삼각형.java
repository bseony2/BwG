import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.lang.Math;

public class 정수삼각형 {
    static int[][] dp;
    static int[][] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n][n];
        input = new int[n][n];

        for(int i=0; i<n; i++) {
            input[i] = Arrays.stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
        dp[0][0] = input[0][0];

        for(int i=1; i<n; i++) {
            for(int j=0; j<i+1; j++) {
                if(j ==0) {
                    dp[i][j] = dp[i-1][j] + input[i][j];
                }
                else if(j == i) {
                    dp[i][j] = dp[i-1][j-1] + input[i][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i-1][j] + input[i][j], dp[i-1][j-1] + input[i][j]);
                }
            }
        }

        System.out.println(Arrays.stream(dp[n-1]).max().getAsInt());
    }
}
