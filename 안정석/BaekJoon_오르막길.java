package day1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon_오르막길 {

	static int sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		sum = 0;
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			sum = 0;
			int j = i;
			for (j = j + 1; j < N; j++) {
				if (arr[j - 1] < arr[j]) {
					sum += arr[j] - arr[j - 1];
					i = j;
				} else {
					break;
				}
			}
			list.add(sum);
		}
		Collections.sort(list);
		System.out.println(list.get(list.size() - 1));
	}
}
