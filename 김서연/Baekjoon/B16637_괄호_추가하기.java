package baekjoon.brute_force;

import java.io.*;
import java.util.ArrayList;

/**
 *  Baekjoon 16637 괄호 추가하기
 *  - Gold 3
 *  - Brute Force, Implementation
 *      > Brute Force - 괄호로 묶을 대상 모두 뽑은 다음 브루트포스를 돌린다??
 *  - DFS, Backtracking
 *
 *  - 조건
 *      1. 0 <= 정수 <= 9
 *      2. 왼쪽부터 순서대로 계산
 *      3. 중첩 괄호 불가
 *      4. 괄호 안 연산자는 1개만
 *      5. 추가하는 괄호 개수 제한없음!!! && 괄호 없어도 됨 (result == MAX)
 */
public class B16637_괄호_추가하기 {
    static int maxRslt; // 최대값
    static ArrayList<Character> ops; // 연산자
    static ArrayList<Integer> nums; // 피연산자

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        ops = new ArrayList<>();
        nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                ops.add(c);
                continue;
            }
            nums.add(Character.getNumericValue(c)); // char to int
        }

        maxRslt = Integer.MIN_VALUE; // -2^31
        dfs(nums.get(0), 0); // nums, ops -> index[0] start

        bw.write(maxRslt + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

    // DFS, 백트래킹
    // 2가지 경우로 동시 재귀수행 (1. 괄호X, 2. 괄호O)
    static void dfs(int result, int opInx) {
        // 종료조건 : 주어진 연산자의 개수 초과하였을 시
        if (opInx >= ops.size()) {
            maxRslt = Math.max(maxRslt, result);
            return;
        }

        // 1. 괄호가 없는 경우
        int rslt1 = calculation(ops.get(opInx), result, nums.get(opInx + 1));
        dfs(rslt1, opInx + 1);

        // 2. 괄호가 있는 경우
        // 뒤부터 괄호 묶는다고?
        if (opInx + 1 < ops.size()) {
            // 파라미터 result의 오른쪽에 있는 값 연산
            int rslt2 = calculation(ops.get(opInx + 1), nums.get(opInx + 1), nums.get(opInx + 2));

            // 현재 result와 rslt2 값을 연산한 결과 && 괄호 오른쪽에 존재하는 연산자의 인덱스를 파라미터로 넘김
            dfs(calculation(ops.get(opInx), result, rslt2), opInx + 2);
        }
    }

    // 연산수행
    static int calculation(char op, int n1, int n2) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return -1;
        }
    }
}
