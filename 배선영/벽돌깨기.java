import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class 벽돌깨기 {

    static int H;
    static int W;
    static int N;
    static int[][] matrix;
    static int[] dr = new int[]{-1, 1, 0, 0};  // 위, 아래, 왼쪽, 오른쪽
    static int[] dc = new int[]{0, 0, -1, 1};
    static int answer;
    static int T;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        // 입력 받기
        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            matrix = new int [H][W];
            answer = H * W;
            
            for(int r=0; r<H; r++) {    // 매트릭스 채우기
                st = new StringTokenizer(br.readLine(), " ");
                for(int c=0; c<W; c++) {
                    matrix[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            // 게임 시작
            play(0);
            sb.append("#").append(t).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void play(int depth) {
        if(depth == N) {
            int result = countBlock();
            answer = result < answer ? result : answer;
            return;
        }
        int[][] temp = deepcopy(matrix);  // 순열 한바퀴 돌고 원상복귀 해줘야 하므로 <= 어쩌면 이 문제의 핵심

        for(int i=0; i<W; i++) {    // 중복 순열로 짜야 함
            dropBall(i);    // i열에다가 볼을 떨굼
            play(depth+1);
            matrix = deepcopy(temp);    // 원상복구
        }
    }

    static void dropBall(int c) {

        int r = -1;
        for(int i=0; i<H; i++) {    // 맨 위의 블럭을 찾으려 함
            if(matrix[i][c] != 0) {
                r = i;
                break;
            }
        }
        if(r == -1) return; //해당 열이 비어있으면(깨지는 벽돌이 없으면 그냥 리턴)

        Queue<int[]> queue = new LinkedList<>();    // 큐에는 행, 열, 해당 행열의 값이 들어감 => {r, c, matrix[r][c]}
        queue.offer(new int[]{r, c, matrix[r][c]});

        while(!queue.isEmpty()) {
            int[] now = queue.poll();

            explose(now[0], now[1], now[2], queue);
        }
        sortBlock();    // 벽돌 다 깨지면 벽돌들 아래로 가도록 다 정리 해야함
    }

    static void explose(int r, int c, int power, Queue<int[]> queue) {  // 벽돌 깨지는거 그냥 폭파로 표현함
        matrix[r][c] = 0;
        for(int i=0; i<4; i++) {
            for(int j=1; j<power; j++) {    // 폭발 반경은 파워만큼 지정됨;
                int nr = r + dr[i] * j;
                int nc = c + dc[i] * j;

                if(nr<0 || nc<0 || nr>=H || nc>=W) break; // 한번 범위가 유효하지 않으면 그 다음 j에 대한 범위도 유효하지 않음

                if(matrix[nr][nc] > 1) {
                    queue.offer(new int[]{nr, nc, matrix[nr][nc]}); // 1이 넘는다면 주위 벽돌에 또 영향을 끼치므로 queue에 삽입
                }

                matrix[nr][nc] = 0;
            }
        }
    }

    static void sortBlock() { // 벽돌 다 깨지면 벽돌들 아래로 가도록 다 정리 해야함
        for(int i=0; i<W; i++) {    // 각 열마다 다 정리해줘야 함
            int index = H;  
            for(int j=H-1; j>=0; j--) {
                if(matrix[j][i] == 0 && index == H) { // 최초의 0자리가 시작 인덱스 자리임
                    index = j;
                    break;
                }
            }

            if(index == H) continue;
            
            for(int j=index-1; j>=0; j--) {
                if(matrix[j][i] != 0) {
                    matrix[index--][i] = matrix[j][i];  // 0이 아닌 숫자는 땡겨져야 함
                }
                matrix[j][i] = 0;
            }
        }
    }

    static int countBlock() {
        int result = 0;

        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix[i].length; j++) {
                if(matrix[i][j] > 0) result += 1;   // 벽돌이 안 깨져있다면 결과값 + 1
            }
        }
        return result;
    }

    static int[][] deepcopy(int[][] map) {
        int[][] result = new int[H][W];

        for(int i=0; i<result.length; i++) {
            System.arraycopy(map[i], 0, result[i], 0, result[i].length);
        }

        return result;
    }

}