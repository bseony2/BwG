package w0;

import java.util.Scanner;

public class p1535 {

    static int N;
    static int[] gain = new int[21];
    static int[] loss = new int[21];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
        for(int i = 0; i < N; i++) loss[i] = sc.nextInt();
        for(int i = 0; i < N; i++) gain[i] = sc.nextInt();

        int answer = 0;
        for(int i = 0; i < (1<<N); i++) {
            int hp = 100, joy = 0;
            boolean isOk = true;
            for(int j = 0; j < N; j++){
                if((i & (1<<j)) != 0 ) { hp -= loss[j]; joy += gain[j];}
                if(hp <= 0) { isOk = false; break;}
            }
            if(!isOk) continue;
            if(answer < joy) answer = joy;
        }
        System.out.println(answer);
    }
}