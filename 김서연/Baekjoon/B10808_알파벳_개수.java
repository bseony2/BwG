package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 97 - c[i] 삽질로 ArrayIndexOutOfBoundsException 발생
public class B10808_알파벳_개수 {
    static int[] arr = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        char[] c = str.toCharArray();

        for (int i = 0; i < c.length; i++) {
            arr[c[i] - 97]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }

        System.out.println(sb);
    }
}
