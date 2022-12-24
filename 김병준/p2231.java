

import java.util.Scanner;

public class p2231 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        System.out.println(solve(N));
    }
    public static int solve(int n) {
        for(int i = 1; i <= n; i++) {
            int answer = i;
            int num = i;
            for(int div = 1000000; div >= 1; div /= 10) {
                answer += (num/div);
                num %= div;
            }
            if(answer == n) return i;
        }
        return 0;
    }
}
