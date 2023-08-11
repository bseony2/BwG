import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class 파이프옮기기1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int matrix[][] = new int[N][N];
        for(int r = 0; r<N; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int c = 0; c<N; c++) {
                matrix[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int[][][] dp = new int[N][N][3];       // 0은 가로, 2는 세로, 3은 대각
        for(int i=1; i<N; i++) {
            if(matrix[0][i] == 1) {
                break;
            }
            dp[0][i][0] = 1;
        }

        for(int r = 0; r<N; r++) {
            for(int c = 2; c<N; c++) {
                if(matrix[r][c] == 1) {
                    continue;
                }
                dp[r][c][0] = dp[r][c-1][2] + dp[r][c-1][0];
                if(r > 0) {
                    dp[r][c][1] = dp[r-1][c][1] + dp[r-1][c][2];
                }
                
                if(r > 0 && matrix[r-1][c] == 0 && matrix[r][c-1] == 0) {
                    dp[r][c][2] = dp[r-1][c-1][0] + dp[r-1][c-1][1] + dp[r-1][c-1][2];
                }
            }
        }

        bw.write(String.valueOf(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]));
        bw.flush();
    }
}
