/**
 *
 *  BOJ - 단어공부 (구현)
 *  https://www.acmicpc.net/problem/1157
 */

import java.util.Scanner;

public class p1157 {
    public static void main(String[] args) {
        int[] alpha = new int[26];
        Scanner sc = new Scanner(System.in);
        String word = sc.next().toUpperCase();
        for(char c : word.toCharArray()) {
            alpha[c-'A']++;
        }
        int max_cnt = 0, dup = 0, what = 0;
        for(int i = 0; i < 26; i++) {
            if(max_cnt <= alpha[i]) {
                what = i;
                if(max_cnt == alpha[i]) dup++;
                else {
                    dup = 0;
                    max_cnt = alpha[i];
                }
            }
        }
        if(dup!=0)System.out.println('?');
        else      System.out.println((char)(what+'A'));
    }
}