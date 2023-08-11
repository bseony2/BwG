import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Easy_2018 {
    static int N;
    static int[][] matrix;
    static int answer = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        nPr(0);
        
        System.out.println(answer);
    }

    static void nPr(int depth) {
        if(depth == 5) {
            setAnswer();
            return;
        }

        int[][] temp = new int[N][N];
        for(int i=0; i<N; i++) {
            temp[i] = matrix[i].clone();
        }

        for(int i=0; i<4; i++) {
            move(i);
            nPr(depth+1);

            for(int j=0; j<N; j++) {
                matrix[j] = temp[j].clone();
            }
        }
    }

    static void move(int d) {
        switch(d) {
            ///////////////////// 위로 
            case 0:
                for(int i=0; i<N; i++) {
                    int block = 0;
                    int index = 0;
                    for(int j=0; j<N; j++) {
                        if(matrix[j][i] == 0) continue;
                        if(matrix[j][i] != block) {
                            int temp = matrix[j][i];
                            matrix[j][i] = 0;
                            matrix[index++][i] = temp;
                            block = temp;
                        } else {
                            matrix[index-1][i] = block*2;
                            matrix[j][i] = 0;
                            block = 0;
                        }
                    }
                }
            break;

            ///////////////////// 왼쪽으로
            case 1:
                for(int i=0; i<N; i++) {
                    int block = 0;
                    int index = 0;
                    for(int j=0; j<N; j++) {
                        if(matrix[i][j] == 0) continue;
                        if(matrix[i][j] != block) {
                            int temp = matrix[i][j];
                            matrix[i][j] = 0;
                            matrix[i][index++] = temp;
                            block = temp;
                        } else {
                            matrix[i][index-1] = block*2;
                            matrix[i][j] = 0;
                            block = 0;
                        }
                    }
                }
            break;

            ///////////////////// 아래로
            case 2:
                for(int i=0; i<N; i++) {
                    int block = 0;
                    int index = N-1;
                    for(int j=N-1; j>=0; j--) {
                        if(matrix[j][i] == 0) continue;
                        if(matrix[j][i] != block) {
                            int temp= matrix[j][i];
                            matrix[j][i] = 0;
                            matrix[index--][i]  = temp;
                            block = temp;
                        } else {
                            matrix[index+1][i] = block*2;
                            matrix[j][i] = 0;
                            block = 0;
                        }
                    }
                }
            break;

            ///////////////////// 오른쪽으로
            case 3:
                for(int i=0; i<N; i++) {
                    int block = 0;
                    int index = N-1;
                    for(int j=N-1; j>=0; j--) {
                        if(matrix[i][j] ==0) continue;
                        if(matrix[i][j] != block) {
                            int temp = matrix[i][j];
                            matrix[i][j] = 0;
                            matrix[i][index--] = temp;
                            block = temp;
                        } else {
                            matrix[i][index+1] = block*2;
                            matrix[i][j] = 0;
                            block = 0;
                        }
                    }
                }
            break;

            /////////////////////
        }
    }

    static void setAnswer() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                answer = answer > matrix[i][j] ? answer : matrix[i][j];
            }
        }
    }
        
}