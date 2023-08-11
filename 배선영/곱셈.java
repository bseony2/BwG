import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class 곱셈 {

    static long A;
    static long B;
    static long C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        System.out.println(divide(B));
    }

    static long divide(long a) {
        if(a == 1) return A % C;

        long next = divide( a/2 );
        if(a % 2 == 0) {
            return next * next % C;
        } else {
            return next * next % C * A % C;
        }
    }
}



// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.io.BufferedReader;
// import java.util.StringTokenizer;

// public class 곱셈 {

//     static int A;
//     static int B;
//     static int C;
//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine());

//         A = Integer.parseInt(st.nextToken());
//         B = Integer.parseInt(st.nextToken());
//         C = Integer.parseInt(st.nextToken());

//         System.out.println(divide(B));
//     }

//     static long divide(int a) {
//         if(a == 1) return A % C;

//         int next = a / 2;
//         if(a % 2 == 0) {
//             return (divide(next) * divide(next)) % C;
//         } else {
//             return (divide(next) * divide(next + 1)) % C;
//         }
//     }
// }