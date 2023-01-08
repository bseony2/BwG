import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class 동물원 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());

        int[][] fence = new int[N][3];
        fence[0][0] = 1;
        fence[0][1] = 1;
        fence[0][2] = 1;

        for(int i=1; i<N; i++) {
            fence[i][0] = (fence[i-1][0] + fence[i-1][1] + fence[i-1][2]) % 9901;
            fence[i][1] = (fence[i-1][0] + fence[i-1][2]) % 9901;
            fence[i][2] = (fence[i-1][0] + fence[i-1][1]) % 9901;
        }

        int answer = fence[N-1][0] + fence[N-1][1] + fence[N-1][1];

        System.out.println(answer % 9901);
    }
}
