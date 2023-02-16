import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 게임개발 {
    
    static ArrayList<ArrayList<Integer>> bldList = new ArrayList<>();
    static int[] degree;
    static int[] finishTime;
    static int[] bldTime;
    static int N;

    private static void _proceed() {
        Queue<Integer> queue = new LinkedList<>();

        for(int i=1; i<=N; i++) {
            if(degree[i] == 0) queue.add(i);
            finishTime[i] = bldTime[i];
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int nextBld : bldList.get(now)) {
                degree[nextBld]--;
                finishTime[nextBld] = Math.max(finishTime[nextBld], finishTime[now] + bldTime[nextBld]);

                if(degree[nextBld] == 0) queue.add(nextBld);
            }
        }
    }

    private static void _init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        degree = new int[N + 1];
        finishTime = new int[N + 1];
        bldTime = new int[N + 1];

        for(int i=0; i<=N; i++) {
            bldList.add(new ArrayList<>());
        }

        for(int bldNum=1; bldNum<=N; bldNum++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            bldTime[bldNum] = Integer.parseInt(st.nextToken());

            while(true) {
                int nextBld = Integer.parseInt(st.nextToken());
                if(nextBld == -1) break;

                bldList.get(nextBld).add(bldNum);
                degree[bldNum]++;
            }
        }
    }

    private static void _print() {
        for(int i=1; i<=N; i++) {
            System.out.println(finishTime[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        
        _init();

        _proceed();

        _print();
    }
}
