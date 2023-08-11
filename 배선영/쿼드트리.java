import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.lang.StringBuilder;

public class 쿼드트리 {
    static int[][] matrix;
    static StringBuilder sb = new StringBuilder();
    static int N;
    public static void main(String[] args) throws IOException {
        input();

        devide(0, 0, N, 0);
        System.out.println(sb.toString());
    }

    static void devide(int y, int x, int size, int level) {
        if(size == 1) {
            sb.append(matrix[y][x]);
            if(level == 4) sb.append(")");
            return;
        }

        if(check(y, x, size)) {
            sb.append(String.valueOf(matrix[y][x]));
            if(level == 4) sb.append(")");
            return;
        } else {
            sb.append("(");
        }

        int nextSize = size / 2;

        for(int i=0; i<2; i++) {
            for(int j=0; j<2; j++) {
                devide(y + i*nextSize, x + j*nextSize, nextSize, 2*i + j + 1);
            }
        }

        // devide(y, x, nextSize, 1);
        // devide(y, x + nextSize, nextSize, 2);
        // devide(y + nextSize, x, nextSize, 3);
        // devide(y + nextSize, x + nextSize, nextSize, 4);

        if(level == 4) sb.append(")");
    }

    static boolean check(int y, int x, int size) {
        int flag = matrix[y][x];
        for(int i=y; i<y+size; i++) {
            for(int j=x; j<x+size; j++) {
                if(flag != matrix[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
