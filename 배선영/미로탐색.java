import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 미로탐색 {

    static int N;
    static int M;

    static int[][] road;
    static int[][] dis;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        road = new int[N][M];
        dis = new int[N][M];
        dis[0][0] = 1;

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                road[i][j]= Integer.parseInt(input[j]);
            }
        }

        bfs();
        System.out.println(dis[N-1][M-1]);
    }

    static void bfs() {

        int[] dr = new int[]{-1, 0, 1, 0};  // U R D L
        int[] dc = new int[]{0, 1, 0, -1};

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0,0});

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr<0 || nc<0 || nr>=N || nc>=M) continue;
                if(road[nr][nc] != 1) continue;
                if(dis[nr][nc] != 0) continue;

                dis[nr][nc] = dis[r][c] + 1;
                queue.offer(new int[]{nr, nc});

                if(nr == N && nc == M) queue.clear();
            }
        }
    }


}
