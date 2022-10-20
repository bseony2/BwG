package w0;
import java.util.Scanner;

public class p11441 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int arr[] = new int[N + 1];
        int Sarr[] = new int[N + 1];
        for(int i = 1; i <= N; i++){
            arr[i] = sc.nextInt();
            Sarr[i] = Sarr[i-1] + arr[i];
        }
        int M = sc.nextInt();
        while(M-- > 0){
            int s = sc.nextInt();
            int e = sc.nextInt();
            System.out.println(Sarr[e] - Sarr[s-1]);
        }
    }
}