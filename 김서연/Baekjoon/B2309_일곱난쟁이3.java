package baekjoon.brute_force;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Baekjoon 2309 일곱난쟁이
 *  - Bronze 1
 */
public class B2309_일곱난쟁이3 {
    static int[] arr = new int[9];
    static int sum = 0; // 배열의 총합

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }

        Arrays.sort(arr);
        int a = 0; int b = 0;

        // 합이 100 나오는 첫 번째 케이스로 출력
        for (int i = 0; i < arr.length - 1; i++) {
            if (a != 0 && b != 0) { // 첫 번째 결과 도출시 바로 출력
                break;
            }
            for (int j = i + 1; j < arr.length; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    a = i; b = j;
                    break;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (i == a || i == b) continue;
            System.out.println(arr[i]);
        }
    }
}

/*
20
7
23
19
10
15
25
8
13

20
7
23
19
10
9
12
8
13

25 15 - > 9 12 로 변경
*/
