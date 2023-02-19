import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 숨바꼭질4 {

    static int[] distance = new int[100001];
    static int[] root = new int[100001];
    static int N;
    static int K;
    static Queue<Integer> queue = new LinkedList<Integer>();
    public static void main(String[] args) throws IOException {

        init();

        bfs();
        
        print();
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            int currentLoc = queue.poll();
            if( isMinDis(currentLoc - 1, distance[currentLoc]) ) {
                setMinDis( currentLoc, currentLoc - 1, distance[currentLoc] );
            }

            if( isMinDis(currentLoc + 1, distance[currentLoc]) ) {
                setMinDis( currentLoc, currentLoc + 1, distance[currentLoc] );
            }

            if( isMinDis(currentLoc * 2, distance[currentLoc]) ) {
                setMinDis( currentLoc, currentLoc * 2, distance[currentLoc] );
            }

            if( currentLoc - 1 == K || currentLoc + 1 == K || currentLoc * 2 == K ) return;
        }
    }

    static void setMinDis(int loc, int nextLoc, int dis) {
        distance[nextLoc] = dis + 1;
        queue.offer(nextLoc);
        root[nextLoc] = loc;
    }

    static boolean isMinDis(int location, int dis) {
        return  0 <= location && location <= 100000 && distance[location] > dis + 1;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[N] = 0;
        root[N] = N;

        queue.offer(N);
    }

    static void print() {
        System.out.println(distance[K]);
        ArrayList<String> sb = new ArrayList<String>();

        int next = K;
        while(true) {
            sb.add(String.valueOf(next));
            if(next == N) break;
            next = root[next];
            sb.add(" ");
        }
        Collections.reverse(sb);
        
        for(String st : sb) { 
            System.out.print(st);
        }
    }
}