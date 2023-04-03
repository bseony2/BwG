/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.ArrayList;

class 최소이동거리
{
	public static void main(String args[]) throws Exception
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] distance = new int[N + 1];
            boolean[] isVisited = new boolean[N+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[0] = 0;
            PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            ArrayList<ArrayList<int[]>> edgeList = new ArrayList<ArrayList<int[]>>();
			for(int n=0; n<N+1; n++) {
            	edgeList.add(new ArrayList<int[]>());
            }
            for(int smallE=0; smallE<E; smallE++) {
            	st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                
                edgeList.get(s).add(new int[]{e, w});
            }
            queue.offer(new int[] {0, 0});
            while(!queue.isEmpty()) {
            	int[] current = queue.poll();
                int s = current[0];
                if(s == N) {break;}
                if(isVisited[s]){continue;}
                isVisited[s] = true;
                
                for(int[] next : edgeList.get(s)) {
                	int nextNode = next[0];
                    int nextWeight = next[1];
                    
                    if(distance[nextNode] > distance[s] + nextWeight) {
                        distance[nextNode] = distance[s] + nextWeight;
                    	queue.offer(new int[] {nextNode, distance[nextNode] });
                    }
                }
            }
            System.out.println(test_case + " " + distance[N]);
		}
	}
}
