import java.util.Scanner;

public class p1065 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), ans = 0;

        if (N <= 99) ans += N;
        else ans += 99;
        for (int i = 100; i <= N; i++) {
            String str = Integer.toString(i);
            int diff = str.charAt(1) - str.charAt(0);
            boolean flag = true;
            for(int j = 1; j < str.length()-1; j++) {
                if(str.charAt(j+1) - str.charAt(j) != diff) {
                    flag = false;
                    break;
                }
            }
            if(flag) ans++;
        }
        System.out.println(ans);
    }
}
