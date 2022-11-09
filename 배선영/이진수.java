import java.util.Scanner;
import java.lang.StringBuilder;
/**
 * 이진수
 */
public class 이진수 {
    static StringBuilder sb;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.next());
        
        for(int i=0; i<T; i++) {
            prcs(Integer.parseInt(sc.next()));
        }
        sc.close();
    }

    public static void prcs(int n) {
        sb = new StringBuilder();
        String str = Integer.toBinaryString(n);
        for(int i=0; i<str.length(); i++) {
            if( str.charAt(str.length() - i - 1) == '1' ) {
                sb.append(String.valueOf(i)).append(" ");
            }
        }
        System.out.println(sb.toString().trim());
    }
}