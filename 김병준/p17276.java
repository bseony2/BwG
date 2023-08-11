/**
 *
 * BOJ - 배열 돌리기 (17276)
 * https://www.acmicpc.net/problem/17276
 * 구현
 */


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class p17276 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static int[][] rotate45(int[][] arr, int d) {

        int size = arr.length, s = 0, where = 1;
        int[][] result = new int[size][size];

        int sx[] = {(size + 1) / 2 - 1, 0, 0, 0, (size + 1) / 2 - 1};
        int sy[] = {0, 0, (size + 1) / 2 - 1, size - 1, size - 1};
        int dx[] = {0, 1, 1, 1, 0};
        int dy[] = {1, 1, 0, -1, -1};

        if(d < 0) {s = 4; where = -1;}

        for (int i = 0; i < 4; i++) {
            int x = sx[s], y = sy[s];                   // 처음 시작장소
            int nx = sx[s + where], ny = sy[s + where];   // 옮길곳 처음 인덱스
            for (int j = 0; j < size; j++) {
                result[nx][ny] = arr[x][y];
                nx += dx[s + where];
                ny += dy[s + where];
                x += dx[s];
                y += dy[s];
            }
            s += where;
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (result[i][j] == 0) {
                    result[i][j] = arr[i][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        int T;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        while (T-- > 0) {
            int N, d;
            N = sc.nextInt();
            d = sc.nextInt();
            int arr[][] = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            for (int div = 0; div < Math.abs(d) / 45; ++div) {
                arr = rotate45(arr,d);
            }
            printArr(arr);
        }
        bw.flush();
    }
    public static void printArr(int[][] arr) throws IOException {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                bw.write(arr[i][j] + " ");
            }
            bw.write("\n");
        }
    }
}