import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class 약수구하기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        String[] inVal = bf.readLine().split(" ");
        int N = Integer.parseInt(inVal[0]);
        int K = Integer.parseInt(inVal[1]);

        int cnt = 0;
        int answer = 0;
        for(int i=1; i< N + 1; i++) {
            if(N % i == 0) {
                cnt++;
                answer = i;
                if(cnt == K)
                    break;
            }
        }
        System.out.println(cnt == K ? answer : 0);
    }
}