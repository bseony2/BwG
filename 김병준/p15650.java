package w3;

import java.util.Scanner;

public class p15650 {

    static int ans[] = new int[8];

    public static void go(int idx, int start, int n, int m){
        if(idx == m) {
            for(int i = 0; i < m; i++){
                System.out.print(ans[i]+ " ");
            }
            System.out.println();
            return;
        }
        for(int i = start; i <= n; i++){
            ans[idx] = i;
            go(idx + 1, i + 1, n, m);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt();
        go(0, 1, n, m);
    }
}