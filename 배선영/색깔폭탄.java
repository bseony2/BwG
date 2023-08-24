import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.lang.Comparable;

public class 색깔폭탄 {

    static int[][] map, tempMap;
    static int score = 0;
    static int N, M;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        init();

        simulate();

        System.out.println(score);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        tempMap = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void simulate() {
        while(true) {
            Dummy selectedDummy = selectDummy();

            if(selectedDummy == null) return;

            explodeDummy(selectedDummy);

            gravity();

            spin();

            gravity();
        }
    }

    static Dummy selectDummy() {
        boolean[][] isVisited = new boolean[N][N];
        PriorityQueue<Dummy> pq = new PriorityQueue<>();

        for(int i=N-1; i>=0; i--) {
            for(int j=0; j<N; j++) {
                if(!isVisited[i][j] && isColorBomb(i, j)) {
                    isVisited[i][j] = true;
                    Dummy dummy = bfs(isVisited, i, j);
                    if(dummy.size < 2 || dummy.colorList.size() == 0) continue;
                    pq.add(dummy);
                }
            }
        }

        return pq.poll();
    }

    static Dummy bfs(boolean[][] isVisited, int r, int c) {
        Dummy dummy = new Dummy(map[r][c]);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{r, c});

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int cr = point[0];
            int cc = point[1];
            if(isColorBomb(cr, cc)) {
                dummy.colorList.add(point);
            } else {
                dummy.red.add(point);
            }
            dummy.size += 1;

            for(int i=0; i<4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                if(!isValidPoint(nr, nc) || isVisited[nr][nc] || isStone(nr, nc) || (dummy.color != map[nr][nc] && !isRedBomb(nr, nc))) continue;

                isVisited[nr][nc] = true;
                queue.add(new int[]{nr, nc});
            }
        }

        for(int[] point : dummy.red) {
            isVisited[point[0]][point[1]] = false;
        }

        return dummy;
    }

    static void explodeDummy(Dummy dummy) {
        score += dummy.size * dummy.size;
        for(int[] point : dummy.colorList) {
            explode(point[0], point[1]);
        }
        for(int[] point : dummy.red) {
            explode(point[0], point[1]);
        }
    }

    static void explode(int r, int c) {
        map[r][c] = -2;
    }

    static void gravity() {
        for(int c=0; c<N; c++) {
            for(int r=N-2; r>=0; r--) {
                if(!isBomb(r, c)) continue;
                int nr = r;

                while(isValidPoint(nr+1, c) && isBlankSpace(nr+1, c) && !isStone(nr+1, c)) {
                    nr += 1;
                }

                if(nr == r) continue;

                map[nr][c] = map[r][c];
                map[r][c] = -2;
            }
        }
    }

    static void spin() {
        for(int c=N-1; c>=0; c--) {
            for(int r=0; r<N; r++) {
                tempMap[N-1-c][r] = map[r][c];
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                map[r][c] = tempMap[r][c];
            }
        }
    }

    static boolean isGameEnd() {
        boolean result = true;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j] >= 0) return false;
            }
        }
        return result;
    }

    static boolean isBomb(int r, int c) {
        return map[r][c] >= 0;
    }

    static boolean isRedBomb(int r, int c) {
        return map[r][c] == 0;
    }

    static boolean isColorBomb(int r, int c) {
        return map[r][c] >= 1;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static boolean isStone(int r, int c) {
        return map[r][c] == -1;
    }

    static boolean isBlankSpace(int r, int c) {
        return map[r][c] == -2;
    }

    static class Dummy implements Comparable<Dummy> {
        ArrayList<int[]> red;
        ArrayList<int[]> colorList;
        int color;
        int size;

        public Dummy(int color) {
            this.color = color;
            red = new ArrayList<>();
            colorList = new ArrayList<>();
            this.size = 0;
        }

        public int compareTo(Dummy dummy) {
            if(this.size != dummy.size) return dummy.size - this.size;
            if(this.red.size() != dummy.red.size()) return this.red.size() - dummy.red.size();
            if(this.colorList.get(0)[0] != dummy.colorList.get(0)[0]) return dummy.colorList.get(0)[0] - this.colorList.get(0)[0];
            return this.colorList.get(0)[1] - dummy.colorList.get(0)[1];
        }
    }
}
