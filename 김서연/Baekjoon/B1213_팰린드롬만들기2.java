package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B1213_팰린드롬만들기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String result = "";

        String str = br.readLine();
        int[] arr = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i) - 'A';
            arr[index]++;
        }

        int oddNum = 0; // 홀수 개수 체크
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 != 0) {
                oddNum++;
                num = i;
            }
            // 팰린드롬 생성 불가
            if (oddNum >= 2) {
                System.out.println("I'm Sorry Hansoo");
                return;
            }
        }

        // 절반 출력값 조립
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i] / 2; j++) {
                sb.append((char)(i + 'A'));
            }
        }

        result = sb.toString();
        if (oddNum == 1) {
            result += (char) (num + 'A');
        }
        result += sb.reverse().toString();
        System.out.println(result);
    }
}
