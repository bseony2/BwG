import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1202_보석도둑_2트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Jewel> jewels = new LinkedList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            jewels.add(new Jewel(M, V));
        }
        Collections.sort(jewels, (a, b) -> (a.M - b.M));


        List<Integer> bags = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            int C = Integer.parseInt(st.nextToken());
            bags.add(C);
        }
        Collections.sort(bags);

        long sum = 0;
        PriorityQueue<Jewel> pq = new PriorityQueue<>((a, b) -> b.V - a.V);
        for (int bag : bags) {

            // 훔치기 시작
            while (true) {
                if (jewels.isEmpty()) {
                    break;
                }

                Jewel jewel = jewels.get(0);
                // 가방에 넣을 크기가 되면 다 담는다
                if (bag < jewel.M) {
                    break;
                }

                Jewel removed = jewels.remove(0);
                pq.offer(removed);
            }

            // 다음가방
            if (pq.isEmpty()) {
                continue;
            }
            sum += pq.poll().V;
        }

        System.out.println(sum);
    }

    private static class Jewel {
        int M; // 무게
        int V; // 가격

        public Jewel(int m, int v) {
            M = m;
            V = v;
        }

        @Override
        public String toString() {
            return "Jewel{" +
                    "M=" + M +
                    ", V=" + V +
                    '}';
        }

    }
}
/*

3 3
20 12
0 20
16 16
17
14
7

20[x]
36[o]
* */