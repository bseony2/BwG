package Boj.silver;

import java.io.IOException;
import java.util.Scanner;

public class Boj_2941 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String ex = sc.nextLine();
		
		String[] str = {"c=","c-","dz=","d-","lj","nj","s=","z="};
		
		
		for(int i=0;i<str.length;i++) {
			if(ex.contains(str[i])) 
				ex = ex.replace(str[i], "a"); // 포함된 문자열을 a로 치환
		}
		System.out.println(ex.length());
		
	}

}
