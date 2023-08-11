import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.PriorityQueue;

class 카드정렬하기 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int answer = 0;

        for(int i=0; i<N; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        while(queue.size() > 1) {
            int dummyA = queue.poll();
            int dummyB = queue.poll();

            int result = dummyA + dummyB;
            answer += result;
            queue.offer(result);
        }

        bw.write(String.valueOf(answer));
        bw.flush();
    }

    
}