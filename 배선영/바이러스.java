import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class 바이러스 {
    static int[] parent;
    static int N;
    static int T;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        T = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        Arrays.fill(parent, -1);

        if(T == 1) {
            System.out.println(0);
            return;
        }
        for(int i=0; i<T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            union(x, y);
        }

        int answer = 0;
        for(int i=2; i<N+1; i++) {
            if(isUnion(1, i)) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) {
            return;
        }

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    static int find(int x) {
        if(parent[x] < 0) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    static boolean isUnion(int x, int y) {
        return find(x) == find(y);
    }
}

