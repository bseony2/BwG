import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.stream.Stream;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Collections;

public class 단지번호붙이기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] complex;
    static boolean[][] isVisited;
    static Queue<Node> queue;
    static int numOfComplex = 0;
    static LinkedList<Integer> housePerComplex;
    public static void main(String[] args) throws IOException {
        input();

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(!isVisited[i][j] && complex[i][j] == 1) {
                    isVisited[i][j] = true;
                    bfs(new Node(j, i));
                }
            }
        }

        output();
        
    }

    private static void bfs(Node node) {
        int numOfHouse = 0;
        queue = new LinkedList<Node>();
        queue.offer(node);
        numOfComplex++;

        int[] nx = new int[]{0, 0, 1, -1};
        int[] ny = new int[]{-1, 1, 0, 0};

        while(!queue.isEmpty()) {
            numOfHouse++;
            Node currentNode = queue.poll();

            for(int i=0; i<4; i++) {
                int nextX = currentNode.x + nx[i];
                int nextY = currentNode.y + ny[i];

                if( 0 <= nextX && nextX < N && 0 <= nextY && nextY <N 
                    && !isVisited[nextY][nextX] && complex[nextY][nextX] == 1 ) {
                    isVisited[nextY][nextX] = true;
                    queue.offer(new Node(nextX, nextY));
                }
            }
        }
        housePerComplex.add(numOfHouse);
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        complex = new int[N][N];
        isVisited = new boolean[N][N];
        housePerComplex = new LinkedList<Integer>();

        for(int i=0; i<N; i++) {
            complex[i] = Stream.of(br.readLine().split(""))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
    }

    static void output() {
        System.out.println(numOfComplex);
        Collections.sort(housePerComplex);
        for(int house : housePerComplex) {
            System.out.println(house);
        }
    }

    
}
class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}