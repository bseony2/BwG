import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class 특정한최단경로 {

    static ArrayList<ArrayList<int[]>> linkStatus = new ArrayList<>();
    static int N;
    static int E;

    static int dijkstra(int start, int dest) {
        boolean[] isVisited = new boolean[N + 1];
        int[] distance = new int[N + 1];
        for(int i=1; i<N+1; i++) {
            distance[i] = 200000000;
        }
        distance[start] = 0;
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [노드, 거리]
        queue.offer(new int[] {start, 0});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int vertex = current[0];
            
            if(!isVisited[vertex]) {
                isVisited[vertex] = true;
                if(isVisited[dest]) break;
                for(int[] arr : linkStatus.get(vertex)) {
                    int nVertex = arr[0];
                    int nCost = arr[1];

                    if(!isVisited[nVertex] && distance[nVertex] > distance[vertex] + nCost) {
                        distance[nVertex] = distance[vertex] + nCost;
                        queue.add(new int[] {nVertex, distance[nVertex]});
                    }
                }
            }
        }

        return distance[dest];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i=0; i<=N; i++) {
            linkStatus.add(new ArrayList<int[]>());
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            linkStatus.get(start).add(new int[] {dest, cost});
            linkStatus.get(dest).add(new int[] {start, cost});
        }

        st = new StringTokenizer(br.readLine(), " ");
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
        int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
        
        int answer = (res1 >= 200000000 && res2 >= 200000000) ? -1 : Math.min(res1, res2);

        System.out.println(answer);

        br.close();
    }
}