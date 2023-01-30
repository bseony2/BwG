/**
 *  BOJ - https://www.acmicpc.net/problem/16951
 *  브루트포스
 */

import java.util.Scanner;

public class p16951 {

    static int N, K;
    static int heights[];
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        heights = new int[N];

        for(int i = 0; i < N; i++) {
            heights[i] = sc.nextInt();
        }
        int cost = 999;
        for(int i = 0; i < N; i++){
            if(heights[i] - K * i >= 1) {
                cost = Math.min(calc(heights[i] - K * i), cost);
            }
        }
        System.out.println(cost);
    }

    private static int calc(int start) {
        int diff = 0;
        for(int i = 0; i < N; i++){
            if(heights[i] != start) diff++;
            start += K;
        }
        return diff;
    }
}