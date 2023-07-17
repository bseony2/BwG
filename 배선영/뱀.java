import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class 뱀 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, K, L;
    static boolean[][] appleLocation;
    static boolean[][] snakeMap;
    static ArrayList<int[]> snake = new ArrayList<>();
    static Queue<String[]> signs = new LinkedList<>();
    static int[] dr = new int[]{0, 1, 0, -1}; // R D L U
    static int[] dc = new int[]{1, 0, -1, 0};
    static int direction;

    static public void main(String...args) throws IOException{
        input();

        initialize();

        int answer = play();

        System.out.println(answer);
    }

    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        appleLocation = new boolean[N][N];
        snakeMap = new boolean[N][N];

        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            appleLocation[r][c] = true;
        }

        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            String[] sign = br.readLine().split(" ");
            signs.add(sign);
        }
    }

    static void initialize() {
        snakeMap[0][0] = true;
        snake.add(new int[]{0, 0});
        direction = 0;
    }

    static int play() {
        int time = 0;

        while(true) {
            time += 1;

            moveHead();

            if(isGameEnd()) {
                break;
            }

            if(isAppleOnHead()) {
                eatApple();
            } else {
                moveTail();
            }

            if(isTimeToChangDirection(time)) {
                setDirection();
            }
        }

        return time;
    }

    static void moveHead() {
        int[] snakeHead = getHead();
        int nr = snakeHead[0] + dr[direction];
        int nc = snakeHead[1] + dc[direction];
        snake.add(new int[]{nr, nc});
    }

    static int[] getHead() {
        return snake.get(snake.size()-1);
    }

    static boolean isGameEnd() {
        int[] snakeHead = getHead();
        return !isValidPoint(snakeHead[0], snakeHead[1]) || isSnakeBody(snakeHead[0], snakeHead[1]);
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static boolean isSnakeBody(int r, int c) {
        for(int i=0; i<snake.size()-2; i++) {   // size()-1 값은 현재 뱀 대가리 위치임
            int[] point = snake.get(i);
            if(point[0] == r && point[1] == c) {
                return true;
            }
        }
        return false;
    }

    static boolean isAppleOnHead() {
        int[] snakeHead = getHead();
        return appleLocation[snakeHead[0]][snakeHead[1]];
    }

    static void eatApple() {
        int[] snakeHead = getHead();
        appleLocation[snakeHead[0]][snakeHead[1]] = false;
    }

    static void moveTail() {
        snake.remove(0);
    }

    static boolean isTimeToChangDirection(int time) {
        if(signs.size() == 0) {
            return false;
        } else {
            return Integer.parseInt(signs.peek()[0]) == time;
        }
    }

    static void setDirection() {
        String sign = signs.peek()[1];

        if("L".equals(sign)) {
            direction = (direction + 3) % 4;
        } else {
            direction = (direction + 1) % 4;
        }
        
        signs.poll();
    }
}
