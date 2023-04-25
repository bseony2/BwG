import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

public class 최장경로 {

    static int N;
    static int M;
    static boolean[][] map;
    static boolean[] isVisited;
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {

            st = new StringTokenizer(br.readLine(), " ");
            answer = Integer.MIN_VALUE;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new boolean[N+1][N+1];

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[x][y] = true;
                map[y][x] = true;
            }

            for(int i=1; i<N+1; i++) {
                isVisited = new boolean[N+1];
                isVisited[i] = true;
                dfs(1, i);
            }

            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void dfs(int depth, int i) {

        for(int j=0; j< N+1; j++) {
            if(map[i][j] && !isVisited[j]) {
                isVisited[j] = true;
                dfs(depth+1, j);
                isVisited[j] = false;
            }
        }

        answer = answer > depth ? answer : depth;
    }
}
