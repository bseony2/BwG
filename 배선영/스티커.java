import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.StringBuilder;
import java.lang.Math;

public class 스티커 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[2][N];
            for(int i=0; i<2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            sb.append(getScore(map)).append("\n");
        }

        System.out.println(sb.toString());
    }

    static int getScore(int[][] map) {
        int N = map[0].length;
        int[][] score = new int[2][N];

        score[0][0] = map[0][0];
        score[1][0] = map[1][0];

        for(int i=1; i<N; i++) {
            score[0][i] = score[1][i-1] + map[0][i];
            score[1][i] = score[0][i-1] + map[1][i];

            if(i == 1) continue;

            score[0][i] = Math.max(score[1][i-2] + map[0][i], score[0][i]);
            score[1][i] = Math.max(score[0][i-2] + map[1][i], score[1][i]);
        }

        return score[0][N-1] > score[1][N-1] ? score[0][N-1] : score[1][N-1];
    }
}
