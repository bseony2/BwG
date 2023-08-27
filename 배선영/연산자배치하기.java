import java.io.*;
import java.util.*;

public class 연산자배치하기 {
    static int N;
    static int[] arr;
    static int[] signs = new int[3];
    static int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        init();

        dfs(0, arr[0]);

        System.out.println(minValue + " " + maxValue);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        signs[0] = Integer.parseInt(st.nextToken());
        signs[1] = Integer.parseInt(st.nextToken());
        signs[2] = Integer.parseInt(st.nextToken());
    }

    static void dfs(int depth, int value) {
        if(depth == N-1) {
            minValue = minValue > value ? value : minValue;
            maxValue = maxValue > value ? maxValue : value;
            return;
        }

        for(int i=0; i<3; i++) {
            if(signs[i] > 0) {
                signs[i] -= 1;
                if(i == 0) {
                    dfs(depth + 1, value + arr[depth+1]);
                }
                else if(i == 1) {
                    dfs(depth + 1, value - arr[depth+1]);
                }
                else {
                    dfs(depth + 1, value * arr[depth+1]);
                }
                signs[i] += 1;
            }
        }
    }
}
