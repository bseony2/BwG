import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기 {
    static boolean[][] matrix;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        matrix = new boolean[N][M];

        int answer = Integer.MAX_VALUE;

        for(int i=0; i<matrix.length; i++) {
            String line = br.readLine();
            for(int j=0; j<matrix[0].length; j++) {
                if(line.charAt(j) == 'W') matrix[i][j] = true; // 흰색
                else matrix[i][j] = false;
            }
        }

        for(int i=0; i< N -7; i++) {
            for(int j=0; j<M-7; j++ ) {
                
                int result = check(i, j);
                answer = answer < result ? answer : result;
            }
        }

        System.out.println(answer);
    }

    static int check(int n, int m) {
        int cnt = 0;
        boolean firstColor = matrix[n][m];


        for(int i = n; i<n+8; i++) {
            firstColor = !firstColor;
            for(int j=m; j<m+8; j++) { 

                if(matrix[i][j] == !firstColor) {
                    cnt++;
                }
                
                firstColor = !firstColor;
                
            }
        }
        // 하얀색이 먼저 오는 체스판이과 검정색이 먼저 오는 체스 판은 완전 반대되기 때문에 뒤집어야하는 횟수는 64를 기준으로 대충을 이뤄야 함
        return cnt < 64 - cnt ? cnt : 64 - cnt; 
    }
}
