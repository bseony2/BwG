import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class 집합의표현 {

    static int n;
    static int m;
    static int[] parent;
    static StringTokenizer st;
    static int a;
    static int b;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nAndM =  br.readLine().split(" ");
        n = Integer.parseInt(nAndM[0]);
        m = Integer.parseInt(nAndM[1]);
        parent = new int[n+1];
        Arrays.fill(parent, -1);


        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int sign = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            execute(sign);
        }
    }

    static void execute(int sign) {
        switch(sign) {
            case 0 :
                union(a, b);
                break;
            case 1 :
                isUnion(a, b);
                break;
        }
    }

    static int find(int x) {
        if(parent[x] < 0)
            return x;
        else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;

        if(x < y) {
            parent[x] += parent[y];
            parent[y] = x;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    static void isUnion(int x, int y) {
        System.out.println(find(x) == find(y) ? "YES" : "NO");
    }
}
