import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 배열돌리기4 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, K;
    static int[][] matrix;
    static int[][] initialMatrix;
    static int ans = Integer.MAX_VALUE;
    static ArrayList<int[]> signList = new ArrayList<int[]>();
    static int[] signs;
    public static void main(String...args) throws IOException{
        initialize();

        perm(0, new boolean[K]);

        System.out.println(ans);
    }

    static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        signs = new int[K];

        matrix = new int[N][M];
        initialMatrix = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                initialMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            signList.add(new int[]{r, c, s});
        }
    }

    static void perm(int depth, boolean[] isVisited) {
        if(depth == K) {
            exeedSign();
            return;
        }

        for(int i=0; i<K; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                signs[depth] = i;
                perm(depth+1, isVisited);
                isVisited[i] = false;
            }
        }
    }

    static void exeedSign() {
        matrix = arrayClone();

        for(int i=0; i<signs.length; i++) {
            int[] sign = signList.get(signs[i]);
            int r = sign[0];
            int c = sign[1];
            int s = sign[2];

            spin(r-s-1, c-s-1, r+s-1, c+s-1);
        }

        getAns();
    }

    static int[][] arrayClone() {
        int[][] result = new int[N][M];
        for(int i=0; i<N; i++) {
            result[i] = initialMatrix[i].clone();
        }
        return result;
    }

    static void spin(int r1, int c1, int r2, int c2) {
        if(isLastTrun(r1, c1, r2, c2)) {
            return;
        }

        int initValue = matrix[r1][c1];

        for(int i=r1; i<r2; i++) {
            matrix[i][c1] = matrix[i+1][c1];
        }

        for(int i=c1; i<c2; i++) {
            matrix[r2][i] = matrix[r2][i+1];
        }

        for(int i=r2; i>r1; i--) {
            matrix[i][c2] = matrix[i-1][c2];
        }

        for(int i=c2; i>c1; i--) {
            matrix[r1][i] = matrix[r1][i-1];
            if(i == c1+1) {
                matrix[r1][i] = initValue;
            }
        }

        spin(r1+1, c1+1, r2-1, c2-1);
    }

    static boolean isLastTrun(int r1, int c1, int r2, int c2) {
        return (r1 == r2 && c1 == c2) || (r1+1 == r2 && c1+1 == c2);
    }

    static void getAns() {
        for(int i=0; i<N; i++) {
            int row = 0;
            for(int j=0; j<M; j++) {
                row += matrix[i][j];
            }
            ans = ans < row ? ans : row;
        }
    }
}
