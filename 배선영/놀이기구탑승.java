import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.lang.Comparable;
import java.util.PriorityQueue;

public class 놀이기구탑승 {

    static class Seat implements Comparable<Seat>{
        int r, c;
        int numOfPriority;
        int emptySeat;

        public Seat(int r, int c, int numOfPriority, int emptySeat) {
            this.r = r;
            this.c = c;
            this.numOfPriority = numOfPriority;
            this.emptySeat = emptySeat;
        }

        public int compareTo(Seat seat) {
            if(this.numOfPriority != seat.numOfPriority) return seat.numOfPriority - this.numOfPriority;
            else if(this.emptySeat != seat.emptySeat) return seat.emptySeat - this.emptySeat;
            else if(this.r != seat.r) return this.r - seat.r;
            else return this.c - seat.c;
        }
    }
    static int[][] priorityMap;
    static int[][] map;
    static int N;
    static int[] entry;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int[] score = new int[]{0, 1, 10, 100, 1000};

    public static void main(String[] args) throws IOException {
        init();

        simulate();

        printAns();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        priorityMap = new int[N*N+1][4];
        map = new int[N][N];
        entry = new int[N*N];

        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            priorityMap[num] = new int[]{a, b, c, d};
            entry[i] = num;
        }
    }

    static void simulate() {
        for(int student : entry) {
            searchSeat(student);
        }
    }

    static void searchSeat(int student) {
        PriorityQueue<Seat> pq = new PriorityQueue<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(isEmptySeat(i, j)) {
                    setSeatPriority(pq, student, i, j);
                }
            }
        }
        Seat selectedSeat = pq.poll();
        map[selectedSeat.r][selectedSeat.c] = student;
    }

    static boolean isEmptySeat(int r, int c) {
        return map[r][c] == 0;
    }

    static void setSeatPriority(PriorityQueue<Seat> pq, int student, int r, int c) {
        int numOfPriority = 0;
        int emptySeat = 0;

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!isValidPoint(nr, nc)) continue;

            if(isEmptySeat(nr, nc)) {
                emptySeat += 1;
            } else {
                if(isPriorityStudent(student, map[nr][nc])) {
                    numOfPriority += 1;
                }
            }
        }

        pq.add(new Seat(r, c, numOfPriority, emptySeat));
    }

    static boolean isValidPoint(int nr, int nc) {
        return 0<=nr && nr<N && 0<=nc && nc<N;
    }

    static boolean isPriorityStudent(int student, int priorityStudent) {
        boolean result = false;

        for (int nextStudent : priorityMap[student]) {
            if(priorityStudent == nextStudent) return true;
        }

        return result;
    }

    static void printAns() {
        int ans = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                ans += getScore(i, j);
            }
        }

        System.out.println(ans);
    }

    static int getScore(int r, int c) {
        int numOfPriority = 0;

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(!isValidPoint(nr, nc)) continue;

            if(isPriorityStudent(map[r][c], map[nr][nc]))
                numOfPriority += 1;
        }

        return score[numOfPriority];
    }
}
