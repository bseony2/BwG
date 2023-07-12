import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class 토마토 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] box;
    static Queue<int[]> queue = new LinkedList<>();
    static int N;
    static int M;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        getInput();

        firstSearch();

        BFS();

        System.out.println(getAnswer());
    }

    static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        box = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void firstSearch() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isGoodTomato(i, j)) {
                    queue.add(new int[]{i, j});
                }
            }
        }
    }

    static boolean isGoodTomato(int r, int c) {
        return box[r][c] > 0;
    }

    static void BFS() {
        while(!queue.isEmpty()) {
            int[] currentPoint = queue.poll();
            int r = currentPoint[0];
            int c = currentPoint[1];

            searchTomatoAround(r, c);
        }
    }

    static void searchTomatoAround(int r, int c) {
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(isValidPoint(nr, nc) && isNotGoodTomato(nr, nc)) {
                box[nr][nc] = box[r][c] + 1;
                queue.add(new int[]{nr, nc});
            }
        }
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }

    static boolean isNotGoodTomato(int r, int c) {
        return box[r][c] == 0;
    }

    static int getAnswer() {
        int maxVal = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isNotGoodTomato(i, j)) {
                    return -1;
                }
                maxVal = maxVal > box[i][j] ? maxVal : box[i][j];
            }
        }

        return maxVal - 1;
    }
}