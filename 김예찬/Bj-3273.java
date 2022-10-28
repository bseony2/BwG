package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bj_3273 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n,target;
	static int answer;
	public static void main(String[] args) throws NumberFormatException, IOException {


		// 입력부
		n = Integer.parseInt(br.readLine());
		int [] arr = new int[n];		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		target=Integer.parseInt(br.readLine());
		
		Arrays.sort(arr);
		
		int start = arr[0];
		int end = arr[n-1];
		int i=1;
		while(end>start) {
			
			if(start+end==target) answer++;
		
			if(start+end>=target) {
				n -=1;
				end = arr[n-1];
			}
			else {
				start = arr[i++];
			}
		}
		
		
		
		/* 이러케하면 시간초과입니다~~ */
//		for(int i=0; i<n;i++) {
//			int a1 = arr[i];
//			for(int j=i+1;j<n;j++) {
//				int a2=arr[j];
//				
//				if(a1+a2==target) answer++;
//			}
//		}
		
		System.out.println(answer);	
	}

}
