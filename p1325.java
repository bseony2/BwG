package w1;

import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class p1325 {

    private static int N, M;
    private static List<Integer> graph[];
    private static int[] outDegree;
    private static String[] line;

    public static void main(String[] args) throws IOException {

        inputValueSetting();

        solution();
    }

    private static void solution() {

        int max = -1;

        // 정답배열 출력
        List<Integer> ans = new ArrayList<>();
        
        // 각각의 컴퓨터에서 BFS를 수행
        for(int i = 1; i <= N; i++) {
            int depth = bfs(i);
            if (max == -1 || depth > max) {
                ans = new ArrayList<>();
                ans.add(i);
                max  = depth;
            } else if(depth == max) {
                ans.add(i);
            }
        }
        
        // 정답배열 출력
        for(int i = 0; i < ans.size(); i++){
            System.out.print(ans.get(i) +" ");
        }
        System.out.println();
    }

    private static int bfs(int start) {

        // 하나의 컴퓨터에서 해킹할 수 있는 컴퓨터의 수
        int depth = 0;

        boolean visited[] = new boolean[N + 1];
        visited[start] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int x = q.poll();
            for (int i = 0; i < graph[x].size(); i++) {
                int nx = graph[x].get(i);
                if(!visited[nx]){
                    visited[nx] = true;
                    q.offer(nx);
                    depth += 1;
                }
            }
        }

        return depth;
    }

    private static void inputValueSetting() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        line = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        graph     = new ArrayList[N+1];
        outDegree = new int[N + 1];

        for(int i = 1; i <= N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < M; i++) {
            line = br.readLine().split(" ");
            int to = Integer.parseInt(line[0]);
            int from = Integer.parseInt(line[1]);

            graph[from].add(to);
        }
    }
}
