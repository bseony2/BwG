import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class 부분수열의 합 {

    static int answer = 0;
    static int N;
    static int S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] NS = br.readLine().split(" ");
        N = Integer.parseInt(NS[0]);
        S = Integer.parseInt(NS[1]);

        arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(0, 0);
        if(S == 0 )
            System.out.println(answer-1);
        else {
            System.out.println(answer);
        }
    }

    public static void dfs(int depth, int sum) {
        if(depth == N) {
            if(sum == S) {
                answer++;
            }
            return;
        }

        dfs(depth+1, sum + arr[depth]);
        dfs(depth+1, sum);
    }
}
