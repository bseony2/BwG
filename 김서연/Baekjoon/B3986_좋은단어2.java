package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// 짝수개 팰린드롬

/**
 *  경우의 수
 *      1. AABB - 연속되어 나오는 경우
 *      2. ABBA - 팰린드롬
 *      3. ABBABB - ???
 */
public class B3986_좋은단어2 {
    static int totRslt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean validCnt;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            isGoodString(str);
        }
        System.out.println(totRslt);
    }

    static void isGoodString(String str) {
        if (str.length() % 2 == 1) return; // 문자열 길이가 홀수면 count 대상X

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (stack.size() > 0 && stack.peek() == str.charAt(i)) {
                stack.pop();
            } else {
                stack.push(str.charAt(i));
            }
        }

        if (stack.size() == 0) totRslt++;
    }
}
