import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class 생명과학부랩인턴 {
    static int n, m, k;
    static ArrayList<Mold> moldList = new ArrayList<Mold>();
    static PriorityQueue<Mold>[][] moldMap;
    static int[][] countMatrix;
    static int[] dr = new int[]{-1, 1, 0, 0}; // U D R L
    static int[] dc = new int[]{0, 0, 1, -1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Mold implements Comparable <Mold>{
        int r;
        int c;
        int s;
        int d;
        int b;

        Mold(int r, int c, int s, int d, int b) {
            this.r = r;
            this.c = c;
            if(d==1 || d==2) {
                this.s = (s%(n*2-2));
            } else {
                this.s = (s%(m*2-2));
            }
            this.d = d;
            this.b = b;
        }

        public int compareTo(Mold mold) {
            return mold.b - this.b;
        }

        public void changDirection() {
            if(this.d == 1) this.d = 2;
            else if(this.d == 2) this.d = 1;
            else if(this.d == 3) this.d = 4;
            else this.d = 3;
        }
    }

    public static void main(String...args) throws IOException {
        int ans = 0;

        initialize();

        for(int i=0; i<m; i++) {
            Mold mold = findMold(i);
            if(moldExist(mold)) {
                ans += mold.b;
                moldList.remove(moldList.indexOf(mold));
            }
            move();

            eat();
        }

        System.out.println(ans);
    }

    static void initialize() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        moldMap = new PriorityQueue[n][m];
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                moldMap[i][j] = new PriorityQueue<Mold>();
            }
        }

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Mold mold = new Mold(r, c, s, d, b);
            moldList.add(mold);
            moldMap[r][c].add(mold);
        }
    }

    static Mold findMold(int c) {
        Mold result = null;
        for(int r=0; r<n; r++) {
            if(moldMap[r][c].size() > 0) {
                result = moldMap[r][c].poll();
                break;
            }
        }
        return result;
    }

    static boolean moldExist(Mold mold) {
        return mold != null;
    }

    static void move() {
        for(Mold mold : moldList) {
            moldMap[mold.r][mold.c].remove(mold);
            int limit = (mold.d == 1 || mold.d == 2) ? n-1 : m-1;
            int loc = (mold.d == 1 || mold.d == 2) ? mold.r : mold.c;
            int dis = mold.s;

            while(dis > 0) {
                if((loc == limit && (mold.d == 2 || mold.d == 3)) || (loc == 0 && (mold.d == 1 || mold.d == 4))) {
                    mold.changDirection();
                }
                if(mold.d == 2 || mold.d == 3) {
                    if(loc + dis > limit) {
                        dis -= (limit - loc);
                        loc = limit;
                    }
                    else {
                        loc += dis;
                        dis = 0;
                    }
                }
                else {
                    if(loc - dis < 0) {
                        dis -= loc;
                        loc = 0;
                    }
                    else {
                        loc -= dis;
                        dis = 0;
                    }
                }
            }

            if(mold.d == 1 || mold.d == 2) {
                mold.r = loc;
            }
            else {
                mold.c = loc;
            }
            moldMap[mold.r][mold.c].add(mold);
        }
    }

    static void eat() {
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(moldMap[i][j].size() > 1) {
                    Mold BiggestMold = moldMap[i][j].poll();
                    while(!moldMap[i][j].isEmpty()) {
                        Mold currentMold = moldMap[i][j].poll();
                        moldList.remove(moldList.indexOf(currentMold));
                    }
                    moldMap[i][j].add(BiggestMold);
                }
            }
        }
    }
}