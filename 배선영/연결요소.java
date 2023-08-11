import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class 연결요소 {

    static int N;
    static int M;
    static boolean[] isVisited;
    static int answer;
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        isVisited = new boolean[N+1];
        int answer = 0;
        graph = new ArrayList<ArrayList<Integer>>();
        queue = new LinkedList<>();

        for(int i=0; i<N+1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(int i=1; i<N+1; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                answer += 1;
                queue.offer(i);
                dfs();
            }
        }

        System.out.println(answer);
    }

    static void dfs() {
        while(!queue.isEmpty()) {
            int node = queue.poll();

            for(int nextNode : graph.get(node)) {
                if(!isVisited[nextNode]) {
                    isVisited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }
    }
}
