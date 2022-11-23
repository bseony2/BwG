package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_11052 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {

		// 구매하려고 하는 카드의 개수
		N = Integer.parseInt(br.readLine());
		// N개의 카드를 살 때 가격의 최대값이 들어가는 배열 dp[] 로 선언
		int dp[] = new int[N + 1];
		// 카드팩 당 가격 들어가는 배열 p[] 로 선언
		int p[] = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");
		
		//i개가 들어있는 카드팩을 살 때 값 저장 
		for (int i = 1; i < N + 1; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		
		// i개의 카드를 살 때 최대값은 dp[i] = p[j]  +  dp[i-j]
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i], p[j] + dp[i - j]); 
			}
		}

		System.out.println(dp[N]);

	}
}