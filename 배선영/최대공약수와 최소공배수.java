import java.util.Scanner;

public class 최대공약수와 최소공배수 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = Integer.parseInt(sc.next());
        int b = Integer.parseInt(sc.next());
        int gcd = a > b ? gcd(a, b) : gcd(b, a);
        System.out.println(gcd);
        System.out.println(a * b / gcd);
        sc.close();
    }

    static int gcd(int a, int b) {
        if( b == 0 )
            return a;
        else {
            return gcd(b, a % b);
        }
    }
}

/*
 * 최대 공약수 - 유클리드 호제법
 *      두 자연수 a,b의 나머지를 r 이라 하면 ( a > b )
 *      a와 b의 최대공약수는 b와 r의 최대공약수와 같다.
 *          -> 재귀 호출을 통해 a b의 최대 공약수 획득
 * 
 * 최소 공배수
 *      두 자연수 a,b의 최소공배수는 a와 b의 곱을 최대 공약수로 나눈 값과 같다
 */