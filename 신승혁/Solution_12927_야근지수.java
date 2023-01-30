import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution_12927_야근지수 {
    public static void main(String[] args) {
        Solution_12927_야근지수 sol = new Solution_12927_야근지수();

        int[] works = {4, 3, 3};
        int n = 4;
        long result = sol.solution(n, works);
        System.out.println(result);
    }

    public long solution(int N, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a); // maxHeap
        for (int work : works) {
            pq.offer(work);
        }


        for (int n = 0; n < N; n++) {
            int work = pq.poll();
            if (work == 0) return 0;

            pq.offer(--work);
        }

        // 제곱합
        return pq.stream().map(a -> (long) Math.pow(a, 2)).reduce((a, b) -> a + b).get();
    }

}
