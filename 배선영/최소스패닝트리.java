import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class 최소스패닝트리 {
    static int parent[];

    static boolean isUnion(int x, int y) {
        return x == parent[y] || y == parent[x] || (parent[y] != -1 && parent[y] == parent[x]);
    }

    static int find(int x) {
        if(parent[x] < 0) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static void union(int inputX, int inputY) {
        int x = find(inputX);
        int y = find(inputY);

        if(x==y) return;

        if(x < y) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = 0;
        int E = 0;
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        ArrayList<int[]> pQ = new ArrayList<int[]>();
        int answer = 0;

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());

            pQ.add(new int[] {v1, v2, edge});
        }

        pQ.sort((a, b) -> a[2] - b[2]);

        parent = new int[V + 1];
        Arrays.fill(parent, -1);
        for(int i = 0 ; i<E; i++) {
            int[] arr = pQ.get(i);

            if(!isUnion(arr[0], arr[1])) {
                union(arr[0], arr[1]);
                answer += arr[2];
            }
        }

        System.out.println(answer);
    }
}


// 3 3
// 1 2 1
// 2 3 2
// 1 3 3