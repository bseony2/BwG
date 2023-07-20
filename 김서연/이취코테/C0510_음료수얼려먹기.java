package 이취코테;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  05-3 음료수 얼려 먹기
 *  시간제한 : 1초 메모리제한 : 128MB
 *
 *  N X M 크기의 얼음 틀이 있다. 구멍이 뚫려 있는 부분은 0, 칸막이가 존재하는 부분은 1로 표시된다.
 *  구멍이 뚫려 있는 부분끼리 상, 하, 좌, 우로 붙어 있는 경우 서로 연결되어 있는 것으로 간주한다.
 *  이때 얼음 틀의 모양이 주어졌을 때 생성되는 총 아이스크림의 개수를 구하는 프로그램을 작성하시오.
 *  다음의 4 X 5 얼음 틀예시에서는 아이스크림이 총 3개 생성된다.
 *
 *  00110
 *  00011
 *  11111
 *  00000
 *
 *  입력조건
 *      1. 첫 번째 줄 에 얼음 틀의 세로 길이 N과 가로 길이 M이 주어진다. (1 <= N, M <= 1,000)
 *      2. 두 번째 줄부터 N + 1번째 줄까지 얼음 틀의 형태가 주어진다.
 *      3. 이때 구멍이 뚫려있는 부분은 0, 그렇지 않은 부분은 1이다.
 *  출력조건
 *      1. 한 번에 만들 수 있는 아이스크림의 개수를 출력한다.
 */
public class C05_음료수얼려먹기 {
    static int n, m;
    static int[][] matrix = new int[1000][1000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = str.charAt(j) - '0';
            }
        }

        // dfs - 모든 노드(위치)에 대하여 음료수 채우기
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 위치에서 dfs 수행
                if (dfs(i, j)) result += 1;
            }
        }

        System.out.println(result);
    }

    private static boolean dfs(int x, int y) {
        // 입력검증 - 주어진 범위를 벗어나는 경우 즉시 종료
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }

        if (matrix[x][y] == 0) {
            matrix[x][y] = 1; // 방문처리

            // 상, 하, 좌, 우 재귀호출
            // dx, dy
            dfs(x - 1, y);
            dfs(x, y - 1);
            dfs(x + 1, y);
            dfs(x, y + 1);
            return true; // 최종만 count
        }
        return false;
    }
}
