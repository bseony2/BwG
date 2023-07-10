import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class 피보나치수3 {
    static final int PISANO = 1500000;
    static int[] pibonachi;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        int size = (int)(n % PISANO);
        pibonachi = new int[size + 1];

        pibonachi[0] = 0;
        pibonachi[1] = 1;

        for(int i=2; i<pibonachi.length; i++) {
            pibonachi[i] = (pibonachi[i-1] + pibonachi[i-2]) % 1000000;
        }

        System.out.println(pibonachi[size]);
    }
}