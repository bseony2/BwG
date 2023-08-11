import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class 진용이네주차타워 {

    static int[] cost;
    static boolean[] space;
    static int[] carWeight;
    static int[] carLocated;
    static Queue<Integer> queue;
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            answer = 0;

            cost = new int[n];                          // 단위 무게당 요금
            space = new boolean[n];                          // 남은 자리 확인하기 위함
            carWeight = new int[m];                     // 차량의 무게
            carLocated = new int[m];
            queue = new LinkedList<Integer>();         // 대기 차량

            for(int i=0; i<n; i++) {
                cost[i] = Integer.parseInt(br.readLine());
            }

            for(int i=0; i<m; i++) {
                carWeight[i] = Integer.parseInt(br.readLine());
            }

            for(int i=0; i<2*m; i++) {
                int car = Integer.parseInt(br.readLine());
                if(car > 0) {
                    comeIn(car -1);   // 입차 인덱스 생각해서 1 뺌
                } else {
                    goOut(-(1+car));    // 출차 배열 인덱스 생각해서 1 더함
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    static void comeIn(int car) {
        int num = findSpace();
        if(num == -1) {
            queue.offer(car);
        } else {
            carLocated[car] = num;    // 차 위치 기록
            space[num] = true;          // 주차 자리 참
            answer += cost[num] * carWeight[car];
        }
    }

    static void goOut(int car) {
        int num = carLocated[car];  // 차가 위치해있던 자리
        if(!queue.isEmpty()) {  // 대기 차가 있으면 먼저 들어가야 함
            car = queue.poll();
            carLocated[car] = num;
            answer += cost[num] * carWeight[car];
        } else {
            space[num] = false;
        }
    }

    static int findSpace() {
        int result = -1;
        for(int i=0; i<space.length; i++) {
            if(!space[i]) {
                result = i;
                break;
            }
        }

        return result;  // -1을 리턴하면 주차장 자리 없다는 뜻
    }
}