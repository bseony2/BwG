import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 테트리스블럭안의합최대화하기 {
    static int N, M;
    static int[][] map;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        init();

        simulate();

        System.out.println(ans);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void simulate() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                test(i, j);
            }
        }
    }

    static void test(int r, int c) {        
        firstBlock(r, c);

        secondBlock(r, c);

        thirdBlock(r, c);

        fourthBlock(r, c);

        fifthBlock(r, c);
    }

    static void firstBlock(int r, int c) {
        getScore(r, c, r, c+1, r, c+2, r, c+3);
        getScore(r, c, r+1, c, r+2, c, r+3, c);
    }

    static void secondBlock(int r, int c) {
        getScore(r, c, r+1, c, r, c+1, r+1, c+1);
    }

    static void thirdBlock(int r, int c) {
        getScore(r, c, r+1, c, r+2, c, r+2, c+1);
        getScore(r, c, r, c+1, r, c+2, r-1, c+2);
        getScore(r, c, r, c+1, r+1, c+1, r+2, c+1);
        getScore(r, c, r+1, c, r, c+1, r, c+2);

        getScore(r, c, r, c+1, r-1, c+1, r-2, c+1);
        getScore(r, c, r, c+1, r, c+2, r+1, c+2);
        getScore(r, c, r+1, c, r+2, c, r, c+1);
        getScore(r, c, r+1, c, r+1, c+1, r+1, c+2);
    }

    static void fourthBlock(int r, int c) {
        getScore(r, c, r+1, c, r+1, c+1, r+2, c+1);
        getScore(r, c, r, c+1, r-1, c+1, r-1, c+2);

        getScore(r, c, r+1, c, r, c+1, r-1, c+1);
        getScore(r, c, r, c+1, r+1, c+1, r+1, c+2);
    }

    static void fifthBlock(int r, int c) {
        getScore(r, c, r+1, c, r+2, c, r+1, c+1);
        getScore(r, c, r, c+1, r, c+2, r-1, c+1);
        getScore(r, c, r-1, c+1, r, c+1, r+1, c+1);
        getScore(r, c, r, c+1, r, c+2, r+1, c+1);
    }

    static void getScore(int...args) {
        int score = 0;
        for(int i=0; i<args.length; i+=2) {
            int r = args[i];
            int c = args[i+1];
            if(!isValidPoint(r, c)) return;

            score += map[r][c];
        }

        ans = ans > score ? ans : score;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }
}
