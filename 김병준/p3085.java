/**
 *  백준 (BOJ) _ 사탕게임
 *  https://www.acmicpc.net/problem/3085
 *  브루트포스
 */

import java.io.*;

public class p3085 {

    static int N;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static char[][] board = new char[51][51];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for(int i = 0 ;i < N; i++) {
            int idx = 0;
            String line = br.readLine();
            for (char c : line.toCharArray()) {
                board[i][idx++] = c;
            }
        }
        bw.write(solution()+"\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int dir = 0; dir < 4; ++dir){
                    int x = i + dx[dir];
                    int y = j + dy[dir];
                    if(isInRange(x, y)) {
                        swap(i, j, x, y);
                        ans = Math.max(count(), ans);
                        swap(i, j, x, y);
                    }
                }
            }
        }
        return ans;
    }

    private static int count() {
        int maxCnt = 0;
        // 가로 순회
        for(int garo = 0; garo < N; garo++) {
            int cnt = 0;
            char c = board[garo][0];
            for(int j = 0; j < N; j++) {
                if(c == board[garo][j]) cnt++;
                else{
                    c = board[garo][j];
                    maxCnt = Math.max(cnt, maxCnt);
                    cnt = 1;
                }
            }
            maxCnt = Math.max(cnt, maxCnt);
        }
        // 세로 순회
        for(int sero = 0; sero < N; sero++) {
            int cnt = 0;
            char c = board[0][sero];
            for(int j = 0; j < N; j++) {
                if(c == board[j][sero]) cnt++;
                else{
                    c = board[j][sero];
                    maxCnt = Math.max(cnt, maxCnt);
                    cnt = 1;
                }
            }
            maxCnt = Math.max(cnt, maxCnt);
        }
        return maxCnt;
    }

    private static boolean isInRange(int x, int y) {
        if(0 > x || x >= N || 0 > y || y >= N) return false;
        return true;
    }

    private static void swap(int fromX, int fromY, int toX, int toY) {
        char from = board[fromX][fromY];
        char to   = board[toX][toY];
        board[fromX][fromY] = to;
        board[toX][toY]     = from;
    }
}
