/**
 *  BOJ - 1 ( 4375)
 *  https://www.acmicpc.net/problem/4375
 *
 *  오답 - 해설,
 */


import java.util.Scanner;

public class p4375 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int start = 1, ans = 1;
            while(start % n != 0){
                start *= 10;
                start += 1;
                ans += 1;
            }
            System.out.println("ans = " + ans);
        }
    }
}