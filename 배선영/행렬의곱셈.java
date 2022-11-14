import java.util.Scanner;
import java.lang.StringBuilder;

public class 행렬의곱셈 {
    static int N;
    static int[][] basicMtx;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb;
        int[][] answerMtx = new int[N][N];
        N = sc.nextInt();
		long B = sc.nextLong();	// 타입 주의
        basicMtx = new int[N][N];

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                basicMtx[i][j] = sc.nextInt() % 1000;
            }
        }

        answerMtx = devide(basicMtx, B);

        for(int i=0; i<N; i++) {
            sb = new StringBuilder();
            for(int j=0; j<N; j++) {
                sb.append(String.valueOf(answerMtx[i][j]) + " ");
            }
            System.out.println(sb.toString().trim());
        }
        sc.close();

    }

    static int[][] devide(int[][] matrix, long count) {
        if(count == 1) {
            return matrix;
        }

        int[][] middleMtx = devide(matrix, count / 2);

        middleMtx = multiplyMatrix(middleMtx, middleMtx);

        if(count % 2 == 1) {
            middleMtx = multiplyMatrix(middleMtx, basicMtx);
        }

        return middleMtx;
    }

    static int[][] multiplyMatrix(int[][] a, int[][] b) {
        int[][] returnMtx = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                for(int x=0; x<N; x++) {
                    returnMtx[i][j] += a[i][x] * b[x][j];
					returnMtx[i][j] %= 1000;	// 행렬 원소 연산이 끝나면 MOD로 나머지연산
                }
            }
        }
        return returnMtx;
    }
}