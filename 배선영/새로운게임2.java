import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

class 새로운게임2 {

    static int N;
    static int K;
    static int[][] matrix;
    static ArrayList<Unit>[][] unitBoard;
    static Unit[] unitArr;
    static int answer = 0;
    static int[] dr = new int[]{0, 0, -1, 1};
    static int[] dc = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        matrix = new int[N][N];
        unitBoard = new ArrayList[N][N];
        unitArr = new Unit[K];

        for(int i=0; i<N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for(int j=0; j<N; j++) {
                unitBoard[i][j] = new ArrayList<>();
            }
        }

        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Unit unit = new Unit(r-1, c-1, d);
            unitArr[i] = unit;
            unitBoard[r-1][c-1].add(unit);
        }

        for(int i=0; i<1000; i++) {
            answer++;
            play();
        }

        System.out.println(-1);
    }

    static void play() {
        for(int i=0; i<unitArr.length; i++) {
            Unit unit = unitArr[i];
            int r = unit.r;
            int c = unit.c;
            int d = unit.d;
            int nr = r + dr[d-1];
            int nc = c + dc[d-1];

            if(nr<0 || nc<0 || nr>=N || nc>=N || matrix[nr][nc] == 2) { // 체스판 밖으로 나가거나, 다음 칸이 파란색
                if(d == 1) d=2;
                else if(d == 2) d=1;
                else if(d == 3) d=4;
                else if(d == 4) d=3;
                unit.d = d;
                nr = r + dr[d-1];
                nc = c + dc[d-1];
                if(nr<0 || nc<0 || nr>=N || nc>=N || matrix[nr][nc] == 2) continue; // 두번 그러면 가만히~
            }

            boolean start = false;
            ArrayList<Unit> tempArr = new ArrayList<>(); // 다음 칸의 색상에 따라 말이 뒤집어질 수 있으므로 - 아래에서 Collections.reverse(tempArr)
            for(int j=0; j<unitBoard[r][c].size(); j++) {
                if(unitBoard[r][c].get(j) == unit) { // 특정 말(현재 말과 현재말에 업힌 말들)이동 시작
                    start = !start;
                }

                if(!start) continue;
                
                Unit currentUnit = unitBoard[r][c].get(j);
                currentUnit.r = nr;
                currentUnit.c = nc;
                tempArr.add(currentUnit);
                unitBoard[r][c].remove(j--);
            }

            if(matrix[nr][nc] == 1) Collections.reverse(tempArr); // 빨간색이면 뒤집어서
                
            for(Unit nowUnit : tempArr) {
                unitBoard[nr][nc].add(nowUnit);
            }

            if(unitBoard[nr][nc].size() >= 4) {
                System.out.println(answer);
                System.exit(0);
            }
        }
    }
}

class Unit {
    int r;
    int c;
    int d;

    public Unit(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}