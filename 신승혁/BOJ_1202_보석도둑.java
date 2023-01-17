import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1202_보석도둑 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            long M = Long.parseLong(st.nextToken());
            long V = Long.parseLong(st.nextToken());
            jewels.add(new Jewel(M, V));
        }
        Collections.sort(jewels);

        List<Long> bags = new ArrayList<>();
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            long C = Long.parseLong(st.nextToken());
            bags.add(C);
        }
        Collections.sort(bags);

        long sum = 0;
        for (long bag : bags) {
            long max = -1;
            int maxIndex = -1;

            // 훔치기 시작
            for (int i = 0; i < jewels.size(); i++) {
                Jewel jewel = jewels.get(i);
                if (bag < jewel.M) { // 가방크기가 작으면
                    break;
                }
                max = Math.max(max, jewel.V);
                maxIndex = i;
            }

            // 이번 가방에 못 담음
            if(maxIndex == -1){
                continue;
            }

            sum += max;
            jewels.remove(maxIndex);
        }

        System.out.println(sum);
    }

    private static class Jewel implements Comparable<Jewel> {
        long M; // 무게
        long V; // 가격

        public Jewel(long m, long v) {
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


        @Override
        public int compareTo(Jewel o) {
            // 1. 무게 오름차순
            if (o.M > this.M) return -1;
            if (o.M < this.M) return 1;

            // 2. 가격 내림차순
            if (o.V < this.V) return -1;
            if (o.V > this.V) return 1;

            return 0;
        }
    }
}
/*

4 2
1 65
5 23
2 99
2 80


* */