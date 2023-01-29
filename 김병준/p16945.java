/**
 *
 * BOJ - 매직 스퀘어로 변경하기
 * https://www.acmicpc.net/problem/16945
 *
 * 예제에 있는 정답은 모두 맞으나, 틀렸습니다.
 */

import java.util.Scanner;

public class p16945 {

    final static int N = 3;
    static int ans = 1000;
    static boolean[] checked = new boolean[10];
    static int[][] arr = new int[N][N];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0);
        System.out.println(ans);
    }
    private static void dfs(int depth, int cost) {

        if(depth == 9 && isMagicSquare()) {
            ans = Math.min(ans, cost);
            return;
        }

        int x = depth / 3;
        int y = depth % 3;

        for(int num = 1; num <= 9; num++) {
            if(!checked[num]) {
                int temp = arr[x][y];
                checked[num] = true;
                arr[x][y] = num;
                dfs(depth + 1, cost + Math.abs(temp - num));
                checked[num] = false;
                arr[x][y] = temp;
            }
        }
    }
    private static boolean isMagicSquare() {

        int rowSum0 = arr[0][0] + arr[0][1] + arr[0][2];
        int rowSum1 = arr[1][0] + arr[1][1] + arr[1][2];
        int rowSum2 = arr[2][0] + arr[2][1] + arr[2][2];

        int colSum0 = arr[0][0] + arr[1][0] + arr[2][0];
        int colSum1 = arr[0][1] + arr[1][1] + arr[2][1];
        int colSum2 = arr[0][2] + arr[1][2] + arr[2][2];

        int diagonalSum0 = arr[0][0] + arr[1][1] + arr[2][2];
        int diagonalSum1 = arr[2][0] + arr[1][1] + arr[0][2];

        if(rowSum0 == colSum0 && rowSum1 == colSum1 && rowSum2 == colSum2 && diagonalSum0 == diagonalSum1) return true;
        else return false;
    }
}
