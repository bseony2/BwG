import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Collections;

class Solution {

    public static void main(String[] args) {
        int[] priorities = {2, 1, 3, 2};
        solution(priorities, 2);
    }

    static public int solution(int[] priorities, int location) {

        
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        Queue<Integer> q = new LinkedList<Integer>();

        for(int value : priorities) {
            pq.offer(value);
            q.offer(value);
        }
        
        while(q.peek() != null) {
            
            int max = pq.poll();
            for(int i=0; i<q.size(); i++) {
                
                int selected = q.peek();
                if(selected == max) {
                    
                    answer++;
                    if(location == 0)
                        return answer;
                    q.poll();
                    location--;
                    break;
                }
                else {
                    q.offer(q.poll());
                    location = location == 0 ? q.size() - 1 : location - 1;
                }
            }
        }
        return answer;
    }
}