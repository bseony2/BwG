import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 종이의개수 {
    static int minus = 0;
    static int zero = 0;
    static int one = 0;
    static int[][] matrix;
    static int[] result = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        check(0, 0, N);

        System.out.println(result[0] + "\n" + result[1] + "\n" + result[2]);
    }

    static void check(int y, int x, int size) {
        int flag = matrix[y][x];
        if(size == 1) {
            cal(flag);
            return;
        }

        boolean needCheck = false;
        for(int i=y; i<y+size; i++) {
            if(needCheck) {
                break;
            }
            for(int j=x; j<x+size; j++) {
                if(matrix[i][j] != flag) {
                    needCheck = true;
                    break;
                }
            }
        }

        if(!needCheck) {
            cal(flag);
            return;
        }

        int nextSize = size/3;

        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                check(y + nextSize*i, x + nextSize*j, nextSize);
            }
        }
    }

    static void cal(int flag) {
        result[flag + 1]++;
    }
}
