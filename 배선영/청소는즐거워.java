import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 청소는즐거워 {
    static int initSum = 0;
    static int[][] map;
    static int N;
    static int[] dr = new int[]{0, 1, 0, -1};
    static int[] dc = new int[]{-1, 0, 1, 0};
    static int[][] percentR = new int[][]
    {
        {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
        {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
        {1, -1, 2, -2, 0, -1, 1, -1, 1, 0},
        {0, 0, -1, -1, -3, -1, -1, -2, -2, -2}
    };
    static int[][] percentC = new int[][]
    {
        {0, 0, -1, -1, -3, -1, -1, -2, -2, -2},
        {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0},
        {0, 0, 1, 1, 3, 1, 1, 2, 2, 2},
        {-1, 1, -2, 2, 0, -1, 1, -1, 1, 0}
    };
    static double[] percent = new double[]{1, 1, 2, 2, 5, 7, 7, 10, 10};
    public static void main(String[] args) throws IOException {
        init();

        clean();

        print();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                initSum += value;
            }
        }
    }

    static void clean() {
        int r = N/2;
        int c = N/2;
        int d = 3;

        boolean[][] isVisited = new boolean[N][N];
        isVisited[r][c] = true;
        while(r != 0 || c!= 0) {
            int nd = (d + 1) % 4;
            if(isValidPoint(r + dr[nd], c + dc[nd]) && !isVisited[r + dr[nd]][c + dc[nd]]) {
                d = nd;
            }
            int nr = r + dr[d];
            int nc = c + dc[d];
            isVisited[nr][nc] = true;

            spread(r, c, d);

            r = nr;
            c = nc;
        }
    }

    static void spread(int r, int c, int d) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        int initAmt = map[nr][nc];
        int spreadAmt = 0;
        
        for(int i=0; i<10; i++) {
            int amt = 0;
            if(i != 9) {
                amt = (int)((double)initAmt * percent[i] / 100);
            }
            spreadAmt += amt;

            int spreadR = r + percentR[d][i];
            int spreadC = c + percentC[d][i];

            if(!isValidPoint(spreadR, spreadC)) continue;

            if(i != 9) {
                map[spreadR][spreadC] += amt;
            }
            else {
                map[spreadR][spreadC] += initAmt - spreadAmt + amt;
            }
        }
        map[nr][nc] = 0;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static void print() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                initSum -= map[i][j];
            }
        }

        System.out.println(initSum);
    }
}
