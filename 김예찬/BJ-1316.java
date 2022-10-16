package Boj.silver;

import java.util.HashSet;
import java.util.Scanner;

public class Boj_1316 {
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		answer = N;

		for (int i = 0; i < N; i++) {

			String line = sc.next();
			check(line);

		}

		System.out.println(answer);
	}

	private static void check(String line) {
		HashSet<Character> set = new HashSet<>();
		char ch = line.charAt(0);
		set.add(ch);
		for (int i = 0; i < line.length(); i++) {
			System.out.println(ch);
			if (ch == line.charAt(i)) { // 연속된 문자면 반복문 진행
				continue; 
			} else {
				if (set.contains(line.charAt(i))) {
					answer--;  
					return;
				} else {
					ch = line.charAt(i);
					set.add(ch); 
				}
			}
		}
	}

}
