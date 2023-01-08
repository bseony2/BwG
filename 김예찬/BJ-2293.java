package Boj.gold;

import java.util.Scanner;

public class Boj_2293 {

	static int n,k;
	static int dp[];
	static int coin[];
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		
		dp = new int [k+1];
		coin = new int[n];
		
		for(int i=0;i<n;i++) {
			coin[i] = sc.nextInt();
		}
		dp[0]=1;
		
		
		for(int i=0; i<n;i++) {
			for(int j=1;j<=k;j++) {
				if(coin[i]<=j) {
					dp[j] += dp[j-coin[i]];
				}
			}
		}
		
		 System.out.println(dp[k]);
	
	}

}



