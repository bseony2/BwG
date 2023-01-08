import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;


public class 이xn타일링 {

    static public void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n + 1];
        arr[0] = 1;
        arr[1] = 1;
        

        for(int i=2; i<n+1; i++) {
            arr[i] = (arr[i-2] + arr[i-1]) % 10007;
        }

        System.out.println(arr[n]);
    }
}