package w2;

import java.util.Scanner;

public class p4948 {
    final static int size = 123456 * 2;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean prime[] = new boolean[size + 1];
        prime[0] = prime[1] = true;
        for(int i = 2; i <= size; i++) {
            if(prime[i]) continue;
            for(int j = i + i; j <= size; j += i) {
                prime[j] = true;
            }
        }

        while(true) {
            int n = sc.nextInt(), answer = 0;
            if(n == 0) break;
            for(int i = n+1; i <=2*n; i++) {
                if(!prime[i]) answer++;
            }
            System.out.println(answer);
        }
    }
}
