package baekjoon.brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 2023.07.24
 * Baekjoon 2839 설탕배달
 * - Silver 4
 * - Brute Force, Implementation
 *   기본수학1 문제였으나 Brute Force로 푸는게 빠름
 *
 *      1. 정확히 나누어 떨어지는가? 판단
 *          - 정확히 나누어떨어지지 않으면 -1 반환
*           - 그리디?
 *              1) 5로만 나누면서 나머지 생기는 경우 몫 -1씩 차감
 *              2) 5전체 안되면 3으로만 나눈다
 *              3) 1,2, 안되면 -1 반환
 *
 *      그리디인데....??? 베낭문제..
 */
public class B2839_설탕배달2 {
    static int n;
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        br.close();
        int quot = n / 5;

        if (n % 5 == 0) {
            System.out.println(n / 5);
            return;
        } else {
            divide(quot);
        }

        System.out.println(result);
    }

    // 재귀
    static int divide(int quotFive) {
        if (quotFive == 0) {
            if (n % 3 != 0) {
                result = -1;
            } else {
                result = n / 3;
            }
            return result;
        }

        int temp = (n - (5 * quotFive)) % 3;
        if (temp == 0) {
            result = quotFive + ((n - (5 * quotFive)) / 3);
            return result;
        } else {
            divide(quotFive - 1);
        }

        return result;
    }
}
