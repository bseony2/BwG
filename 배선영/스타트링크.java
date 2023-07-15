import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;

public class 스타트링크 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] floors;
    static int F, S, G, U, D;
    static Queue<Integer> queue = new LinkedList<>();
    static public void main(String...args) throws IOException{
        input();

        initialize();

        search();

        System.out.println(floors[G] > 0 ? floors[G]-1 : "use the stairs");
        
    }

    static void input() throws IOException{
        String[] input = br.readLine().split(" ");
        F = Integer.parseInt(input[0]);
        S = Integer.parseInt(input[1]);
        G = Integer.parseInt(input[2]);
        U = Integer.parseInt(input[3]);
        D = Integer.parseInt(input[4]);
    }

    static void initialize() {
        floors = new int[F+1];
        floors[S] = 1;
        queue.add(S);
    }

    static void search() {
        while(!queue.isEmpty()) {
            int currentFloor = queue.poll();

            setButtonCnt(currentFloor + U, floors[currentFloor] + 1);
            setButtonCnt(currentFloor - D, floors[currentFloor] + 1);
        }
    }

    static void setButtonCnt(int floor, int cnt) {
        if(isValidFloor(floor) && floors[floor] == 0) {
            queue.add(floor);
            floors[floor] = cnt;
        }
    }
    
    static boolean isValidFloor(int floor) {
        return 1<=floor && floor<=F;
    }
}
