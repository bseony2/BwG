package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B1260_DFS와_BFS {
    static int[][] checkEdge; // 간선 연결상태 (0 or 1)
    static boolean[] checked; // 체크여부(출력여부)
    static int node; // 정점개수
    static int edge; // 간선개수
    static int start; // 시작정점
    static StringBuilder print; // 최종출력값

    // DFS
    static void dfs(int num) {

        checked[num] = true; // 확인사살완료
        print.append(num + " ");

        // 간선연결 && 출력되어있으면 재귀호출
        for (int i = 1; i <= node; i++) { // node 정점개수만큼 loop
            if (checkEdge[num][i] == 1 && !checked[i]) { // 간선연결O && 출력 == false면 재귀호출
                dfs(i);
            }
        }
    }

    // BFS
    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start); // 시작점 queue 삽입
        checked[start] = true; // start 출력세팅완료
        print.append(start + " ");

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            for (int j = 1; j <= node; j++) {
                if (checkEdge[temp][j] == 1 && !checked[j]) {// 시작점고정, loop로 bfs탐색
                    queue.offer(j);
                    checked[j] = true;
                    print.append(j + " ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        print = new StringBuilder();

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        checkEdge = new int[node + 1][node + 1]; // [0][0] 제외, 좌표값 그대로 입력받음
        checked = new boolean[node + 1]; // 상동, 초기값 false

        // 간선 연결상태 저장(value: 1)
        // 간선 = 양방향
        for (int i = 0; i < edge; i++) {
            StringTokenizer stEdge = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(stEdge.nextToken());
            int y = Integer.parseInt(stEdge.nextToken());

            checkEdge[x][y] = checkEdge[y][x] = 1;
        }

        dfs(start);

        checked = new boolean[node + 1]; // 초기화!!!
        print.append("\n");

        bfs();

        System.out.println(print);
    }
}
