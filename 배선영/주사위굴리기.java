import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class 주사위굴리기 {

    static int[][] matrix;
    static int[] dice = new int[]{0, 0, 0, 0, 0, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] dr = {0, 0, 0, -1, 1};
        int[] dc = {0, 1, -1, 0, 0};

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        matrix = new int[N][M];
        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int[] moveArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int move : moveArr) {
            int nr = r + dr[move];
            int nc = c + dc[move];

            if(nr < 0 || nc < 0 || nr >= N || nc >=M) {
                continue;
            }
            moveDice(move);

            if(matrix[nr][nc] == 0) {
                matrix[nr][nc] = dice[2];
            } else {
                dice[2] = matrix[nr][nc];
                matrix[nr][nc] = 0;
            }

            r = nr;
            c = nc;

            System.out.println(dice[5]);
        }
    }

    static void moveDice(int move) {
        int[] newDice = new int[6];
        if(move == 1) {
            newDice = new int[]{dice[0], dice[2], dice[3], dice[5], dice[4], dice[1]};
        } else if(move == 2) {
            newDice = new int[]{dice[0], dice[5], dice[1], dice[2], dice[4], dice[3]};
        } else if(move == 3) {
            newDice = new int[]{dice[5], dice[1], dice[0], dice[3], dice[2], dice[4]};
        } else if(move == 4) {
            newDice = new int[]{dice[2], dice[1], dice[4], dice[3], dice[5], dice[0]};
        }

        dice = newDice;
    }
    

}
