import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2667_단지번호붙이기_2 {
    static boolean[][] visited;
    static char[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        visited = new boolean[N][N];


        for (int n = 0; n < N; n++) {
            String line = br.readLine();
            map[n] = line.toCharArray();
        }

//        _print(map);
        int count = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '1' && !visited[i][j]) {
                    visited[i][j] = true;
                    list.add(_bfs(i, j));
                    count++;
                }
            }
        }

        Collections.sort(list);
        System.out.println(count);
        list.stream().forEach(c -> System.out.println(c));

    }

    private static class Point {
        int y;
        int x;

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

    private static int _bfs(int i, int j) {

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(i, j));
        int count = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int nextX;
            int nextY;

            for (int d = 0; d < 4; d++) {
                nextX = p.x + dx[d];
                nextY = p.y + dy[d];

                if (_isGo(nextY, nextX) && !visited[nextY][nextX]
                        && map[nextY][nextX] == '1') {
                    visited[nextY][nextX] = true;
                    queue.offer(new Point(nextY, nextX));
                    count++;
                }

            }


        }
        return count;
    }

    private static boolean _isGo(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    private static void _print(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
}
