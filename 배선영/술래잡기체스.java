import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Comparable;

public class 술래잡기체스{
    static class Thief implements Comparable<Thief>{
        int d, score;
        public Thief(int score, int d) {
            this.d = d;
            this.score = score;
        }

        public int compareTo(Thief thief) {
            return this.score - thief.score;
        }
    }

    static class Police {
        int r, c;
        int d;

        public Police(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static Thief[][] map = new Thief[4][4];
    static int ans = 0;
    static Police police = new Police(0, 0);
    static int[] dr = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = new int[]{0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException{
        initialize();

        play(ans);

        System.out.println(ans);
    }

    static void initialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<4; j++) {
                int score = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())-1;
                Thief thief = new Thief(score, d);
                map[i][j] = thief;
            }
        }

        ans += catchThief(0, 0);
        moveThieves();
    }

    static int catchThief(int r, int c) {
        int score = map[r][c].score;
        police.d = map[r][c].d;
        police.r = r;
        police.c = c;
        map[r][c] = null;
        return score;
    }

    static void play(int score) {

        if(!isPoliceMoveable()) {
            ans = ans > score ? ans : score;
            return;
        }

        Thief[][] tempMap = copyMap();
        
        int r = police.r;
        int c = police.c;
        int d = police.d;
        for(int dist=1; dist<=4; dist++) {
            int nr = r + dr[d]*dist;
            int nc = c + dc[d]*dist;

            if(!isValidPoint(nr, nc) || map[nr][nc] == null) {
                continue;
            }

            int catchScore = catchThief(nr, nc);
            
            moveThieves();

            play(score + catchScore);
            
            police.r = r;
            police.c = c;
            police.d = d;
            for(int i = 0; i < 4; i++)
                for(int j = 0; j < 4; j++)
                    map[i][j] = tempMap[i][j];
        }
    }

    static Thief[][] copyMap() {
        Thief[][] result = new Thief[4][4];

        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                if(map[i][j] == null) continue;
                result[i][j] = map[i][j];
            }
        }

        return result;
    }

    static boolean isPoliceMoveable() {
        int r = police.r;
        int c = police.c;

        while(true) {
            int nr = r + dr[police.d];
            int nc = c + dc[police.d];

            if(!isValidPoint(nr, nc)) {
                return false;
            }
            else if(map[nr][nc] != null) {
                return true;
            }
            r = nr;
            c = nc;
        }
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<4 && 0<=c && c<4;
    }

    static void moveThieves() {
        for(int k=1; k<=16; k++) {
            moveThief(k);
            
        }
    }

    static void moveThief(int k) {
        for(int r=0; r<4; r++) {
            for(int c=0; c<4; c++) {
                if(map[r][c] == null) continue;
                if(map[r][c].score == k) {
                    int d = map[r][c].d;
                    for(int i=0; i<8; i++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];

                        if(isThiefMoveAble(nr, nc)) {
                            Thief newThief = new Thief(k, d);

                            Thief temp = map[nr][nc];
                            map[r][c] = temp;

                            map[nr][nc] = newThief;
                            break;
                        }

                        d = (d+1)%8;
                    }
                    return;
                }
            }
        }
    }

    static boolean isThiefMoveAble(int r, int c) {
        return isValidPoint(r, c) && (r != police.r || c != police.c);
    }

    static boolean isThiefLocated(int r, int c) {
        return map[r][c] != null;
    }
}