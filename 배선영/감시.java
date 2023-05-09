import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.lang.Math;

public class 감시 {

    static int[][] map;
    static ArrayList<int[]> location = new ArrayList<int[]>();
    static int[] dir;
    static int N;
    static int M;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        boolean[][] sight = new boolean[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if(value > 0) {
                    sight[i][j] = true;
                    if(value<=5) location.add(new int[]{i,j});
                }
            }
        }

        dfs(sight, 0);

        System.out.println(answer);
    }

    static void dfs(boolean[][] sight, int depth) {
        if(depth == location.size()) {
            answer = Math.min(answer, getAnswer(sight));
            return;
        }

        int[] point = location.get(depth);
        int r = point[0];
        int c = point[1];
        boolean[][] copyArray;

        if(map[r][c] == 1) {
            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'D', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'L', r, c);
            dfs(copyArray, depth + 1);
        }
        else if(map[r][c] == 2){
            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'D', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'R', r, c);
            onSight(copyArray, 'L', r, c);
            dfs(copyArray, depth + 1);
        }
        else if(map[r][c] == 3){
            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'L', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'L', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);
        }
        else if(map[r][c] == 4){
            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'L', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'L', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);

            copyArray = cloneArray(sight);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'L', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);
        }
        else if(map[r][c] == 5){
            copyArray = cloneArray(sight);
            onSight(copyArray, 'U', r, c);
            onSight(copyArray, 'D', r, c);
            onSight(copyArray, 'L', r, c);
            onSight(copyArray, 'R', r, c);
            dfs(copyArray, depth + 1);
        }
    }

    static void onSight(boolean[][] target, char d, int r, int c) {
        switch(d) {
            case 'U' : 
                for(int i=r; i>=0; i--) {
                    if(map[i][c] == 6) break;
                    target[i][c] = true;
                }
            break;
            case 'R' : 
                for(int i=c; i<M; i++) {
                    if(map[r][i] == 6) break;
                    target[r][i] = true;
                }
            break;
            case 'D' : 
                for(int i=r; i<N; i++) {
                    if(map[i][c] == 6) break;
                    target[i][c] = true;
                }
            break;
            case 'L' : 
                for(int i=c; i>=0; i--) {
                    if(map[r][i] == 6) break;
                    target[r][i] = true;
                }
            break;
        }
    }

    static boolean[][] cloneArray(boolean[][] target) {
        boolean[][] result = new boolean[N][M];

        for(int i=0; i<N; i++) {
            System.arraycopy(target[i], 0, result[i], 0, target[i].length);
        }

        return result;
    }

    static int getAnswer(boolean[][] sight) {
        int result = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!sight[i][j]) result += 1;
            }
        }
        return result;
    }
}
