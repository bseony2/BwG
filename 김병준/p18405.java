/**
 * BOJ - 경쟁적 전염 (18405)
 * https://www.acmicpc.net/problem/18405
 * BFS ( 우선순위 큐 사용)
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Point{
    int x, y, number;
    Point(int x, int y, int number){
        this.x = x;
        this.y = y;
        this.number = number;
    }
}

class PointComparator implements Comparator<Point> {
    @Override
    public int compare(Point o1, Point o2) {
        return o1.number - o2.number;
    }
};


public class p18405 {
    private static int N, K, S, X, Y;
    private static int board[][];
    private static int dx[] = {-1, 0, 1, 0};
    private static int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line[] = br.readLine().split(" ");

        N = Integer.parseInt(line[0]);
        K = Integer.parseInt(line[1]);
        board = new int[N][N];

        PriorityQueue<Point> pq = new PriorityQueue<>(1, new PointComparator());

        for(int i = 0; i < N; i++) {
            line = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(line[j]);
                if(board[i][j] != 0){
                    pq.add(new Point(i, j, board[i][j]));
                }
            }
        }

        line = br.readLine().split(" ");

        S = Integer.parseInt(line[0]);
        X = Integer.parseInt(line[1]);
        Y = Integer.parseInt(line[2]);

        solution(pq);

        System.out.println(board[X-1][Y-1]);
    }

    private static void solution(PriorityQueue<Point> pq) {
        for(int time = 1; time <= S; ++time) {
//            printArr();
            Queue<Point> temp = new LinkedList<>();
            while(!pq.isEmpty()) {
                int pqSize = pq.size();
                for(int i = 0; i < pqSize; i++){
                    Point p = pq.poll();
                    int x = p.x, y = p.y;
                    for(int dir = 0; dir < 4; ++dir){
                        int nx = x + dx[dir], ny = y + dy[dir];
                        if(isinRange(nx, ny) && board[nx][ny] == 0) {
                            board[nx][ny] = p.number;
                            temp.add(new Point(nx, ny, p.number));
                        }
                    }
                }
            }
            while(!temp.isEmpty()) {
                pq.add(temp.poll());
            }
        }
//        printArr();
    }

    private static boolean isinRange(int x, int y) {
        if(0 > x || x >= N || 0 > y || y >= N) return false;
        return true;
    }
    private static void printArr(){
        System.out.println();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++){
                System.out.print(board[i][j]+ " ");
            }
            System.out.println();
        }
    }
}
