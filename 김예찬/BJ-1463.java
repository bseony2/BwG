package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Boj_1463 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int dp[] ;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {

	       n = Integer.parseInt(br.readLine());
	       dp = new int[n+1];
	
	       dp[0]=0;
	       dp[1]=0;
	       solution(n);
	       	       
	       System.out.println(dp[n]);
	}

	private static void solution(int n) {

			// 타겟 정수까지 반복
	       for(int i=2;i<=n;i++) {
	    	   
	    	   // 2와 3으로 안나누어지는 경우엔 어차피 이전 수에 +1을 더하면 된다.
	    	   dp[i]=dp[i-1]+1;
	    	    
	    	   // 3으로 나누어지면 -> 기존에 저장한 수  vs 3으로나눔(+1)한 수와 횟수 비교 저장 
	    	   if(i%3==0) dp[i]=Math.min(dp[i],dp[i/3]+1 );
	    	   if(i%2==0) dp[i]=Math.min(dp[i],dp[i/2]+1 );
	    	   
	       }
	}

}
