import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class 토마토_7569 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][][] box;
    static Queue<int[]> queue = new LinkedList<>();
    static int H;
    static int N;
    static int M;
    static int[] dr = new int[]{-1, 1, 0, 0, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1, 0, 0};
    static int[] dh = new int[]{0, 0, 0, 0, -1, 1};
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
        H = Integer.parseInt(st.nextToken());
        box = new int[H][N][M];
        for(int k=0; k<H; k++) {
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<M; j++) {
                    box[k][i][j] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }

    static void firstSearch() {
        for(int k=0; k<H; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(isGoodTomato(k, i, j)) {
                        queue.add(new int[]{k, i, j});
                    }
                }
            }
        }
    }

    static boolean isGoodTomato(int h, int r, int c) {
        return box[h][r][c] > 0;
    }

    static void BFS() {
        while(!queue.isEmpty()) {
            int[] currentPoint = queue.poll();
            int h = currentPoint[0];
            int r = currentPoint[1];
            int c = currentPoint[2];

            searchTomatoAround(h, r, c);
        }
    }

    static void searchTomatoAround(int h, int r, int c) {
        for(int i=0; i<dr.length; i++) {
            int nh = h + dh[i];
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(isValidPoint(nh, nr, nc) && isNotGoodTomato(nh, nr, nc)) {
                box[nh][nr][nc] = box[h][r][c] + 1;
                queue.add(new int[]{nh, nr, nc});
            }
        }
    }

    static boolean isValidPoint(int h, int r, int c) {
        return 0<=h && h<H && 0<=r && r<N && 0<=c && c<M;
    }

    static boolean isNotGoodTomato(int h, int r, int c) {
        return box[h][r][c] == 0;
    }

    static int getAnswer() {
        int maxVal = 0;
        for(int k=0; k<H; k++){
            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(isNotGoodTomato(k, i, j)) {
                        return -1;
                    }
                    maxVal = maxVal > box[k][i][j] ? maxVal : box[k][i][j];
                }
            }
        }

        return maxVal - 1;
    }
}