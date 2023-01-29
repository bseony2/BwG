package Boj.gold;

import java.util.Arrays;
import java.util.Scanner;

public class Boj_2470 {
	static int n;
	static int[] arr;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr);
	     int start = 0;
	        int end = n - 1;
	        int sum = 2000000000;
	        int min = 0;
	        int max = 0;
	        while(start < end) {
	            int abs = Math.abs(arr[start] + arr[end]);
	            if(abs < sum) {
	                sum = abs;
	                min = arr[start];
	                max = arr[end];
	            }
	            if(arr[start] + arr[end] > 0) end--; // end 값  > start 값 : 합이 커서 줄여야함
	            else start++; // start > end : 합이 작아서 늘려야함
	        }
	        System.out.println(min + " " +max);
	}

}
