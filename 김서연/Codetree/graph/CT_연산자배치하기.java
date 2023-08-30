package codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * - 동일 문제 : BOJ 14888
 */
public class CT_연산자배치하기 {
    static int n; // 피연산자 개수
    static int[] nums; // 피연산자 배열
    static int[] operator = new int[3]; // 인덱스 순서[0 : +, 1 : -, 2 : *], 연산자 배열(크기 고정 : 3)
    static int MAX = Integer.MIN_VALUE; // 출력 최대값
    static int MIN = Integer.MAX_VALUE; // 출력 최소값


    public static void main(String[] args) throws IOException {
        init(); // 입력값 초기화

        dfs(1, nums[0]); // depth 초기값 유의
        System.out.println(MIN + " " + MAX);
    }

    static void dfs(int depth, int operand) {
        if (depth == n) {
            MAX = Math.max(MAX, operand);
            MIN = Math.min(MIN, operand);
            return;
        }

        // 연산자 배열 순회
        for (int i = 0; i < 3; i++) {
            // 연산자 배열 value가 0이상이어야 사용 가능
            if (operator[i] > 0) {
                // 연산자 사용할테니 값 -1 처리
                operator[i]--; // isVisited = true;

                // operator배열의 인덱스에 따른 연산 수행(dfs 재귀호출)
                switch (i) {
                    case 0: dfs(depth + 1, operand + nums[depth]); break;
                    case 1: dfs(depth + 1, operand - nums[depth]); break;
                    case 2: dfs(depth + 1, operand * nums[depth]); break;

                }

                operator[i]++; // isVisited = false;
            }
        }
    }
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 총 숫자
        n = Integer.parseInt(br.readLine());
        nums = new int[n];

        // 피연산자 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            operator[i] = Integer.parseInt(st.nextToken());
        }
    }
}
