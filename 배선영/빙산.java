import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.Queue;

class 빙산 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static LinkedList<Iceberg> icebergList = new LinkedList<>();
    static Iceberg[][] icebergMap;
    static int N;
    static int M;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static Queue<int[]> queue = new LinkedList<>();
    
    static public void main(String...args) throws IOException{
        input();

        int answer = doProcess();
        
        System.out.println(answer);
    }

    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        icebergMap = new Iceberg[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<M; j++) {
                int height = Integer.parseInt(st.nextToken());
                setIceberg(i, j, height);
            }
        }
    }

    static void setIceberg(int r, int c, int height) {
        if(height != 0) {
            Iceberg iceberg = new Iceberg(r, c, height);
            icebergMap[r][c] = iceberg;
            icebergList.add(iceberg);
        }
    }

    static int doProcess() {
        int answer = 0;
        boolean isDone = false;

        while(!isDone) {
            answer += 1;

            meltDown();

            boolean isAllMeltDown = !hasValidIceberg();
            if(isAllMeltDown) {
                answer = 0;
                break;
            }

            setUnion();

            isDone = isSeperated();
        }

        return answer;
    }

    static void meltDown() {
        for(Iceberg iceberg : icebergList) {
            int meltAmount = setMeltingHeight(iceberg);
            iceberg.height -= meltAmount;
        }

        for(Iceberg iceberg : icebergList) {
            if(iceberg.height <= 0) {
                icebergMap[iceberg.r][iceberg.c] = null;
            }
        }

        icebergList.removeIf(item -> item.height <= 0);
    }

    static int setMeltingHeight(Iceberg iceberg) {
        int result = 0;
        for(int i=0; i<4; i++) {
            int nr = iceberg.r + dr[i];
            int nc = iceberg.c + dc[i];
            if(isValidPoint(nr, nc) && icebergMap[nr][nc] == null) {
                result += 1;
            }
        }
        return result;
    }

    static boolean isValidPoint(int nr, int nc) {
        return 0<=nr && nr<N && 0<= nc && nc<M;
    }

    static boolean hasValidIceberg() {
        return !(icebergList.size() == 0);
    }

    static void setUnion() {
        Iceberg headIceberg = icebergList.get(0);
        queue.add(new int[]{headIceberg.r, headIceberg.c});
        boolean unionFlag = !headIceberg.unionFlag;
        boolean[][] isVisited = new boolean[N][M];

        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            int r = point[0];
            int c = point[1];
            icebergMap[r][c].unionFlag = unionFlag;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(isValidPoint(nr, nc) && icebergMap[nr][nc] != null && !isVisited[nr][nc]) {
                    isVisited[nr][nc] = true;
                    queue.add(new int[]{nr, nc});
                }
            }
        }
    }

    static boolean isSeperated() {
        boolean flag = icebergList.get(0).unionFlag;
        for(Iceberg iceberg : icebergList) {
            if(iceberg.unionFlag != flag) {
                return true;
            }
        }
        return false;
    }
}

class Iceberg {
    int r;
    int c;
    int height;
    int minusValue;
    boolean unionFlag;

    public Iceberg(int r, int c, int height) {
        this.r = r;
        this.c = c;
        this.height = height;
        unionFlag = false;
    }
}