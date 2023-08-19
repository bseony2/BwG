import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.lang.Comparable;
import java.util.Collections;
public class 승자독식모노폴리 {
    static class Player implements Comparable<Player>{
        int idx, d, r, c;
        int[][] nextDir;
        boolean isGameOver;

        public Player(int idx) {
            this.idx = idx;
            nextDir = new int[4][4];
            isGameOver = false;
        }

        public int compareTo(Player player) {
            return this.idx - player.idx;
        }
    }
    static int n, m, k;
    static Player[] players;
    static int[][] priorityMap;
    static int[][] priorityTime;
    static ArrayList<Player>[][] playerMap;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        init();

        play();

        System.out.println();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        priorityTime = new int[n][n];

        players = new Player[m+1];
        priorityMap = new int[n][n];

        playerMap = new ArrayList[n][n];
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                playerMap[i][j] = new ArrayList<Player>();
            }
        }

        for(int i=1; i<=m; i++) {
            players[i] = new Player(i);
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<n; j++) {
                int idx = Integer.parseInt(st.nextToken());
                priorityMap[i][j] = idx;
                if(idx != 0) {
                    players[idx].r = i;
                    players[idx].c = j;
                    playerMap[i][j].add(players[idx]);
                    priorityTime[i][j] = k;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=1; i<=m; i++) {
            players[i].d = Integer.parseInt(st.nextToken())-1;
        }

        for(int i=1; i<=m; i++) {
            for(int j=0; j<4; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                int u = Integer.parseInt(st.nextToken())-1;
                int d = Integer.parseInt(st.nextToken())-1;
                int l = Integer.parseInt(st.nextToken())-1;
                int r = Integer.parseInt(st.nextToken())-1;
                int[] dir = new int[]{u, d, l, r};
                players[i].nextDir[j] = dir;
            }
        }
    }

    static void play() {
        int turn = 0;

        while(++turn <= 1000) {
            moveAll();

            removeDuplicatedPlayer();

            removeExpirePriority();

            setPlayersPriority();

            if(isGameOver()) {
                break;
            }
        }

        System.out.println(turn > 1000 ? -1 : turn);
    }

    static void moveAll() {
        for(int i=1; i<=m; i++) {
            Player player = players[i];
            if(player.isGameOver) continue;
            move(player);
        }
    }

    static void move(Player player) {
        for(int d : player.nextDir[player.d]) {
            int nr = player.r + dr[d];
            int nc = player.c + dc[d];
            
            if(isValidPoint(nr, nc) && !hasPriority(nr, nc)) {
                moveNextPoint(player, nr, nc);
                player.d = d;
                return;
            }
        }

        for(int d : player.nextDir[player.d]) {
            int nr = player.r + dr[d];
            int nc = player.c + dc[d];
            
            if(isValidPoint(nr, nc) && isOwnPriority(player, nr, nc)) {
                moveNextPoint(player, nr, nc);
                player.d = d;
                return;
            }
        }
    }

    static void moveNextPoint(Player player, int nr, int nc) {
        playerMap[player.r][player.c].remove(player);
        playerMap[nr][nc].add(player);
        player.r = nr;
        player.c = nc;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<n && 0<=c && c<n;
    }

    static boolean hasPriority(int r, int c) {
        return priorityMap[r][c] != 0;
    }

    static boolean isOwnPriority(Player player, int r, int c) {
        return priorityMap[r][c] == player.idx;
    }

    static void removeDuplicatedPlayer() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(playerMap[i][j].size() > 1) {
                    removePlayers(i, j);
                }
            }
        }
    }

    static void removePlayers(int r, int c) {
        ArrayList<Player> list = playerMap[r][c];
        Collections.sort(list);
        
        while(list.size() >1) {
            Player player = list.get(list.size()-1);
            player.isGameOver = true;
            list.remove(player);
        }
    }

    static void setPlayersPriority() {
        for(int i=1; i<=m; i++) {
            Player player = players[i];
            if(player.isGameOver) continue;
            setPriority(player);
        }
    }
    
    static void setPriority(Player player) {
        priorityMap[player.r][player.c] = player.idx;
        priorityTime[player.r][player.c] = k;
    }

    static void removeExpirePriority() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(priorityTime[i][j] != 0) {
                    priorityTime[i][j] -= 1;
                    if(priorityTime[i][j] == 0) {
                        priorityMap[i][j] = 0;
                    }
                }
            }
        }
    }

    static boolean isGameOver() {
        int sum = 0;
        for(int i=1; i<=m; i++) {
            sum = !players[i].isGameOver ? sum +1 : sum;
            if(sum > 1) {
                break;
            }
        }
        return sum == 1 ? true : false;
    }
}