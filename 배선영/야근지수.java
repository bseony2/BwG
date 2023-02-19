import java.util.PriorityQueue;
import java.util.Arrays;

public class 야근지수 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] works = new int[] {4, 3, 3};
        System.out.println(solution.solution(4, works));
    }
}

class Solution {
    public long solution(int n, int[] works) {
        if( Arrays.stream(works).sum() < n ) return 0;

        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>((a, b) -> b - a);

        for(int work : works) {
            pQueue.add(work);
        }

        for(int i=0; i<n; i++) {
            pQueue.add(pQueue.poll() - 1);
        }

        return pQueue.stream().map(work -> (long)Math.pow(work, 2)).reduce((a, b) -> a+b).get();
    }
}