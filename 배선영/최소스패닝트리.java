import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;

public class 최소스패닝트리 {
    static int parent[];

    static int find(int x) {
        if(parent[x] < 0) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) {
            return;
        }else if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    static boolean isUnion(int x, int y) {
        return find(x) == find(y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        parent = new int[V + 1];
        Arrays.fill(parent, -1);

        ArrayList<Link> linkList = new ArrayList<>();

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            linkList.add(new Link(start, dest, cost));
        }

        linkList.sort((a, b) -> a.cost - b.cost);

        int answer = 0;

        for(int i = 0 ; i<E; i++) {
            Link link = linkList.get(i);

            if(!isUnion(link.start, link.dest)) {
                union(link.start, link.dest);
                answer += link.cost;
            }
        }

        System.out.println(answer);
    }
}

class Link {
    int start;
    int dest;
    int cost;

    public Link(int start, int dest, int cost) {
        this.start = start;
        this.dest = dest;
        this.cost = cost;
    }
}