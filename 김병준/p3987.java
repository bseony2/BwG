package w2;

import java.util.Scanner;

public class p3987 {

    static int N, M;
    static int sx, sy;
    final static int INF = 100001;
    static int dx[] = {-1, 0, 1, 0};
    static int dy[] = {0, 1, 0, -1};
    static char[][] map = new char[502][502];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();
        // map 세팅
        for(int i = 0 ; i < N; i++) {
            String row = sc.nextLine();
            int j = 0;
            for(char c : row.toCharArray()){
                map[i][j++] = c;
            }
        }

        // 출발지 세팅
        sx = sc.nextInt();
        sy = sc.nextInt();
        int ans = -1;
        int ansDir = -1;
        for(int dir = 0; dir < 4; dir++) {
            int res = solve(sx, sy, dir);
//            System.out.println("res = " + res);
            if(res > ans){
                ansDir = dir;
                ans = res;
            }
        }
        
        // 출력
        if(ans == INF){
            System.out.println("Voyager");
        } else{
            if(ansDir == 0) System.out.println("U");
            else if(ansDir == 1) System.out.println("R");
            else if(ansDir == 2) System.out.println("D");
            else System.out.println("L");
            System.out.println(ans);
        }
    }

    private static int solve(int x, int y, int dir) {
        int cnt = 1;
        while(isInRange(x + dx[dir], y + dy[dir]) && map[x + dx[dir]][y + dy[dir]] != 'C'){
            x += dx[dir]; y += dy[dir];
            System.out.println("x + \" \" + y + \" \" + dir = " + x + " " + y + " " + dir);
            dir = nextDir(x, y, dir);
            System.out.println("nextdir = " + dir);
            cnt += 1;
            if(cnt >= N*M + 1) return INF;
        }
        return cnt;
    }

    private static boolean isInRange(int x, int y){
        if( 0 > x || x >= N || 0 > y || y >= M) return false;
        else return true;
    }
    // 방향전환
    private static int nextDir(int x, int y, int dir) {
        if(map[x][y] == '\\'){
            if(dir == 3) return 0;
            else if(dir == 0) return 3;
            else if(dir == 1) return 2;
            else return 1;
        } else if(map[x][y] == '/'){
            if(dir == 2) return 3;
            else if(dir == 3) return 2;
            else if(dir == 1) return 0;
            else return 1;
        } else{
            return dir;
        }
    }
}