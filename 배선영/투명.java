import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 투명 {
    static int N, M;
    static int[][] matrix = new int[100][100];
    public static void main(String...args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r1 = Integer.parseInt(st.nextToken())-1;
            int c1 = Integer.parseInt(st.nextToken())-1;
            int r2 = Integer.parseInt(st.nextToken())-1;
            int c2 = Integer.parseInt(st.nextToken())-1;
            block(r1, c1, r2, c2);
        }

        System.out.println(cnt());
    }

    static void block(int r1, int c1, int r2, int c2) {
        for(int i=r1; i<=r2; i++) {
            for(int j=c1; j<=c2; j++) {
                matrix[i][j] += 1;
            }
        }
    }

    static int cnt() {
        int result = 0;
        for(int i=0; i<100; i++) {
            for(int j=0; j<100; j++) {
                if(matrix[i][j] > M) result += 1;
            }
        }
        return result;
    }
}
