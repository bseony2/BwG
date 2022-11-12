import java.util.Scanner;
import java.lang.Math;
public class 소수찾기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.next());
        int answer = 0;
        for(int i=0; i<N; i++) {
            if( isPrime(Integer.parseInt(sc.next())) )
                answer++;
        }
        System.out.println(answer);
        sc.close();
    }

    static boolean isPrime(int n) {
        if( n == 1 || n == 2)
            return n == 1 ? false : true;

        for(int i=2; i<(int)Math.sqrt(n); i++) {
            if(n % i == 0)
                return false;
        }
        return true;
    }
}
