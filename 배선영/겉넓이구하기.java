import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 겉넓이구하기 {

    static int[][] map;
    static int N;
    static int M;

    static int[] dr = new int[] {-1, 1, 0, 0}; // 위, 아래, 오른쪽, 왼쪽
    static int[] dc = new int[] {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int answer = 0;

        for(int n=0; n<N; n++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int m=0; m<M; m++) {
                int value = Integer.parseInt(st.nextToken());
                answer += value;
                map[n][m] = value;
            }
        }

        answer = answer * 6;

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                int top = map[r][c];

                for(int height=1; height<=top; height++) {
                    for(int i=0; i<4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
    
                        if(nr <0 || nc < 0 || nr >=N || nc >= M) continue;

                        if(map[nr][nc] >= height) answer -= 1;
                    }
                    if(height != top) answer -= 1;
                    if(height != 1) answer -= 1;
                }
            }
        }

        System.out.println(answer);
    }
}
