import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class 소수 구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[] array = new boolean[N + 1];
        array[1] = true;
        for(int i=2; i< (N/2) + 1; i++) {
            if(array[i] == false) {
                for(int j = i * 2; j < N + 1; j+=i) {
                    array[j] = true;
                }
            }
        }

        for(int i = M; i< N + 1; i++) {
            if(array[i] == false)
                System.out.println(i);
        }

    }
}
