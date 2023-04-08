import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 원판돌리기 {

    static int N;
    static int M;
    static int num;
    static int sum = 0;
    static int[][] matrix;
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        num = N*M;

        matrix = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int temp = Integer.parseInt(st.nextToken());
                sum += temp;
                matrix[i][j] = temp;
            }
        }

        
        for(int i=0; i<T; i++) {
            
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            move(x, d, k);

        }

        System.out.println(sum);

    }

    static void move(int x, int d, int k) {
        for(int i=x-1; i<N; i+=x) {
            
            int[] temp = new int[M];
            if(d == 0) {    // 시계방향
                System.arraycopy(matrix[i], M-k, temp, 0, k);
                System.arraycopy(matrix[i], 0, temp, k, M-k);
            } else {    // 반시계방향
                System.arraycopy(matrix[i], k, temp, 0, M-k);
                System.arraycopy(matrix[i], 0, temp, M-k, k);
            }

            matrix[i] = temp;
        }

        cal();
    }

    static void cal() {
        int[] dr = new int[]{-1, 1, 0, 0};
        int[] dc = new int[]{0, 0, -1, 1};
        ArrayList<int[]> points = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(matrix[i][j] == 0)continue;

                for(int k=0; k<4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];

                    if(nr == -1 || nr == N) continue; 

                    if(nc == -1) {
                        nc = M-1;
                    } else if(nc == M) {
                        nc = 0;
                    }

                    if(matrix[i][j] == matrix[nr][nc] && matrix[i][j] !=0){
                        points.add(new int[]{i, j});
                        points.add(new int[]{nr, nc});
                    }
                    
                }
            }
        }

        if(points.size() == 0) {
            double avg = (double)sum/(double)num;
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(matrix[i][j] != 0) {
                        if(matrix[i][j] < avg){
                            matrix[i][j] += 1;
                            sum++;
                        }
                        else if(matrix[i][j] > avg){
                            matrix[i][j] -= 1;
                            sum--;
                        }
                    }
                }
            }
            return;
        }
        for(int[] point : points) {
            int r = point[0];
            int c = point[1];

            if(matrix[r][c] != 0) {
                num--;
                sum -= matrix[r][c];
                matrix[r][c] = 0;
            }
        }
    }
}


