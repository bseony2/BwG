import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Stream;

public class 거짓말 {

    static int N;
    static int M;
    static int[] parent;
    static boolean[] known;
    static int[] party;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        known = new boolean[N + 1];
        Arrays.fill(parent, -1);
        party = new int[M];

        st = new StringTokenizer(br.readLine(), " ");
        int truth = Integer.parseInt(st.nextToken());
        for(int i=0; i<truth; i++) {
            known[Integer.parseInt(st.nextToken())] = true;
        }

        for(int i=0; i<M; i++) {
            String[] tempArr = br.readLine().split(" ");
            party[i] = Integer.parseInt(tempArr[1]);

            for(int j=1; j<tempArr.length - 1; j++) {
                int x = Integer.parseInt(tempArr[j]);
                int y = Integer.parseInt(tempArr[j+1]);

                if(!_isUnion(x, y)) {
                    _union(x, y);
                }
            }
        }

        int answer = 0;
        for(int p : party) {
            if(!known[_find(p)]) answer++;
        }

        System.out.println(answer);
    }

    private static boolean _isUnion(int x, int y) {
        return x == parent[y] || y == parent[x] || (parent[y] != -1 && parent[y] == parent[x]);
    }

    private static void _union(int inputX, int inputY) {
        int x = _find(inputX);
        int y = _find(inputY);

        if(x == y) return;

        if(parent[x] < parent[y]) {
            parent[x] += parent[y];
            parent[y] = x;
            if(known[y]) known[x] = true;
        }
        else {
            parent[y] += parent[x];
            parent[x] = y;
            if(known[x]) known[y] = true;
        }
    }

    private static int _find(int x) {
        if(parent[x] <= 0)  return x;
        else return parent[x] = _find(parent[x]);
    }
}
