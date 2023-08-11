import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.util.Arrays;

public class 스타트와링크 {
    static int[][] matrix;
    static int N;
    static boolean[] isVisited;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isVisited = new boolean[N];
        matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        nCr(0, 0);

        System.out.println(answer);
    }

    static void nCr(int depth, int start) {
        if(depth == N/2) {
            cal();
            return;
        }

        for(int i = start; i<N; i++) {

            isVisited[i] = true;
            nCr(depth + 1, i + 1);
            isVisited[i] = false;
        }
    }

    static void cal() {
        int[] start = new int[N/2];
        int[] link = new int[N/2];

        int startIndex = 0;
        int linkIndex = 0;

        for(int i=0; i<N; i++) {
            if(isVisited[i]) {
                start[startIndex++] = i;
            } else {
                link[linkIndex++] = i;
            }
        }
        int startSum = 0;
        for(int i=0; i<start.length -1; i++) {
            for(int j=i+1; j<start.length; j++) {
                startSum += matrix[start[i]][start[j]] + matrix[start[j]][start[i]];
            }
        }

        int linkSum = 0;
        for(int i=0; i<link.length -1; i++) {
            for(int j=i+1; j<link.length; j++) {
                linkSum += matrix[link[i]][link[j]] + matrix[link[j]][link[i]];
            }
        }
        int result = (int)Math.abs(startSum - linkSum);
        answer = answer < result ? answer : result;
    }
}
