package graphs.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Baekjoon 11724 연결 요소의 개수 - Silver2
 * - Graph, DFS, BFS
 *
 * - 무방향 그래프
 */
public class B11724_연결_요소의_개수 {
    static int V;
    static int E;
    static int[][] graph = new int[1001][1001]; // 정점 V 범위 (1 ≤ V ≤ 1,000, 0 ≤ E ≤ V×(V-1)/2)
    static boolean[] isVisited = new boolean[1001]; // 정점

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rslt = 0; // 결과값: 연결 요소의 개수(Connected Components)

        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        E = Integer.parseInt(st.nextToken()); // 간선의 개수

        // 간선 연결
        int u, v; // 간선의 양 끝점
        for (int i = 0; i < E; i++) { // 간선 개수만큼 loop
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());

            graph[u][v] = graph[v][u] = 1; // 무방향그래프, 연걸값 : 1, 연결X : 0
        }

        // dfs 탐색
        for (int i = 1; i <= V; i++) { // index 확인 (정점 1부터 시작)
            // 방문하지 않은 노드만 dfs 진행
            if (isVisited[i] == false) {
                dfs(i);
                rslt++;
            }
        }

        System.out.println(rslt);
        br.close();
    }

    static void dfs(int V1) {
        if (isVisited[V1]) return;
        else {
            isVisited[V1] = true; // 방문함
            for (int i = 1; i <= V; i++) {
                if (graph[V1][i] == 1) {
                    dfs(i);
                }
            }
        }
    }
}
