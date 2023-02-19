import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class p20055 {

    private static int N, K;
    private static int[] durability;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
        // 인풋값 세팅
        inputValueSetting();

        bw.write(solution() + "\n");

        bw.flush();
        bw.close();
    }

    private static int solution() {
        
        // 로봇들의 상태
        Queue<Integer> robotQ = new LinkedList<>();

        int step = 0;
        for (step = 1; ; step++) {

            System.out.println("step = " + step);

            int qSize=  robotQ.size();

            // 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
            for(int i = 0 ; i < qSize; i++) {
                int x = robotQ.poll();  // 현재 좌표.
                int nx = (x + 1) % (2 * N);
                robotQ.add(nx);
                visited[x]  = false;
                visited[nx] = true;
            }

            // 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
            for(int i = 0 ; i < qSize; i++) {
                int x = robotQ.peek();  // 현재 좌표.
                int nx = (x + 1) % (2 * N);
                System.out.println("x  = " + x);
                System.out.println("nx = " + nx);

                // 다음 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
                if(!visited[nx] && durability[nx] > 0) {
                    robotQ.poll();
                    visited[x] = false;
                    visited[nx] = true;
                    durability[nx]--;
                    robotQ.add(nx);
                }
            }

            System.out.println("qSize = " + qSize);

            // 올리는칸에 내구도가 0이 아니면, 로봇을 올린다.
            if(durability[0] > 0) {
                visited[0] = true;
                durability[0] -= 1;
                robotQ.add(0);
            }

            if(getDurabilityValueIsZeroCnt() >= K) break;
            debug();
        }
       return step - 1;
    }

    private static void inputValueSetting() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);

        durability = new int[2 * N];
        visited = new boolean[2 * N];

        line = br.readLine().split(" ");

        for(int i = 0; i < 2 * N; i++){
            durability[i] = Integer.parseInt(line[i]);
        }
    }

    private static int getDurabilityValueIsZeroCnt() {
        int res = 0;
        for(int i = 0; i < 2 * N; i++){
            if(durability[i] == 0) res++;
        }
        return res;
    }

    private static void debug(){
        System.out.println();
        for(int i = 0 ; i < 2 * N; i++){
            System.out.print(" " + durability[i]);
        }
        System.out.println();
    }
}