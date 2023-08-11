import java.util.Scanner;

public class p1244 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] switches = new int[n+1];

        for(int i = 1; i <= n; i++){
            switches[i] = sc.nextInt();
        }
        int m = sc.nextInt();
        while(m-- > 0){
            int who = sc.nextInt();
            int where = sc.nextInt();
            if(who == 1){
                for(int i = where; i <= n; i+=where){
                    switches[i] ^= 1;
                }
            } else{
                switches[where] ^= 1;
                for(int i = 1; i <= n; i++){
                    if(where - i >= 1 && i + where <= n && switches[where - i] == switches[i + where]) {
                        switches[where - i] = switches[i + where] = switches[i + where]^1;
                    } else break;
                }
            }
        }
        for(int i = 1; i <= n; i++){
            System.out.print(switches[i] + " ");
            if(i%20 == 0) System.out.println();
        }
    }
}
