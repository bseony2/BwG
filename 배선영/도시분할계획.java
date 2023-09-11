import java.io.*;
import java.util.*;
import java.lang.Math;

public class 도시분할계획 {
    static int N, M;
    static int[] parents;
    static PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
        return a[2] - b[2];
    }); 
    static int sum = 0;
    static int max = Integer.MIN_VALUE;
    public static void main(String...args) throws IOException {
        init();

        while(!queue.isEmpty()) {
            int[] road = queue.poll();
            int A = road[0];
            int B = road[1];
            int C = road[2];

            if(isUniont(A, B)) continue;

            union(A, B);
            sum += C;
            max = Math.max(max, C);
        }

        System.out.println(sum - max);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        Arrays.fill(parents, -1);

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            queue.add(new int[] {A, B, C});
        }
    }

    static int find(int x) {
        if(parents[x] < 0) return x;

        return parents[x] = find(parents[x]);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return;

        if(parents[x] < parents[y]) {
            parents[x] += parents[y];
            parents[y] = x;
        }
        else {
            parents[y] += parents[x];
            parents[x] = y;
        }
    }

    static boolean isUniont(int x, int y) {
        return find(x) == find(y);
    }
}