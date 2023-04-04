import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마법사상어와토네이도 {

<<<<<<< HEAD
=======
    //static int[][] matrix;
>>>>>>> 29d3847c0f2db277910b4d4a270903ca46381317
    static int N;
    static int[] dr = new int[] {0, 1, 0, -1}; // 왼쪽, 아래, 오른쪽, 위
    static int[] dc = new int[] {-1, 0, 1, 0}; // 왼쪽, 아래, 오른쪽, 위

    static int[] rate = new int[] {1, 1, 2, 2, 7, 7, 10, 10, 5, 55};
    static int[][]spreadR = new int[][] {
        {1, -1, 2, -2, 1, -1, 1, -1, 0, 0} // 왼쪽으로 갈 때
        ,{0, 0, 1, 1, 1, 1, 2, 2, 3, 2}
        ,{1, -1, 2, -2, 1, -1, 1, -1, 0, 0}
        ,{0, 0, -1, -1, -1, -1, -2, -2, -3, -2}};
    static int[][]spreadC = new int[][] {
        {0, 0, -1, -1, -1, -1, -2, -2, -3, -2}
        ,{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}
        ,{0, 0, 1, 1, 1, 1, 2, 2, 3, 2}
        ,{-1, 1, -2, 2, -1, 1, -1, 1, 0, 0}};
    static boolean[][] isVisited;
    static public void main(String[] args) throws IOException{
        int initSum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] matrix = new int[N][N];
        isVisited = new boolean[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                int current = Integer.parseInt(st.nextToken());
                matrix[i][j] = current;
                initSum += current;
            }
        }

        int r = N / 2;
        int c = N / 2;
        int d = 0;

        while(r != 0 || c != 0) {
            isVisited[r][c] = true;

            int spreadedSand = 0;

            for(int i=0; i<9; i++) {
                int nr = r + spreadR[d][i];
                int nc = c + spreadC[d][i];

                int nextVal = (matrix[r + dr[d]][c + dc[d]] * rate[i] / 100);
                spreadedSand += nextVal;
                if(!isValid(nr, nc)) continue;
<<<<<<< HEAD
=======
                //System.out.println("r : " + r + " c : " + c + " d : " + d);
>>>>>>> 29d3847c0f2db277910b4d4a270903ca46381317
                
                matrix[nr][nc] += nextVal;
            }
            
            int nr = r + spreadR[d][9];
            int nc = c + spreadC[d][9];
            if(isValid(nr, nc)) {
                matrix[nr][nc] += matrix[r + dr[d]][c + dc[d]] - spreadedSand;
            }

<<<<<<< HEAD
=======
            if(r == 0 && c == 0) break;
>>>>>>> 29d3847c0f2db277910b4d4a270903ca46381317
            r = r + dr[d];
            c = c + dc[d];
            matrix[r][c] = 0;
            if( !isVisited[r + dr[(d+1)%4]][c + dc[(d+1)%4]] ) {
                d = (d + 1) % 4;
            }
<<<<<<< HEAD
=======
            // for(int i=0; i<N; i++) {
            //     System.out.println();
            //     for(int j=0; j<N; j++) {
            //         System.out.print(matrix[i][j] + " ");
            //     }
            // }
>>>>>>> 29d3847c0f2db277910b4d4a270903ca46381317
        }

        

        int sum = 0;
        for(int i=0; i<N; i++) {
            sum += Arrays.stream(matrix[i]).sum();
        }
        System.out.println(initSum - sum);
    }

    static boolean isValid(int r, int c) {
        return r>=0 && c>=0 && r<N && c<N;
    }
}