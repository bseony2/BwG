import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 원자충돌 {
    static class Atom {
        int r, c, m, s, d;
        public Atom(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d =d;
        }
    }
    static int N,M,K;
    static ArrayList<Atom>[][] map;
    static ArrayList<Atom> atomList = new ArrayList<>();
    static int[] dr = new int[]{-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = new int[]{0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        init();

        test();

        int ans = 0;
        for(Atom atom : atomList) {
            ans += atom.m;
        }

        System.out.println(ans);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = new ArrayList<Atom>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Atom atom = new Atom(r, c, m, s, d);
            map[r][c].add(atom);
            atomList.add(atom);
        }
    }

    static void test() {
        while(K-->0) {
            allMove();

            checkDuplicatedAtom();
        }
    }

    static void allMove() {
        for(Atom atom : atomList) {
            move(atom);
        }
    }

    static void move(Atom atom) {
        int s = atom.s%N;
        int nr = atom.r + dr[atom.d]*s;
        int nc = atom.c + dc[atom.d]*s;

        if(nr < 0) nr += N;
        if(nr >= N) nr %= N;
        if(nc < 0) nc += N;
        if(nc >= N) nc %= N;
        
        map[atom.r][atom.c].remove(atom);
        map[nr][nc].add(atom);
        atom.r = nr;
        atom.c = nc;
    }

    static void checkDuplicatedAtom() {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(map[i][j].size() > 1) {
                    crash(i, j);
                }
            }
        }
    }

    static void crash(int r, int c) {
        int numOfAtom = map[r][c].size();
        int sumS = 0;
        int sumM = 0;
        int nextDirectionFlag = map[r][c].get(0).d % 2;
        boolean isDiagonal = false;


        for(Atom atom : map[r][c]) {
            sumM += atom.m;
            sumS += atom.s;
            if(!isDiagonal && atom.d % 2 != nextDirectionFlag) {
                isDiagonal = true;
            }
            atomList.remove(atom);
        }
        map[r][c].clear();

        int nextM = sumM / 5;
        if(nextM == 0) return;

        int nextS = sumS / numOfAtom;

        for(int i=isDiagonal?1:0; i<8; i+=2) {
            Atom newAtom = new Atom(r, c, nextM, nextS, i);
            map[r][c].add(newAtom);
            atomList.add(newAtom);
        }
    }
}