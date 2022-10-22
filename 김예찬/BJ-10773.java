package Boj.silver;

import java.util.Scanner;

public class BJ_10773 {
	static int K;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		
		int [] answer = new int [K];
		
		for(int i=0;i<K;i++) {
			answer[i]=sc.nextInt();
		}
		
		for(int i=1; i<K;i++) {
			if(answer[i]==0) {
				int t = i-1;
				boolean sync = false;
				while(!sync) {
					if(answer[t]!=0) {
						answer[t]=0;
						sync = true;
					}
					t--;
					if(t<0) continue;
				}
			}
		}
		int sum = 0;
		for(int i : answer) {
			sum+=i;
		}
		System.out.println(sum);
	}

}
