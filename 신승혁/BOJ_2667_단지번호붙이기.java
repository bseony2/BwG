import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단지번호붙이기 {
    private static final char HOUSE = '1';
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, 1, -1};
    private static int[] dy = {1, -1, 0, 0};
    private static int N;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

//        _print(map);

        int complexCount = 0; // 단지
        List<Integer> houseCounts = new ArrayList<>(); // 단지당 집갯수
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (_isGoHouse(i, j)) {  // 방문안한 집
                    complexCount++;
                    int houseCount = _bfs(i, j);
                    houseCounts.add(houseCount);

                }
            }
        }
        Collections.sort(houseCounts);

        System.out.println(complexCount);
        for (int housCount : houseCounts) {
            System.out.println(housCount);
        }

    }

    private static int _bfs(int y, int x) {

        int houseCount = 0;
        Queue<Point> queue = new LinkedList<>();

        visited[y][x] = true;
        queue.offer(new Point(y, x));
        houseCount++;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int nx, ny;
            for (int d = 0; d < 4; d++) {
                nx = dx[d] + p.x;
                ny = dy[d] + p.y;


                if (_isGo(ny, nx) && _isGoHouse(ny, nx)) {
                    visited[ny][nx] = true;
                    queue.offer(new Point(ny, nx));
                    houseCount++;
                }
            }
        }

        return houseCount;
    }


    private static boolean _isGoHouse(int y, int x) {
        return map[y][x] == HOUSE && !visited[y][x];
    }

    private static boolean _isGo(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }


    private static void _print(char[][] array) {
        int N = array.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static class Point {
        int y, x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }

}
/*

7
0110100
0110101
1110101
0000111
0100000
0111110
0111000


* */