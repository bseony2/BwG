import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

public class 흙길보수하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int L, N;
    static PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    public static void main(String...args) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            queue.add(new int[]{s, e});
        }

        int answer = 0;
        int road = 0;
        while(!queue.isEmpty()) {
            int[] point = queue.poll();
            if(point[0] > road) {
                road = point[0];
            }

            if(point[1] > road) {
                while(point[1] > road) {
                    road += L;
                    answer += 1;
                }
            }
        }

        System.out.println(answer);
    }

    
}
