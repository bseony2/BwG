import java.util.Scanner;
import java.util.Arrays;

public class 합성수방정식 {

    static boolean[] isNotPrime = new boolean[100000001];
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        for(int t=1; t<=T; t++) {
            int n =scanner.nextInt();
            int[] result = get(n);
            System.out.println("#"+t + " " + result[0] + " " + result[1]);
        }

        scanner.close();
    }

    static int[] get(int n) {
        int[] result = new int[2];
        
        if(n==1) {
            result[0] = 9;
            result[1] = 8;
        }
        else {
            result[0] = n*3;
            result[1] = n*2;
        }

        return result;
    }
}
