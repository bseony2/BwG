import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 야구 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final int NUM_OF_PLAYER = 9;
    static int[] entry = new int[NUM_OF_PLAYER];
    static boolean[] isVisited = new boolean[NUM_OF_PLAYER];
    static int[][] players;
    static boolean[] base;
    static int ans = 0;
    static int N;
    static int gameScore;
    static int hitter;
    
    public static void main(String...args) throws IOException{
        initialize();

        isVisited[0] = true;

        dfs(0);

        System.out.println(ans);
    }

    static void initialize() throws IOException{
        N = Integer.parseInt(br.readLine());
        players = new int[NUM_OF_PLAYER][N];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<NUM_OF_PLAYER; j++) {
                players[j][i] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int depth) {
        if(depth == 3) {
            dfs(depth+1);
            return;
        }
        if(depth == NUM_OF_PLAYER) {
            playBaseBall();
            return;
        }

        for(int player=1; player<NUM_OF_PLAYER; player++) {
            if(!isVisited[player]) {
                isVisited[player] = true;
                entry[depth] = player;
                dfs(depth+1);
                isVisited[player] = false;
            }
        }
    }

    static void playBaseBall() {
        gameInit();
        for(int inning = 0; inning<N; inning++) {
            gameScore += getInningScore(inning);
        }

        ans = ans > gameScore ? ans : gameScore;
    }

    static void gameInit() {
        gameScore = 0;
        hitter = 0;
    }

    static int getInningScore(int inning) {
        int score = 0;
        int out = 0;
        base = new boolean[4];

        while(out < 3) {
            int hit = players[entry[hitter]][inning];
            if(hit == 0) {
                out += 1;
            } else {
                score += moveRunner(hit);
            }
            hitter = (hitter + 1) % NUM_OF_PLAYER;
        }

        return score;
    }

    static int moveRunner(int hit) {
        int score = 0;
        
        for(int i=0; i<hit; i++) {
            if(base[3]) {
                score += 1;
            }
            base[3] = base[2];
            base[2] = base[1];
            if(i == 0) {
                base[1] = true;
            } else {
                base[1] = false;
            }
        }

        return score;
    }
}
