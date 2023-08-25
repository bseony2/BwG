import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무타이쿤 {
    static int N, M;
    static int[][] map, tempMap;
    static boolean[][] nutritionMap, tempNutritionMap;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] dr = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = new int[]{1, 1, 0, -1, -1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        init();

        simulate();

        printAns();
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        nutritionMap = new boolean[N][N];

        nutritionMap[N-1][0] = true;
        nutritionMap[N-1][1] = true;
        nutritionMap[N-2][0] = true;
        nutritionMap[N-2][1] = true;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void simulate() throws IOException {
        while(M-->0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int p = Integer.parseInt(st.nextToken());

            moveAllNutrition(d, p);

            grow();

            cut();
        }
    }

    static void moveAllNutrition(int d, int p) {
        tempNutritionMap = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(nutritionMap[i][j]) {
                    moveNutrition(i, j, d, p);
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                nutritionMap[i][j] = tempNutritionMap[i][j];
            }
        }
    }

    static void moveNutrition(int r, int c, int d, int p) {
        p %= N;
        int nr = (r + dr[d]*p + N) % N;
        int nc = (c + dc[d]*p + N) % N;

        tempNutritionMap[nr][nc] = true;
    }

    static void grow() {
        tempMap = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(nutritionMap[i][j]) {
                    map[i][j] += 1;
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(nutritionMap[i][j]) {
                    growTree(i, j);
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] += tempMap[i][j];
            }
        }
    }

    static void growTree(int r, int c) {
        for(int i=1; i<8; i+=2) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!isValidPoint(nr, nc) || map[nr][nc] <= 0) continue;

            tempMap[r][c] += 1;
        }
    }

    static void cut() {
        tempNutritionMap = new boolean[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!nutritionMap[i][j] && map[i][j] >=2) {
                    map[i][j] -= 2;
                    tempNutritionMap[i][j] = true;
                }
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                    nutritionMap[i][j] = tempNutritionMap[i][j];
            }
        }
    }

    static void printAns() {
        int ans = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }
}
