package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 *  B10773_제로2_ver2 (ver1 : ArrayList)
 *  10773 Baekjoon Silver4
 *  Stack 사용
 */
public class B10773_제로_ver2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Integer> stack = new Stack<>();

        int N = Integer.parseInt(br.readLine());
        int sumResult = 0;

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(br.readLine());

            // input 판단 후 push, pop 시점에 연산수행 (for문 2개 X - 입력 끝내고 판단X)
            // 0이 연속으로 나오는 경우 ?! -> stack
            if (input == 0) {
                sumResult -= stack.pop(); // input = 0, 최근의 수 뺴기
            } else {
                stack.push(input);
                sumResult += input;
            }
        }

        System.out.println(sumResult);
    }
}
