import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class 여행가자 {
    
    static int N;
    static int M;
    static int[] parent;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static private boolean _isUnion(int x, int y) {
        return _find(x) == _find(y) || _find(x) == y || _find(y) == x;
    }

    static private void _union(int inputX, int inputY) {
        int x = _find(inputX);
        int y = _find(inputY);

        if(x == y) return;

        if(x < y) {
            parent[x] += parent[y];
            parent[y] = x;
        } else {
            parent[y] += parent[x];
            parent[x] = y;
        }
    }

    static private int _find(int x) {
        if(parent[x] < 0) return x;
        return parent[x] = _find(parent[x]);
    }

    static private void _input() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        Arrays.fill(parent, -1);

        for(int i=1; i<=N; i++) {
            int[] link = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<N; j++) {
                if(link[j] == 1) {
                    _union(i, j+1);
                }
            }
        }
    }

    static private String _print() throws IOException {
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for(int i=0; i<M-1; i++) {
            if(!_isUnion(input[i], input[i+1])) {
                return "NO";
            }
        }

        return "YES";
    }

    public static void main(String[] args) throws IOException {
        _input();
        
        System.out.println(_print());;
    }
}
