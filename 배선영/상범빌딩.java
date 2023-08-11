import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 상범빌딩 {
    static int L = -1, R = -1, C = -1;
    static char[][][] building;
    static int[][][] time;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<int[]> queue;
    static int[] dh = new int[]{0, 0, 0, 0, -1, 1};
    static int[] dr = new int[]{-1, 0, 1, 0, 0, 0};
    static int[] dc = new int[]{0, 1, 0, -1, 0, 0};
    static int[] exit;
    static final int NUM_OF_DIRECTION = 6;

    public static void main(String...args) throws IOException{
        while(true) {
            initialize();

            if(isGameEnd()) {
                break;
            }

            findExit();

            printAnswer();
        }
    }

    static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        building = new char[L][R][C];
        time = new int[L][R][C];
        queue = new LinkedList<>();

        for(int i=0; i<L; i++) {
            for(int j=0; j<R; j++) {
                building[i][j] = br.readLine().toCharArray();
                for(int k=0; k<C; k++) {
                    if(building[i][j][k] == 'S') {
                        queue.add(new int[]{i, j, k});
                        time[i][j][k] = 1;
                    }

                    if(building[i][j][k] == 'E') {
                        exit = new int[]{i, j, k};
                    }
                }
            }
            br.readLine();
        }
    }

    static boolean isGameEnd() {
        return L==0 && R==0 && C==0;
    }

    static void findExit() {
        boolean isExitFind = false;

        while(!queue.isEmpty() && !isExitFind) {
            int[] currentPoint = queue.poll();

            int h = currentPoint[0];
            int r = currentPoint[1];
            int c = currentPoint[2];

            for(int i=0; i<NUM_OF_DIRECTION; i++) {
                int nh = h + dh[i];
                int nr = r + dr[i];
                int nc = c + dc[i];

                setNextPoint(nh, nr, nc , time[h][r][c] + 1);

                if(isValidPoint(nh, nr, nc) && isExitPoint(nh, nr, nc)) {
                    isExitFind = true;
                    break;
                }
            }
        }
    }

    static void setNextPoint(int h, int r, int c, int nextTime) {
        if(isValidPoint(h, r, c) && isValidSpace(h, r, c) && !isVisited(h, r, c)) {
            time[h][r][c] = nextTime;
            queue.add(new int[]{h, r, c});
        }
    }

    static boolean isValidPoint(int h, int r, int c) {
        return 0 <= h && h<L && 0<=r && r<R && 0<=c && c<C;
    }

    static boolean isValidSpace(int h, int r, int c) {
        return building[h][r][c] != '#';
    }

    static boolean isVisited(int h, int r, int c) {
        return time[h][r][c] > 0;
    }

    static boolean isExitPoint(int h, int r, int c) {
        return building[h][r][c] == 'E';
    }

    static void printAnswer() {
        if(isVisited(exit[0], exit[1], exit[2])) {
            System.out.println("Escaped in " + String.valueOf(time[exit[0]][exit[1]][exit[2]]-1) +  " minute(s).");
        } else {
            System.out.println("Trapped!");
        }
    }
}
