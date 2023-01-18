import java.util.Queue;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
public class 숨바꼭질 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static Queue<Integer> que = new LinkedList<Integer>();
    static int[] arr = new int[100001];
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        arr[n] = 0;
        que.offer(n);

        while(!que.isEmpty()) {
            int location = que.poll();
            int nextLocation = 0;
            for(int i=0; i<3; i++) {
                if(i == 0) {
                    nextLocation = location + 1;
                }
                else if(i == 1) {
                    nextLocation = location - 1;
                }
                else {
                    nextLocation = location * 2;
                }

                if(location == k) {
                    System.out.println(arr[location]);
                    return;
                }

                if(0 <= nextLocation && nextLocation < arr.length && arr[nextLocation] == 0) {
                    que.offer(nextLocation);
                    arr[nextLocation] = arr[location] + 1;
                }
            }
        }
        
    }
}