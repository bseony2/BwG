import java.io.*;
import java.util.*;
import java.lang.Math;
public class 외주수익최대화하기 {
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        int[] time = new int[N+1];
        int[] price = new int[N+1];

        for(int n=0; n<N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            time[n] = Integer.parseInt(st.nextToken());
            price[n] = Integer.parseInt(st.nextToken());
        }

        for(int n=0; n<N; n++) {
            if(n + time[n] < N+1) {
                dp[n + price[n]] = Math.max(dp[n] + price[n], dp[n + time[n]]);
            }
            
            dp[n+1] = Math.max(dp[n+1], dp[n]);
        }

        System.out.println(dp[N]);
    }

}
