package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    ** 시간초과 : StringBuilder 한 번에 출력
 */
public class B11729_하노이_탑_이동순서 {
    static StringBuilder sb = new StringBuilder();

    static void hanoi(int dcNum, int startNum, int endNum) {
        if (dcNum > 1) hanoi(dcNum - 1, startNum, 6 - startNum - endNum);

        sb.append(startNum + " " + endNum). append('\n');

        if (dcNum > 1) hanoi(dcNum - 1, 6 - startNum - endNum, endNum);
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        sb.append((int)Math.pow(2, N) - 1).append('\n');

        hanoi(N, 1, 3);

        System.out.println(sb);
    }
}
