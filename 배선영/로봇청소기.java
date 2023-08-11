import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기 {
    static int[][] matrix;
    static boolean[][] isCleaned;
    static int[] dr = new int[] {-1, 0, 1, 0}; //URDL
    static int[] dc = new int[] {0, 1, 0, -1};
    static int answer = 0;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        isCleaned = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                int value = Integer.parseInt(st.nextToken());
                matrix[i][j] = value;
                if(value == 1) isCleaned[i][j] = true;
            }
        }
        doClean(r, c, d);
        System.out.println(answer);
    }

    static void doClean(int r, int c, int d) {

        while(true) {
            if(matrix[r][c] == 0 && !isCleaned[r][c]) {
                answer += 1;
                isCleaned[r][c] = true;
            }
            boolean dirtyExist = false;
            for(int i=0; i<4; i++) {
                d = (d+3) % 4;  // 반시계 방향 90도 회전
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(!isCleaned[nr][nc]) {    // 주변에 더러운 곳 발견
                    r = nr;
                    c = nc;
                    dirtyExist = true;
                    break;
                }
            }

            if(!dirtyExist) {   // 주변에 더러운 곳이 없는 경우
                int nd = (d+2) % 4; // 후진 방향
                int nr = r + dr[nd];
                int nc = c + dc[nd];
                if(nr == 0 || nc == 0 || nr == N-1 || nc == M-1 || matrix[nr][nc] == 1) return;
                
                r = nr;
                c = nc;
            }
        }
    }
}
