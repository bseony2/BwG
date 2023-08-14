import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 이상한윷놀이 {

    static class Unit {
        int r, c, d;

        public Unit(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public void changeDirection() {
            this.d = this.d % 2 == 0 ? this.d-1 : this.d+1;
        }
    }

    static int n, k;
    static int[][] board;
    static ArrayList<Unit>[][] unitBoard;
    static ArrayList<Unit> unitList = new ArrayList<Unit>();
    static int[] dr = new int[]{0, 0, 0, -1, 1};
    static int[] dc = new int[]{0, 1, -1, 0, 0};
    static int turn = 0;

    public static void main(String...args) throws IOException{
        initialize();

        while(++turn <= 1000) {
            play();
        }

        System.out.println(-1);
    }

    static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        unitBoard = new ArrayList[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                unitBoard[i][j] = new ArrayList<Unit>();
            }
        }

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int d = Integer.parseInt(st.nextToken());
            Unit unit = new Unit(r, c, d);
            unitList.add(unit);
            unitBoard[r][c].add(unit);
        }
    }

    static void play() {
        for(Unit unit : unitList) {
            move(unit);
        }
    }

    static void move(Unit unit) {
        

        int nextColor = getNextColor(unit);
        colorMove(unit, nextColor);
    }

    static int getNextColor(Unit unit) {
        int nr = unit.r + dr[unit.d];
        int nc = unit.c + dc[unit.d];
        if(!isValidPoint(nr, nc) || board[nr][nc] == 2) {
            return 2;
        }
        return board[nr][nc];
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<n && 0<=c && c<n;
    }

    static void colorMove(Unit unit, int color) {
        switch(color) {
            case 0 :
                whiteMove(unit);
                break;
            case 1 :
                redMove(unit);
                break;
            case 2 :
                blueMove(unit);
                break;
        }
    }

    static void whiteMove(Unit unit) {
        int r = unit.r;
        int c = unit.c;
        int nr = unit.r + dr[unit.d];
        int nc = unit.c + dc[unit.d];
        int index = unitBoard[unit.r][unit.c].indexOf(unit);

        while(unitBoard[r][c].size() > index) {
            Unit currentUnit = unitBoard[r][c].get(index);
            unitMove(currentUnit, nr, nc);
            unitBoard[r][c].remove(index);
        }

        if(unitBoard[nr][nc].size() >= 4) {
            gameOver();
        }
    }

    static void redMove(Unit unit) {
        int r = unit.r;
        int c = unit.c;
        int nr = unit.r + dr[unit.d];
        int nc = unit.c + dc[unit.d];
        int index = unitBoard[unit.r][unit.c].indexOf(unit);

        while(unitBoard[r][c].size() > index) {
            Unit currentUnit = unitBoard[r][c].get(unitBoard[r][c].size()-1);
            unitMove(currentUnit, nr, nc);
            unitBoard[r][c].remove(unitBoard[r][c].size()-1);
        }

        if(unitBoard[nr][nc].size() >= 4) {
            gameOver();
        }
    }

    static void blueMove(Unit unit) {
        unit.changeDirection();
        int nextColor = getNextColor(unit);

        if(nextColor == 0) {
            whiteMove(unit);
        }
        else if(nextColor == 1) {
            redMove(unit);
        }
    }

    static void unitMove(Unit unit, int nr, int nc) {
        unit.r = nr;
        unit.c = nc;
        unitBoard[nr][nc].add(unit);
    }

    static void gameOver() {
        System.out.println(turn);
        System.exit(0);
    }
}