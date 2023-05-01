import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class Main {

    static int N;
    static int M;
    static int K;
    static int[][] field;
    static int[][] attackTime;
    static int[] dr = new int[]{0, 1, 0, -1, 1, -1, 1, -1};  //우, 하, 좌, 상, 우하, 우상, 좌하, 좌상
    static int[] dc = new int[]{1, 0, -1, 0, 1, 1, -1, -1};
    static LinkedList<int[]> subTarget;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        field = new int[N][M];
        attackTime = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play();

        System.out.println(findTower());

    }

    static void play() {
        for(int k=0; k<K; k++) {
            int[] attacker = findAttacker(k);
            int r = attacker[0];
            int c = attacker[1];
            field[r][c] += N+M;
            int power = field[r][c];
            int[] target = findTarget(r, c);

            if(N == target[0] && M == target[1]) return;

            boolean[][] isVisited = new boolean[N][M];
            isVisited[r][c] = true;
            subTarget = new LinkedList<int[]>();

            findLaserWay(r, c, target, isVisited, new LinkedList<int[]>(), 0);
            field[target[0]][target[1]] -= power;   // 타겟은 일단 한방
            if(subTarget.size() > 0) { // 레이저 공격 경로가 있을 경우
                for(int[] current : subTarget) {
                    field[current[0]][current[1]] -= power/2;
                }
            }
            else {  // 포탄 공격
                for(int i=0; i<8; i++) {
                    int nr = target[0] + dr[i];
                    int nc = target[1] + dc[i];
                    if(nr < 0) nr = N-1;
                    if(nr == N) nr = 0;
                    if(nc < 0) nc = M-1;
                    if(nc == M) nc = 0;

                    if((nr == r && nc ==c) || field[nr][nc] <= 0) continue;

                    subTarget.add(new int[]{nr, nc});
                    field[nr][nc] -= power/2;
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(field[i][j] <= 0) continue;
                    field[i][j] += 1;
                }
            }
            field[r][c] -= 1;
            field[target[0]][target[1]] -= 1;
            for(int[] current : subTarget) {
                field[current[0]][current[1]] -= 1;
            }
        }
    }

    static void findLaserWay(int r, int c, int[] target, boolean[][] isVisited, LinkedList<int[]> tempWay, int depth) {
        if(r == target[0] && c == target[1]) {
            subTarget = (LinkedList)tempWay.clone();
            subTarget.remove(subTarget.size()-1);
            return;
        }

        if(subTarget.size() != 0 && depth  >= subTarget.size()) return;

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr < 0) nr = N-1;
            if(nr == N) nr = 0;
            if(nc < 0) nc = M-1;
            if(nc == M) nc = 0;

            if(isVisited[nr][nc] || field[nr][nc] <= 0) continue;

            isVisited[nr][nc] = true;
            tempWay.add(new int[]{nr, nc});
            findLaserWay(nr, nc, target, isVisited, tempWay, depth+1);
            isVisited[nr][nc] = false;
            tempWay.remove(tempWay.size()-1);
        }

        
    }

    static int[] findAttacker(int k) {
        int power = Integer.MAX_VALUE;
        int r=0, c=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] <= 0) continue;

                if(field[i][j] < power) {
                    r = i;
                    c = j;
                    power = field[i][j];
                    continue;
                }
                else if(field[i][j] == power) { // 공격력이 같다면
                    if(attackTime[i][j] > attackTime[r][c]) {    // 가장 최근에 공격한 포탑이 약한 포탑
                        r = i;
                        c = j;
                        power = field[i][j];
                        continue;
                    }
                    else if(attackTime[i][j] == attackTime[r][c]) { // 공격 시간도 같다면
                        if(r+c < i+j) { // 행과 열의 합이 큰게 약한 포탑
                            r = i;
                            c = j;
                            power = field[i][j];
                            continue;
                        }
                        else if(r+c == i+j) {   // 행과 열이 합이 같다면
                            if(r < j) { //열의 값이 큰게 약한 포바
                                r = i;
                                c = j;
                                power = field[i][j];
                                continue;
                            }
                        }
                    }
                }
            }
        }

        attackTime[r][c] = k+1;
        return new int[] {r, c};
    }

    static int[] findTarget(int y, int x) {
        int power = Integer.MIN_VALUE;
        int r=0, c=0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] <= 0 || (y==i && x==j)) continue;

                if(field[i][j] > power) {   // 공격력이 가장 큰게 강한 포탑
                    r = i;
                    c = j;
                    power = field[i][j];
                    continue;
                }
                else if(field[i][j] == power) { // 공격력이 같다면
                    if(attackTime[i][j] < attackTime[r][c]) {    // 공격한지 가장 오래된 포탑이 강한 포탑
                        r = i;
                        c = j;
                        power = field[i][j];
                        continue;
                    }
                    else if(attackTime[i][j] == attackTime[r][c]) { // 공격 시간도 같다면
                        if(r+c > i+j) { // 행과 열의 합이 작은게 강한 포탑
                            r = i;
                            c = j;
                            power = field[i][j];
                            continue;
                        }
                        else if(r+c == i+j) {   // 행과 열이 합이 같다면
                            if(r > j) { //열의 값이 작은게 약한 포탑
                                r = i;
                                c = j;
                                power = field[i][j];
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return power == Integer.MIN_VALUE ? new int[]{N, M} : new int[]{r, c};
    }

    static int findTower() {
        int result = Integer.MIN_VALUE;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(field[i][j] > result) result = field[i][j];
            }
        }

        return result;
    }
}
