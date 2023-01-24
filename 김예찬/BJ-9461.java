package Boj.silver;

import java.util.Scanner;

public class Boj_9461 {
	static long[] memo; 
	static int T;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		T=sc.nextInt();
		
		memo = new long[101];
		memo[0]=1;
		memo[1]=1;
		memo[2]=1;
		memo[3]=2;
		for(int i=0; i<T;i++) {
			int n = sc.nextInt();
			if(n<4) {
				System.out.println(memo[n-1]);
			}else {
				System.out.println(DP(n-1));
			}
			
		}

	}
	
	private static long DP(int n) {
		if(memo[n]==0) {
			memo[n]= DP(n-2)+DP(n-3);
		}
		return memo[n];
	}

}

//  1 1 1 2 2 3 4 5 7 9

// DP[N] = DP[n-2]+DP[n-3]