package w1;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class p4693 {

    private static int W, H;
    private static int[][] board;
    private static boolean[][] visited;
    private static String[] line;

    private static int[] dx = {-1, -1, 0, 1, 1, 1, -1, 0};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        while (true) {

            Queue<Point> q = new LinkedList<>();

            // input Value setting
            if (inputValueSetting(br, q)) break;

            System.out.println(soulution());
        }
    }

    private static int soulution() {
        int ans = 0;
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                if (!visited[i][j] && board[i][j] != 0) {
                    bfs(i, j);
                    ans += 1;
                }
            }
        }
        return ans;
    }

    private static boolean inputValueSetting(BufferedReader br, Queue<Point> q) throws IOException {
        // input 값 세팅
        line = br.readLine().split(" ");
        H = Integer.parseInt(line[0]);
        W = Integer.parseInt(line[1]);

        if (W == 0 && H == 0) return true;

        board = new int[W][H];
        visited = new boolean[W][H];

        for (int i = 0; i < W; i++) {
            line = br.readLine().split(" ");
            for (int j = 0; j < H; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                if (board[i][j] == 1) {
                    q.add(new Point(i, j));
                }
            }
        }
        return false;
    }

    private static void bfs(int x, int y) {

        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));

        while (!q.isEmpty()) {
            Point p = q.poll();
            x = p.x;
            y = p.y;
            for (int dir = 0; dir < 8; ++dir) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (isInRange(nx, ny) && board[nx][ny] == 1 && visited[nx][ny] == false) {
                    q.offer(new Point(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    // 인덱스가 배열의 범위인지 판단.
    private static boolean isInRange(int x, int y) {
        if (0 > x || x >= W || 0 > y || y >= H) return false;
        else return true;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}