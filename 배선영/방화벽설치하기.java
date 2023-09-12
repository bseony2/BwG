import java.io.*;
import java.util.*;

public class 방화벽설치하기 {
    static int N, M;
    static int[][] initMap, copy;
    static int[] dr = new int[] {-1, 0, 1, 0};
    static int[] dc = new int[] {0, 1, 0, -1};
    static int ans = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException{
        init();

        dfs(0, 0, 0, initMap);

        System.out.println(ans);
    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        initMap = new int[N][M];
        copy = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                initMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int r, int c, int cnt, int[][] map) {
        if(cnt == 3) {
            count(map);
            return;
        }

        if(c == M) {
            if(r == N-1) {
                return;
            }

            r += 1;
            c = 0;
        }

        if(isBlank(map, r, c)) {
            int[][] copyMap = mapcopy(map);
            copyMap[r][c] = 1;
            dfs(r, c+1, cnt+1, copyMap);
        }
        dfs(r, c+1, cnt, map);
        
    }

    static int[][] mapcopy(int[][] origin) {
        int[][] result = new int[origin.length][origin[0].length];

        for(int i=0; i<origin.length; i++) {
            result[i] = origin[i].clone();

        }
        return result;
    }

    static void count(int[][] map) {
        onFire(map);

        int blankSpace = getBlank(map);

        ans = ans > blankSpace ? ans : blankSpace;
    }

    static void onFire(int[][] map) {
        boolean[][] isVisited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!isVisited[i][j] && isFire(map, i, j)) {
                    bfs(map, isVisited, i, j);
                }
            }
        }
    }

    static void bfs(int[][] map, boolean[][] isVisited, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        
        queue.add(new int[]{r, c});
        isVisited[r][c] = true;

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0];
            int cc = point[1];

            for(int i=0; i<4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if(!isValidPoint(nr, nc) || isVisited[nr][nc] || !isBlank(map, nr, nc)) continue;

                isVisited[nr][nc] = true;
                map[nr][nc] = 2;
                queue.add(new int[]{nr, nc});
            }
        }
    }

    static int getBlank(int[][] map) {
        int result = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isBlank(map, i, j)) {
                    result += 1;
                }
            }
        }
        return result;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }

    static boolean isFire(int[][] map, int r, int c) {
        return map[r][c] == 2;
    }

    static boolean isFireWall(int[][] map, int r, int c) {
        return map[r][c] == 1;
    }

    static boolean isBlank(int[][] map, int r, int c) {
        return map[r][c] == 0;
    }
}
