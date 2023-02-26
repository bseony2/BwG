// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.util.StringTokenizer;
// import java.util.PriorityQueue;
// import java.util.List;
// import java.util.ArrayList;

// public class 특정한최단경로 {

//     static ArrayList<ArrayList<int[]>> linkStatus = new ArrayList<>();
//     static int N;
//     static int E;

//     static int dijkstra(int start, int dest) {
//         boolean[] isVisited = new boolean[N + 1];
//         int[] distance = new int[N + 1];
//         for(int i=1; i<N+1; i++) {
//             distance[i] = Integer.MAX_VALUE;
//         }
//         distance[start] = 0;
//         PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]); // [노드, 거리]
//         queue.offer(new int[] {start, 0});

//         while(!queue.isEmpty()) {
//             int[] current = queue.poll();
//             int vertex = current[0];
            
//             if(!isVisited[vertex]) {
//                 isVisited[vertex] = true;
//                 for(int[] arr : linkStatus.get(vertex)) {
//                     int nVertex = arr[0];
//                     int nCost = arr[1];

//                     if(!isVisited[nVertex] && distance[nVertex] > distance[vertex] + nCost) {
//                         distance[nVertex] = distance[vertex] + nCost;
//                         queue.add(new int[] {nVertex, distance[nVertex]});
//                     }
//                 }
//             }

//         }

//         return distance[dest];
//     }

//     public static void main(String[] args) throws IOException {
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         StringTokenizer st = new StringTokenizer(br.readLine(), " ");

//         N = Integer.parseInt(st.nextToken());
//         E = Integer.parseInt(st.nextToken());

//         for(int i=0; i<=N; i++) {
//             linkStatus.add(new ArrayList<int[]>());
//         }

//         for(int i=0; i<E; i++) {
//             st = new StringTokenizer(br.readLine(), " ");

//             int start = Integer.parseInt(st.nextToken());
//             int dest = Integer.parseInt(st.nextToken());
//             int cost = Integer.parseInt(st.nextToken());

//             linkStatus.get(start).add(new int[] {dest, cost});
//             linkStatus.get(dest).add(new int[] {start, cost});
//         }

//         st = new StringTokenizer(br.readLine(), " ");
//         int v1 = Integer.parseInt(st.nextToken());
//         int v2 = Integer.parseInt(st.nextToken());

//         int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
//         int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);
        
//         int answer = (res1 >= 200000000 && res2 >= 200000000) ? -1 : Math.min(res1, res2);

//         System.out.println(answer);

//         br.close();
//     }
// }

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Arrays;

public class 특정한최단경로 {

    static ArrayList<ArrayList<Node2>> linkStatus = new ArrayList<ArrayList<Node2>>();
    static int N;
    static int E;
    static boolean[] isVisited;
    static int[] distance;
    static final int INF = 200000000;

    static int dijkstra(int start, int dest) {
        Arrays.fill(distance, INF);
        Arrays.fill(isVisited, false);

        PriorityQueue<Node2> pq = new PriorityQueue<>((a, b) -> a.weight = b.weight);
        boolean[] check = new boolean[N + 1];
        pq.offer(new Node2(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Node2 curNode2 = pq.poll();
            int cur = curNode2.end;
            
            if (!check[cur]) {
                check[cur] = true;
                if(check[dest]) break;
                for (Node2 Node2 : linkStatus.get(cur)) {
                    if (!check[Node2.end] && distance[Node2.end] > distance[cur] + Node2.weight) {
                        distance[Node2.end] = distance[cur] + Node2.weight;
                        pq.add(new Node2(Node2.end, distance[Node2.end]));
                    }
                }
            }
        }

        return distance[dest];
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        linkStatus = new ArrayList<>();
        distance = new int[N + 1];
        isVisited = new boolean[N + 1];

        Arrays.fill(distance, INF);

        for (int i = 0; i <= N; i++) {
            linkStatus.add(new ArrayList<>());
        }

        // 양방향 인접 리스트 구현.
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            // start에서 end로 가는 weight (가중치)
            linkStatus.get(start).add(new Node2(end, weight));
 
            // end에서 start로 가는 weight (가중치)
            linkStatus.get(end).add(new Node2(start, weight));
        }

        // 반드시 거쳐야 하는 정점.
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        // 1 -> v1 -> v2 -> N으로 가는 경우
        int res1 = 0;
        res1 += dijkstra(1, v1);
        res1 += dijkstra(v1, v2);
        res1 += dijkstra(v2, N);

        // 1 -> v2 -> v1 -> N으로 가는 경우
        int res2 = 0;
        res2 += dijkstra(1, v2);
        res2 += dijkstra(v2, v1);
        res2 += dijkstra(v1, N);

        int ans = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Node2 implements Comparable<Node2> {
    int end;
    int weight;
 
    Node2(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }
 
    @Override
    public int compareTo(Node2 o) {
        return weight - o.weight;
    }
 
}