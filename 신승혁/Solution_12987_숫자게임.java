import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_12987_숫자게임 {
    public static void main(String[] args) {
        Solution_12987_숫자게임 sol = new Solution_12987_숫자게임();

        int[] A = {5, 1, 3, 7};
        int[] B = {2, 2, 6, 8};

        int result = sol.solution(A, B);
//        int result = sol.solution2(A, B);
        System.out.println(result);


    }

    private int solution2(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int bi = 0;
        int count =0;
        for (int ai = 0; ai < A.length; ai++) {

            while (bi < B.length) {
                if (A[ai] < B[bi]) {
                    count ++;
                    bi++;
                    break;
                }

                bi++;
            }

        }
        return count;
    }

    public int solution(int[] A, int[] B) {

        Arrays.sort(A);
        PriorityQueue<Integer> pq = new PriorityQueue((a, b) -> (int) a - (int) b); // MinHeap B
        for (int b : B) {
            pq.offer(b);
        }

        int count = 0;
        for (int a : A) {
            while (!pq.isEmpty()) {
                if (a < pq.poll()) {
                    count++;
                    break;
                }

            }

        }

        return count;
    }

}
