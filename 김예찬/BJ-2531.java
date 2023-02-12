package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj_2531 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	
	static int N,d,k,c;
	static int [] dish;
	static boolean[] kind;
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
 
		st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());

		dish = new int[N];
		kind = new boolean[d+1];
		
		for(int i=0; i<N;i++) {
			dish[i] = Integer.parseInt(br.readLine());
		}
		for(int i=0;i<N;i++) {
			int count=1;
			Arrays.fill(kind, false);//초기화
			
			for(int j=i;j<(i+k);j++)// 끝에 넘어가면 0으로 가야함
				{
				if(!kind[dish[j%N]]) {
					kind[dish[j%N]]=true;
					count++;
					if(dish[j%N]==c) count--;
				}
				
			}
			if(count==k+1) {
				answer=count;
			}
			answer=Math.max(answer,count);
		}
		System.out.println(answer);
	}

}
 