package Boj.silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Boj_12761 {
    static int A,B,N,M, result=0;
    static boolean visited[] = new boolean[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        bfs();
        System.out.println(result);
    }
    private static void bfs() {
        // TODO Auto-generated method stub
        Queue<P> queue = new LinkedList<>();
        queue.add(new P(N,0));
        visited[N] = true;
        while(!queue.isEmpty()) {
            
            P p = queue.poll();
            
            if(p.position == M) {
                result = p.answer;
                return;
            }
            
            if(p.position + 1 < 100001 && !visited[p.position+1]) {
                visited[p.position+1] = true;
                queue.add(new P(p.position+1, p.answer+1));
            }
            if(p.position - 1 >= 0 && !visited[p.position-1]) {
                visited[p.position-1] = true;
                queue.add(new P(p.position-1, p.answer+1));
            }
            if(p.position + A < 100001 && !visited[p.position+A]) {
                visited[p.position+A] = true;
                queue.add(new P(p.position+A, p.answer+1));
            }
            if(p.position - A >= 0 && !visited[p.position-A]) {
                visited[p.position-A] = true;
                queue.add(new P(p.position-A, p.answer+1));
            }
            if(p.position + B < 100001 && !visited[p.position+B]) {
                visited[p.position+B] = true;
                queue.add(new P(p.position+B, p.answer+1));
            }
            if(p.position - B >= 0 && !visited[p.position-B]) {
                visited[p.position-B] = true;
                queue.add(new P(p.position-B, p.answer+1));
            }
            if(p.position * A < 100001 && !visited[p.position*A]) {
                visited[p.position*A] = true;
                queue.add(new P(p.position*A, p.answer+1));
            }
            if(p.position * B < 100001 && !visited[p.position*B]) {
                visited[p.position*B] = true;
                queue.add(new P(p.position*B, p.answer+1));
            }
        }
    }
    
    static class P {
    	int position;
    	int answer;
		public P(int position, int answer) {
			this.position = position;
			this.answer = answer;
		}
    	
    }
}