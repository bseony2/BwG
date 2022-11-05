package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1850 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static long answer;
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		
		long answer = gcd(Math.min(a, b),Math.max(a, b));
		

		for(long i=0;i<answer;i++) {
			sb.append("1");
		}
		System.out.println(sb);
		
	}

	private static long gcd (long a , long b) {
		
		if(b ==0) {
			return a;
		}else {
			long d = a%b;
			return gcd(b,d);
		}
		
	}
}