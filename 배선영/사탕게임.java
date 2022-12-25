import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class 사탕게임 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int answer = 1;
    static int N;
    static char[][] matrix;
    static public void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        matrix = new char[N][N];
        for(int i=0; i<N; i++) {
            matrix[i] = br.readLine().toCharArray();
        }

        for(int y = 0; y< N ; y++) {
            for(int x = 0; x < N; x++) {

                if(x + 1 < N) { //오른쪽 값과 바꾸기
                    char temp = matrix[y][x];
                    matrix[y][x] = matrix[y][x+1];
                    matrix[y][x+1] = temp;
                    check();
                    matrix[y][x+1] = matrix[y][x];
                    matrix[y][x] = temp;
                }
                
                if(y + 1 < N) { //아래 값과 바꾸기
                    char temp = matrix[y][x];
                    matrix[y][x] = matrix[y+1][x];
                    matrix[y+1][x] = temp;
                    check();
                    matrix[y+1][x] = matrix[y][x];
                    matrix[y][x] = temp;
                }
                
            }
        }
        System.out.print(answer);
    }

    static void check() {
        for(int y = 0; y<N - 1; y++) {
            int value = 1;
            for(int x = 0; x < N - 1; x++) {
                if(matrix[y][x] == matrix[y][x+1]) {
                    value++;
                }
                else  {
                    answer = answer > value ? answer : value;
                    value = 1;
                }

                if(x == N-2) {
                    answer = answer > value ? answer : value;
                }
            }
        }

        for(int x = 0; x < N - 1; x++) {
            int value = 1;
            for(int y = 0; y < N - 1; y++) {
                if(matrix[y][x] == matrix[y+1][x]) {
                    value++;
                }
                else {
                    answer = answer > value ? answer : value;
                    value = 1;
                }

                if(y == N -2) {
                    answer = answer > value ? answer : value;
                }
            }
        }
    }
}