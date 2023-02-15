import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 유기농배추 {
    static int M, N, K;
    static int[][] map;
    static boolean[][] isVisited;
    static int[] dx = new int[] {0, 0, -1, 1};
    static int[] dy = new int[] {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            isVisited = new boolean[N][M];
            int answer = 0;

            for(int k=0; k<K; k++) {
                st = new StringTokenizer(br.readLine());
                int m = Integer.parseInt(st.nextToken());
                int n = Integer.parseInt(st.nextToken());
                map[n][m] = 1;
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(map[i][j] == 1 && !isVisited[i][j]) {
                        dfs(i, j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[] {y, x});

        while(!q.isEmpty()) {
            y = q.peek()[0];
            x = q.peek()[1];
            isVisited[y][x] = true;

            q.poll();

            for(int t=0; t<4; t++) {
                int ny = y + dy[t];
                int nx = x + dx[t];

                if(0 <= ny && ny < N && 0 <= nx && nx <M) {
                    if(!isVisited[ny][nx] && map[ny][nx] == 1){
                        q.add(new int[] {ny, nx});
                    }
                }
            }
        }
    }

    static void dfs(int y, int x) {
        isVisited[y][x] = true;
        for(int t=0; t<4; t++) {
            int ny = y + dy[t];
            int nx = x + dx[t];

            if(0 <= ny && ny < N && 0 <= nx && nx <M) {
                if(!isVisited[ny][nx] && map[ny][nx] == 1){
                    dfs(ny, nx);
                }
            }
        }
    }
}