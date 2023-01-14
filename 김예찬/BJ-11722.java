package Boj.silver;

import java.util.Scanner;

public class Boj_11722 {
	static int n;
	static int[] arr;
	static int[] memo;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		n= sc.nextInt();
		arr = new int[n];
		
		memo = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i]= sc.nextInt();
			memo[i]=1;
		}
		
		for(int i=1; i<n;i++) {
			for(int j=0; j<i;j++) {
				
				if(arr[i]<arr[j] && memo[i]<=memo[j]) memo[i]=memo[j]+1;
				else if (arr[i]==arr[j]) {
					memo[i]=memo[j];
				}
			}
		}
		int max =0;
		for(int i : memo) {
			System.out.print(i+" ");
			if(max<i) max=i;
		}
		System.out.println();
		System.out.println(max);
	}
}


/*
10 30 10 20 20 10;

1  1  2  2  2  3 
6
10 30 20 15 20 15
1  1  2  3  2  3

 */