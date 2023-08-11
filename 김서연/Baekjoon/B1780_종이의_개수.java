package baekjoon.resucrsion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1780_종이의_개수 {
    static int[][] arr;
    static int positive;
    static int zero;
    static int negative;

    // 1. 전체 동일원소 체크
    //    1) 기준값 : [0][0], 다르면 재귀호출
    // 2. 재귀
    //    1) 3 * 3 으로 배열 나눔, but, 길이가 1이면 cnt
    //    2) 1/3씩 인덱스 확인하는데, -> 여기서 재귀호출

    // 구획 9개로 나누어 재귀호출
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        } // 입력값 배열 세팅

        partition(0, 0, N);

        System.out.println(negative + "\n" + zero + "\n" + positive);
    }

    static void partition(int row, int col, int size) {
        // 같은색상이면 cnt++ 후 재귀종료
        if (colorCheck(row, col, size)) {
            if (arr[row][col] == 1) positive++;
            else if (arr[row][col] == 0) zero++;
            else negative++;

            return;
        }

        int partSize = size / 3;

        // 9분할 재귀호출
        partition(row, col, partSize);
        partition(row, col + partSize, partSize);
        partition(row, col + partSize * 2, partSize);

        partition(row + partSize, col, partSize);
        partition(row + partSize, col + partSize, partSize);
        partition(row + partSize, col + partSize * 2, partSize);

        partition(row + partSize * 2, col, partSize);
        partition(row + partSize * 2, col + partSize, partSize);
        partition(row + partSize * 2, col + partSize * 2, partSize);
    }

    static boolean colorCheck(int row, int col, int size) {
        int init = arr[row][col]; // 인덱스 초기값 세팅

        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (init != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
