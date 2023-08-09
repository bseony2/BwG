import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 시공의돌풍 {
    static int n, m, t;
    static int topR, bottomR;
    static int[][] matrix;
    static int[][] calTempMatrix;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        initialize();

        for(int i=0; i<t; i++) {
            spread();

            windClean();
        }

        int ans = getAns();

        System.out.println(ans);
    }

    static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        matrix = new int[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<m; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
                if(matrix[i][j] == -1) {
                    bottomR = i;
                    topR = i-1;
                }
            }
        }
    }

    static void spread() {
        calTempMatrix = new int[n][m];

        setCalMatrix();

        calSpread();
    }

    private static void setCalMatrix() {
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                int spreadAmt = matrix[r][c] / 5;
                int validArea = 0;

                for(int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(!isValidPoint(nr, nc) || isWindLocation(nr, nc)) {
                        continue;
                    }
                    calTempMatrix[nr][nc] += spreadAmt;
                    validArea += 1;
                }
                calTempMatrix[r][c] -= (validArea * spreadAmt);
            }
        }
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<n && 0<=c && c<m;
    }

    static boolean isWindLocation(int r, int c) {
        return matrix[r][c] == -1;
    }

    static void calSpread() {
        for(int r=0; r<n; r++) {
            for(int c=0; c<m; c++) {
                if(isWindLocation(r, c)) {
                    continue;
                }
                matrix[r][c] += calTempMatrix[r][c];
            }
        }
    }

    static void windClean() {
        topClean();

        bottomClean();
    }

    static void topClean() {
        for(int i=topR-1; i>0; i--) {
            matrix[i][0] = matrix[i-1][0];
        }

        for(int i=0; i<m-1; i++) {
            matrix[0][i] = matrix[0][i+1];
        }

        for(int i=0; i<topR; i++) {
            matrix[i][m-1] = matrix[i+1][m-1];
        }

        for(int i=m-1; i>1; i--) {
            matrix[topR][i] = matrix[topR][i-1];
        }

        matrix[topR][1] = 0;
    }

    static void bottomClean() {
        for(int i=bottomR+1; i<n-1; i++) {
            matrix[i][0] = matrix[i+1][0];
        }

        for(int i=0; i<m-1; i++) {
            matrix[n-1][i] = matrix[n-1][i+1];
        }

        for(int i=n-1; i>bottomR; i--) {
            matrix[i][m-1] = matrix[i-1][m-1];
        }

        for(int i=m-1; i>1; i--) {
            matrix[bottomR][i] = matrix[bottomR][i-1];
        }

        matrix[bottomR][1] = 0;
    }

    static int getAns() {
        int sum = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                sum += matrix[i][j];
            }
        }

        return sum + 2;
    }
}