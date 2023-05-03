import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 컨베이어벨트위의로봇 {
    static int N;
    static int K;
    static int[] power;
    static boolean[] onRail;
    static int brokenRail = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        power = new int[2*N];
        onRail = new boolean[2*N];
        int turn = 0;

        for(int i=0; i<2*N; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        while(brokenRail < K) {
            turn += 1;
            moveRail();
            moveRobot();
            if(power[0] > 0) {
                onRail[0] = true;
                power[0] -= 1;
                if(power[0] == 0) brokenRail += 1;
            }
        }

        System.out.println(turn);
    }

    static void moveRail() {
        // 내구도 한바퀴
        int temp = power[power.length-1];
        System.arraycopy(power, 0, power, 1, power.length-1);
        power[0] = temp;
        // 로봇들도 레일따라 한바퀴
        System.arraycopy(onRail, 0, onRail, 1, onRail.length-1);
        onRail[0] = false;
        // 내리는 위치에서 내리기
        onRail[N-1] = false;
    }

    static void moveRobot() {   // 로봇들 한칸씩 이동
        for(int i=N-2; i>=0; i--) {
            if(onRail[i] && !onRail[i+1] && power[i+1]>0) {
                onRail[i] = false;
                onRail[i+1] = true;
                power[i+1] -= 1;
                if(power[i+1] == 0) brokenRail += 1;
            }
        }
        onRail[N-1] = false;
    }
}
