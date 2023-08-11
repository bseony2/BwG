package baekjoon.dp;

import java.util.Scanner;

public class B9655_돌_게임 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 짝 = 홀 + 홀 -> 창영
        // 홀 = 홀 + 짝 -> (불가) 상근
        if (n % 2 == 0) System.out.println("CY");
        else System.out.println("SK");
    }
}
