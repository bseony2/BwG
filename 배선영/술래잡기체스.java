import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Comparable;

public class 술래잡기체스 {

    static class Thief implements Comparable<Thief>{
        int r, c, d, score;
        boolean isCaught;

        public Thief(int r, int c, int d, int score, boolean isCaught) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.score = score;
            this.isCaught = isCaught;
        }

        public int compareTo(Thief thief) {
            return this.score - thief.score;
        }
    }

    static ArrayList<Thief> thiefList = new ArrayList<>();
    static Thief[] initThieves = new Thief[17];
    static int[][] initIdxMap = new int[4][4];
    static int ans = 0;
    static int[] dr = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = new int[]{0, -1, -1, -1, 0, 1, 1, 1};
    public static void main(String...args) throws IOException{
        init();

        backtracking(thiefList, initIdxMap, 0, 0, 0);

        System.out.println(ans);
    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<4; j++) {
                int idx = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken())-1;
                Thief thief = new Thief(i, j, d, idx,false);
                //initThieves[idx] = thief;
                thiefList.add(thief);
                initIdxMap[i][j] = idx-1;
            }
        }
    }

    static void backtracking(ArrayList<Thief> originThiefArr, int[][] originIdxMap, int r, int c, int score) {
        ArrayList<Thief> thieves = thiefArrCopy(originThiefArr);
        int[][] idxMap = idxMapCopy(originIdxMap);

        int thiefIdx = idxMap[r][c];
        thieves.get(thiefIdx).isCaught = true;
        int policeDirection = thieves.get(thiefIdx).d;
        idxMap[r][c] = -1;

        score += thieves.get(thiefIdx).score;
        ans = ans > score ? ans : score;

        thievesMove(thieves, idxMap, r, c);

        for(int i=1; i<4; i++) {
            int nr = r + dr[policeDirection]*i;
            int nc = c + dc[policeDirection]*i;

            if(!isValidPoint(nr, nc) || idxMap[nr][nc] == -1) continue;

            backtracking(thieves, idxMap, nr, nc, score);
        }
        
    }

    static ArrayList<Thief> thiefArrCopy(ArrayList<Thief> originList) {
        ArrayList<Thief>result = new ArrayList<>();
        for(Thief tempThief : originList) {
            result.add(new Thief(tempThief.r, tempThief.c, tempThief.d, tempThief.score, tempThief.isCaught));
        }
        Collections.sort(result);
        return result;
    }

    static int[][] idxMapCopy(int[][] originArr) {
        int[][] result = new int[4][4];
        for(int i=0; i<4; i++) {
            result[i] = originArr[i].clone();
        }
        return result;
    }

    static void thievesMove(ArrayList<Thief> thieves, int[][] idxMap, int policeR, int policeC) {
        for(Thief thief : thieves) {
            if(thief.isCaught) continue;

            int cr = thief.r;
            int cc = thief.c;
            int cd = thief.d;

            for(int j=0; j<8; j++) {
                int d = (cd + j) % 8;
                int nr = cr + dr[d];
                int nc = cc + dc[d];
                if(!isValidPoint(nr, nc) || (nr == policeR && nc == policeC)) continue;

                int nextThiefIdx = idxMap[nr][nc];

                idxMap[nr][nc] = thief.score-1;
                idxMap[cr][cc] = nextThiefIdx;

                thief.r = nr;
                thief.c = nc;
                thief.d = d;

                if(nextThiefIdx != -1) {
                    Thief nextThief = thieves.get(nextThiefIdx);
                    nextThief.r = cr;
                    nextThief.c = cc;
                }
                
                break;
            }
        }
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<4 && 0<=c && c<4;
    }
}