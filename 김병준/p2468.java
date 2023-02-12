/**
 * BOJ - 안전 영역
 * https://www.acmicpc.net/problem/2468
 */


import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class p2468 {

    static boolean[][] check;
    static int[][] area = new int[100][100];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0 ,-1};
    static int N;

    public static void bfs(int x, int y){
        check[x][y] = true;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        while (!q.isEmpty()) {
            int cx = q.peek()[0], cy = q.peek()[1];
            q.poll();
            for(int dir = 0; dir < 4; ++dir){
                int nx = cx + dx[dir], ny = cy + dy[dir];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || check[nx][ny]) continue;
                check[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                area[i][j] = sc.nextInt();
            }
        }
        int max = 0;
        for (int rain = 0; rain <= 100; rain++) {
            check = new boolean[100][100];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(rain >= area[i][j]) check[i][j] = true;
                }
            }
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(check[i][j]) continue;
                    bfs(i, j);
                    cnt++;
                }
            }
            max = Math.max(cnt, max);
        }
        System.out.println(max);
    }
}