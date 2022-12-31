package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1912 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n;
	static int arr[];
	static int dp[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		n = Integer.parseInt(br.readLine()); 
		arr = new int [n+1];
		dp = new int[n+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i]= Integer.parseInt(st.nextToken());
		}
		dp[0]=arr[0];
		
		int answer =arr[0];
		 
		for(int i=1; i<n;i++) {
			
			dp[i] = Math.max(dp[i-1]+arr[i], arr[i]);
			answer =Math.max(answer, dp[i]);
		}

		System.out.println(answer);
	}

}
