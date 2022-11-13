package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2961 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N,c;
	static int[] S,B,numbers;
	
	static int min=Integer.MAX_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {

		N=Integer.parseInt(br.readLine());
		
		S=new int [N];
		B=new int [N];
		numbers = new int[N];

		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			S[i]=Integer.parseInt(st.nextToken());
			B[i]=Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i < N + 1; i++) {
			c = i;
			combination(0, 0); // n개중 i개 고르는 조합 nCi
		}

		System.out.println(min);
	}// end of main

	/** n개 중 i개를 고르는 조합 */
	private static void combination(int cnt, int index) {

		if(cnt==c) {
			int sum = 0;
			int mul = 1;
			for (int i = 0; i < c; i++) {
				mul *= S[numbers[i]];
				sum += B[numbers[i]];
			}
			min = Math.min(min, Math.abs(sum - mul));
			return;
		}
		for(int i=0;i<N;i++) {
			numbers[cnt]=i;
			combination(cnt+1,i+1);
		}//cnt 뽑아야하는 개수, index 시작점
		
	}
}// end of class