package w2;

import java.util.Scanner;

public class p15649 {
    static boolean[] check = new boolean[10];
    static int[] ans = new int[10];

    public static void go(int n, int m, int idx){
        if(idx == m) {
            for(int i = 0; i < m; i++){
                System.out.print(ans[i]+ " ");
            }
            System.out.println();
            return;
        }
        for(int i = 1; i <= n; i++){
            if(check[i]) continue;
            check[i] = true;
            ans[idx] = i;
            go(n, m, idx + 1);
            check[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        go(n, m, 0);
    }
}
