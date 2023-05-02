import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.Comparable;

class Tower implements Comparable<Tower>{
    int r, c, power, turn, backR, backC;

    public Tower(int r, int c, int power) {
        this.r = r;
        this.c = c;
        this.power = power;
    }

    public int compareTo(Tower tower) {
        if(this.power != tower.power) return this.power - tower.power;
        if(this.turn != tower.turn) return tower.turn - this.turn;
        if(this.r+this.c != tower.r + tower.c) return (tower.r+tower.c) - (this.r+this.c);
        return tower.c - this.c;
    }
}

public class 포탑부수기 {

    static int N;
    static int M;
    static int K;
    static Tower[][] field;
    static int[] dr = new int[]{0, 1, 0, -1, 1, 1, -1, -1};  //우, 하, 좌, 상, 우하, 좌하, 좌상, 우상
    static int[] dc = new int[]{1, 0, -1, 0, 1, -1, -1, 1};
    static Queue<Tower> queue;
    static Queue<Tower> damagedTower;
    static Tower attacker;
    static Tower target;
    static boolean[][] isVisited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new Tower[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value != 0) field[i][j] = new Tower(i, j, value);
            }
        }

        for(int k=1; k<=K; k++) {
            init();
            selectTower(k);
            
            isVisited[attacker.r][attacker.c] = true;
            
            queue.offer(attacker);
            boolean laser = laserPossible();
            
            target.power -= attacker.power;
            if(laser) laserAttack();
            else bombAttack();
            destroyOrHeal();
            if(isFinish()) break;
        }
        System.out.println(findStrongestTower());
    }

    public static void init() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] == null) continue;
                if(field[i][j].power <= 0) field[i][j] = null;
            }
        }

        isVisited = new boolean[N][M];
        queue = new LinkedList<Tower>();
        damagedTower = new LinkedList<Tower>();
    }

    public static void destroyOrHeal() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] == null) continue;
                if(field[i][j].power <= 0) field[i][j] = null;
                else field[i][j].power += 1;
            }
        }
        attacker.power -= 1;
        target.power -= 1;
        for(Tower tower : damagedTower) {
            tower.power -= 1;
        }

        if(target.power <= 0) target = null;
    }

    public static void bombAttack() {
        int r = target.r;
        int c = target.c;
        for(int i=0; i<8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0) nr = N-1;
            if(nr >= N) nr = 0;
            if(nc < 0) nc = M-1;
            if(nc >= M) nc = 0;

            if(field[nr][nc] == null || (attacker.r == nr && attacker.c == nc)) continue;

            field[nr][nc].power -= (attacker.power/2);
            damagedTower.offer(field[nr][nc]);
            
        }
    }

    public static void laserAttack() {
        int r = target.backR;
        int c = target.backC;

        while(true) {
            
            Tower tower = field[r][c];

            if(tower == attacker) break;

            r = tower.backR;
            c = tower.backC;

            tower.power -= (attacker.power/2);
            damagedTower.offer(tower);
            
        }
    }

    public static boolean laserPossible() {
        boolean result = false;

        while(!queue.isEmpty()) {
            Tower currentTower = queue.poll();
            int r = currentTower.r;
            int c = currentTower.c;

            if(currentTower == target) {
                result = true;
                break;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0) nr = N-1;
                if(nr >= N) nr = 0;
                if(nc < 0) nc = M-1;
                if(nc >= M) nc = 0;

                if(field[nr][nc] == null || isVisited[nr][nc]) continue;

                isVisited[nr][nc] = true;
                Tower nextTower = field[nr][nc];
                nextTower.backR = r;
                nextTower.backC = c;
                queue.offer(nextTower);
            }
        }

        return result;
    }

    public static void selectTower(int k) {
        target = new Tower(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);    // 제일 약한 경우를 넣음
        attacker = new Tower(0, 0, Integer.MAX_VALUE);                              // 제일 강한 경우를 넣음
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] == null) continue;
                if(field[i][j].compareTo(attacker) < 0 ) attacker = field[i][j];
                if(field[i][j].compareTo(target) > 0) target = field[i][j];
                field[i][j].backR = 0;
                field[i][j].backC = 0;
            }
        }
        attacker.power += N+M;
        attacker.turn = k;
    }

    public static boolean isFinish(){
        int unbrokenCnt = 0;
        for(int i=0; i<N ;i++){
            for(int j=0; j<M; j++){
                if(field[i][j] == null) continue;
                if(field[i][j].power > 0) unbrokenCnt++;
            }
        }
        return unbrokenCnt <= 1 ? true : false;
    }

    public static int findStrongestTower(){
        int max = 0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(field[i][j] == null) continue;
                max = Math.max(max,field[i][j].power);
            }
        }
        return max;
    }
}
