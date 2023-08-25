import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 미로타워디펜스 {
    static int N, M = 1;
    static int[] map;
    static int[][] indexMap = new int[4][12];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int score = 0;
    public static void main(String[] args) throws IOException {
        init();

        simulate();

        System.out.println(score);
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N*N];
        int[][] initMap = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                initMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dr = new int[]{0, 1, 0, -1};
        int[] dc = new int[]{-1, 0, 1, 0};
        boolean[][] isVisited = new boolean[N][N];
        int r = N/2, c = N/2;
        isVisited[r][c] = true;
        int index = 0;
        int d = 3;

        while(true) {
            int nd = (d+1) % 4;
            if(isValidPoint(r + dr[nd], c + dc[nd]) && !isVisited[r + dr[nd]][c + dc[nd]]) {
                d = nd;
            }

            int nr = r + dr[d];
            int nc = c + dc[d];

            if(!isValidPoint(nr, nc)) break;

            map[index++] = initMap[nr][nc];
            isVisited[nr][nc] = true;

            r = nr;
            c = nc;
        }

        indexMap[0][0] = 4;
        indexMap[0][1] = 17;
        indexMap[1][0] = 2;
        indexMap[1][1] = 13;
        indexMap[2][0] = 0;
        indexMap[2][1] = 9;
        indexMap[3][0] = 6;
        indexMap[3][1] = 21;

        for(int i=0; i<4; i++) {
            for(int j=2; j<12; j++) {
                indexMap[i][j] = 2*(indexMap[i][j-1])-(indexMap[i][j-2])+8;
            }
        }
    }

    static void simulate() throws IOException {
        while(M-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            attack(d, p);

            boolean isMove = move();

            while(isMove) {
                removeLongMonster();

                isMove = move();
            }

            mpaReNew();
        }
    }

    static void attack(int d, int p) {
        for(int i=0; i<p; i++) {
            int index = indexMap[d][i];
            score += map[index];
            map[index] = 0;
        }
        
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static boolean move() {
        int[] tempMap = new int[N*N];
        int index = 0;
        int lastIndex = 0;

        for(int i=0; i<N*N; i++) {
            int value = map[i];
            if(value != 0) {
                tempMap[index++] = value;
                lastIndex = i;
            }
        }

        map = tempMap;
        return map[lastIndex] == 0;
    }

    static void removeLongMonster() {
        int start = 0;
        int end = 0;
        int size = 1;
        for(int i=1; i<map.length; i++) {
            if(map[start] == map[i]) {
                size += 1;
                end = i;
            }
            else {
                if(size >=4) {
                    removeLongMonster(start, end);
                }
                size = 1;
                start = i;
            }
        }

        if(size >= 4) {
            removeLongMonster(start, N*N-1);
        }
    }

    static void removeLongMonster(int start, int end) {
        score += map[start] * (end - start + 1);
        for(int i=start; i<=end; i++) {
            map[i] = 0;
        }
    }

    static void mpaReNew() {
        int[] tempMap = new int[map.length];

        int start = 0;
        int size = 1;
        int index = 0;
        for(int i=1; i<map.length; i++) {
            if(map[i] == 0) break;
            if(map[start] == map[i]) {
                size += 1;
            }
            else {
                tempMap[index++] = size;
                tempMap[index++] = map[start];
                size = 1;
                start = i;
            }
        }

        if(index < map.length-2) {
            tempMap[index++] = size;
            tempMap[index] = map[start];
        }       

        map = tempMap;
    }
}
