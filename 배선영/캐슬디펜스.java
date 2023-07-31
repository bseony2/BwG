import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 캐슬디펜스 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, D;
    static int[][] initialMatrix;
    static Queue<int[]> archerPositionQueue = new LinkedList<int[]>();
    static final int NUM_OF_ARCHER = 3;
    static int[][] matrix;
    static int[] archerPosition;
    static int[] dr = new int[]{0, -1, 0};
    static int[] dc = new int[]{-1, 0, 1};
    static int maxScore = 0;
    public static void main(String...args) throws IOException {
        getInput();

        generateArcherPosition(0, 0, new boolean[M]);

        while(!archerPositionQueue.isEmpty()) {
            play();
        }

        System.out.println(maxScore);
    }

    static void getInput() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        initialMatrix = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                initialMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void generateArcherPosition(int depth, int start, boolean[] isVisited) {
        if(depth == NUM_OF_ARCHER) {
            int[] newArcherPosition = new int[NUM_OF_ARCHER];
            int index = 0;
            for(int i=0; i<isVisited.length; i++) {
                if(isVisited[i]) {
                    newArcherPosition[index++] = i;
                }
            }
            archerPositionQueue.add(newArcherPosition);
            return;
        }

        for(int i=start; i<M; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                generateArcherPosition(depth+1, i+1, isVisited);
                isVisited[i] = false;
            }
        }
    }

    static void play() {
        gameInitialize();

        int score = 0;

        for(int i=0; i<N; i++) {
            score += attack();

            move();
        }

        maxScore = score > maxScore ? score : maxScore;
    }

    static void gameInitialize() {
        archerPosition = archerPositionQueue.poll();

        matrix = new int[N][M];
        for(int i=0; i<N; i++) {
            matrix[i] = initialMatrix[i].clone();
        }
    }

    static int attack() {
        int score = 0;
        Queue<int[]>targetList = new LinkedList<>();
        for(int i=0; i<NUM_OF_ARCHER; i++) {
            int[] target = setTarget(N, archerPosition[i]);

            if(target[0] != -1) {
                targetList.add(target);
            }
        }

        for(int[] target : targetList) {
            if(isEnemyLocated(target[0], target[1])) {
                score += 1;
                matrix[target[0]][target[1]] = 0;
            }
        }

        return score;
    }

    static int[] setTarget(int r, int c) {
        int[] target = new int[]{-1, -1};
        int[][] distance = new int[N+1][M];
        Queue<int[]> queue = new LinkedList<>();
        boolean isEnemyFind = false;

        queue.add(new int[]{r, c});

        while(!queue.isEmpty() && !isEnemyFind) {
            int[] point = queue.poll();

            for(int i=0; i<3; i++) {
                int nr = point[0] + dr[i];
                int nc = point[1] + dc[i];

                if(isValidPoint(nr, nc) && isNotVisited(nr, nc, distance)) {
                    if(isEnemyLocated(nr, nc)) {
                        target[0] = nr;
                        target[1] = nc;
                        isEnemyFind = true;
                        break;
                    }
                    else {
                        if(distance[point[0]][point[1]] < D-1) {
                            distance[nr][nc] = distance[point[0]][point[1]] + 1;
                            queue.add(new int[]{nr, nc});
                        }
                    }
                }
            }
        }

        return target;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<M;
    }

    static boolean isEnemyLocated(int r, int c) {
        return matrix[r][c] == 1;
    }

    static boolean isNotVisited(int r, int c, int[][] distance) {
        return distance[r][c] == 0;
    }

    static void move() {
        for(int i=N-2; i>=0; i--) {
            matrix[i+1] = matrix[i].clone();
        }

        for(int i=0; i<M; i++) {
            matrix[0][i] = 0;
        }
    }
}
