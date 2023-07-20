package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B7569_토마토 {
    /*
    1. 입력과 함께 익은 토마토 -> queue(int[] 배열), 익지 않은 토마토 => nomatoCnt 적재
    2. for문으로 queue size만큼 돌면 하루 증가
    3. queue isEmpty 이면 종료 및 출력

    1. 토마토가 익은 점들을 큐에 넣어준다. (동시 다발적으로 일어나기 때문에 큐를 사용한 BFS)
    2. 익은 토마토 상하좌우를 탐색하며 익지 않은 토마토가 있으면 그 위치를 큐에 넣어준다. (그 위치의 값은 최대 일수를 계산하기 위해 전 위치 +1 을 해준다.)
    3. 큐가 빌때까지 계속해준다.
    4. 전체 토마토들을 탐색하여 익지않은 토마토(0 값) 하나라도 있으면 -1를 출력한다.
    5. 그 외는 최대 일수를 출력한다.

    - 토마토가 모두 익지 못하는 상황 : 익지않은 토마토 주변에 빈자리가 있으면 익지못함 -1
    - queue가 다 비워져야 하루지남 -> cnt로 판별

    - z, x, y
*/
    static int[][][] tomato;
    static Queue<int[]> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dz = {-1, 1, 0, 0, 0, 0};
        int[] dy = {0, 0, -1, 1, 0, 0};
        int[] dx = {0, 0, 0, 0, -1, 1};
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken()); // 상자의 수
        tomato = new int[h][n][m];

        int nomatoCnt = 0; // 익지않은 토마토 개수 (0)
        int days = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    tomato[i][j][k] = Integer.parseInt(st.nextToken());
                    // 1이면 queue에 넣음
                    if (1 == tomato[i][j][k]) {
                        queue.add(new int[] {i, j, k});
                    } else if (0 == tomato[i][j][k]) {
                        nomatoCnt++;
                    }
                }
            }
        }

        // queue가 빌때까지 반복
        // queue rotation마다 일자 증가
        while (nomatoCnt > 0 && !queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int[] current = queue.poll();

                for (int j = 0; j < 6; j++) {
                    int nz = current[0] + dz[j];
                    int ny = current[1] + dy[j];
                    int nx = current[2] + dx[j];

                    if (nz < 0 || ny < 0 || nx < 0 ||  nz >= h || ny >= n || nx >= m || tomato[nz][ny][nx] != 0) {
                        continue;
                    }

                    // 익지않은 토마토의 개수 update X
                    nomatoCnt--;
                    tomato[nz][ny][nx] = 1;
                    queue.add(new int[] {nz, ny, nx}); // 다음 queue rotation 적재
                }
            }
            days++;
        }
        System.out.println(nomatoCnt == 0 ? days : -1);
    }
}
