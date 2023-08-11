package baekjoon.brute_force;

import java.util.Arrays;
import java.util.Scanner;

/**
 *  Baekjoon 2309 일곱난쟁이
 *  - Bronze 1
 */
public class B2309_일곱난쟁이2 {
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
        for (int i = 0; i < arr.length - 1; i++) {
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
