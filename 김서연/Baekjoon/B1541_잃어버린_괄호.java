package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Baekjoon 1541 잃어버린 괄호 - Silver2
 * - Greedy, Math, Parsing
 *
 * 1. 첫 번째 '-'가 나올 때까지 조회
 * 2. 두 번째 '-'가 나올 때까지 연산 -> 할 필요 없음
 *
 * [!] 입력값 공백없음
 * [!] 괄호 여러 개 가능? '적절히'는 누구 기준인가 (뭐야..)
 *     => 적절히가 아니라 그냥 '-' 나오기만 하면 나머지 다 뺌..
 * [!] 첫 번째 '-' 인덱스만 구한다???
 *
 * 왜 그리디 알고리즘이지?
 */
public class B1541_잃어버린_괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        String intStr = str.replaceAll("[^\\d]", " ");
        String oprStr = str.replaceAll("[^\\+ || ^\\-]", " ");

        // 숫자
        StringTokenizer st = new StringTokenizer(intStr);
        int[] numsArr = new int[st.countTokens()];

        for (int i = 0; i < numsArr.length; i++) {
            numsArr[i] = Integer.parseInt(st.nextToken());
        }

        int rslt = numsArr[0]; // 첫 번째 값

        // 연산자
        st = new StringTokenizer(oprStr);
        String[] oprArr = new String[st.countTokens()];

        for (int i = 0; i < oprArr.length; i++) {
            oprArr[i] = st.nextToken();
        }

        // 부호 인덱스 = 숫자 인덱스 + 1
        int idx = Arrays.asList(oprArr).indexOf("-"); // 값이 없으면 -1 반환!
        for (int i = 0; i < oprArr.length; i++) {
            if (-1 != idx && i >= idx) { // 전체 '+'인 경우 생각!!
                rslt -= numsArr[i + 1];
            } else {
                rslt += numsArr[i + 1];
            }
        }

        System.out.println(rslt);
    }
}
