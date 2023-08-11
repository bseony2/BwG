package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  Baekjoon 1531 투명
 *  - Silver 5
 *  - simulation
 */
// Question : 좌표의 total 보다 n개의 종이가 부족하다면???
//            -> 가리는 종이의 크기는 생각하지 않음(1X1 크기아님, 걍 통으로 가리는 것)
public class B1531_투명 {
    static int n, m;
    static int[][] arr = new int[101][101];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n= Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = x1; j <= x2; j++) {
                for (int k = y1; k <= y2; k++) {
                    arr[j][k] += 1;
                }
            }
        }

        // 전체 배열 순회
        result = 0;
        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (arr[i][j] > m) result++;
            }
        }

        System.out.println(result);
    }
}
