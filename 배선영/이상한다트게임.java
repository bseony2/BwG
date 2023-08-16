import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 이상한다트게임 {
    static int[][] matrix;
    static boolean[][] needDelete;
    static Queue<int[]> spinQueue = new LinkedList<>();
    static int n,m,q;
    static int x,d,k;
    static int[] dr = new int[]{-1, 0, 0, 1};
    static int[] dc = new int[]{0, 1, -1, 0};

    public static void main(String...args) throws IOException {
        getInput();

        while(!spinQueue.isEmpty()) {
            int[] sign = spinQueue.poll();
            x = sign[0];
            d = sign[1];
            k = sign[2];
            process();
        }

        System.out.println(getAns());
    }

    static void getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            spinQueue.add(new int[]{x, d, k});
        }
    }

    static void process() {
        for(int i=x-1; i<n; i+=x) {
            turn(i);
        }
        

        deleteSameValue();
    }

    static void turn(int index) {
        if(d == 0) {
            turnRight(index);    // 시계방향
        }
        else if (d == 1) {
            turnLeft(index);     // 반시계방향
        }
    }

    static void turnRight(int index) {
        int[] baseArr = matrix[index];
        int[] newArr = new int[m];

        //[0, 1, 2, 3, 4]
        System.arraycopy(baseArr, m-k, newArr, 0, k);
        System.arraycopy(baseArr, 0, newArr, k, m-k);
        matrix[index] = newArr;
    }

    static void turnLeft(int index) {
        int[] baseArr = matrix[index];
        int[] newArr = new int[m];

        //[0, 1, 2, 3, 4]
        System.arraycopy(baseArr, k, newArr, 0, m-k);
        System.arraycopy(baseArr, 0, newArr, m-k, k);
        matrix[index] = newArr;
    }

    static void deleteSameValue() {
        needDelete = new boolean[n][m];

        findDeleteTarget();

        if(isDeleteTargetExist()) {
            delete();
        }
        else {
            optimize();
        }        
    }

    static void findDeleteTarget() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j] == 0) continue;
                searchPoint(i, j);
            }
        }
    }

    static void searchPoint(int r, int c) {
        int value = matrix[r][c];
        int start = r == 0 ? 1 : 0;
        int limit = r == n-1 ? 3 :4 ;
        for(int i=start; i<limit; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(nr == -1) nr = n-1;
            if(nr == n) nr = 0;
            if(nc == -1) nc = m-1;
            if(nc == m) nc = 0;

            if(matrix[nr][nc] == value) {
                needDelete[nr][nc] = true;
                needDelete[r][c] = true;
            }
        }
    }

    static boolean isDeleteTargetExist() {
        boolean result = false;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(needDelete[i][j]) {
                    return true;
                }
            }
        }
        return result;
    }

    static void delete() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(needDelete[i][j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    static void optimize() {
        int avg = getAvgValue();

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j] == 0) continue;

                if(matrix[i][j] > avg) {
                    matrix[i][j] -= 1;
                }
                else if(matrix[i][j] < avg) {
                    matrix[i][j] += 1;
                }
            }
        }
    }

    static int getAvgValue() {
        int num = 0;
        int sum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j] != 0) {
                    num += 1;
                    sum += matrix[i][j];
                }
            }
        }
        return sum/num;
    }

    static int getAns() {
        int result = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(matrix[i][j] != 0) {
                    result += matrix[i][j];
                }
            }
        }
        return result;
    }
}