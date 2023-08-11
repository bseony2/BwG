import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

class 구슬탈출 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] initBoard;
    static int N;
    static int M;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static Queue<Object[]> queue = new LinkedList<>();
    static boolean otherMarbleMoveable;
    static public void main(String...args) throws IOException{
        input();

        int answer = play();

        System.out.println(answer);
    }

    static void input() throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        initBoard = new String[N][M];
        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split("");
            for(int j=0; j<M; j++) {
                initBoard[i][j] = input[j];
            }
        }
    }

    static int play() {
        //HashMap<String[][], Integer>;
        queue.add(new Object[]{initBoard, 0});
        while(!queue.isEmpty()) {
            Object[] currentBoard = queue.poll();
            String[][] board = (String[][])currentBoard[0];
            int depth = (int)currentBoard[1];

            for(int i=0; i<4; i++) {
                String[][] nextBoard = move(board, i);

                if(isSameBoard(board, nextBoard)) {
                    continue;
                }

                boolean[] result = marbleExist(nextBoard);

                if(!result[1]) { // 파란공이 사라짐
                    continue;
                }

                if(!result[0] && result[1]) {
                    return depth+1;
                }
                if(result[0] && result[1] && depth+1 < 10) {
                    queue.add(new Object[]{nextBoard, depth+1});
                }
            }
        }

        return -1;
    }

    static String[][] move(String[][] baseBoard, int direction) {
        int[] marbleLocation = findMarble(baseBoard);
        String[][] board = copyBoard(baseBoard);
        
        otherMarbleMoveable = true;
        moveMarble(board, marbleLocation[0], marbleLocation[1], direction);
        if(otherMarbleMoveable) {
            moveMarble(board, marbleLocation[2], marbleLocation[3], direction);
        }

        return board;
    }

    static int[] findMarble(String[][] board) {
        int[] answer = new int[4];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if("R".equals(board[i][j])) {
                    answer[0] = i;
                    answer[1] = j;
                }
                if("B".equals(board[i][j])) {
                    answer[2] = i;
                    answer[3] = j;
                }
            }
        }
        return answer;
    }

    static String[][] copyBoard(String[][] board) {
        String[][] answer = new String[N][M];
        for(int i=0; i<N; i++) {
            System.arraycopy(board[i], 0, answer[i], 0, answer[0].length);
        }
        return answer;
    }

    static void moveMarble(String[][] board, int r, int c, int direction) {
        int tempR = r;
        int tempC = c;
        while(true) {
            int nr = tempR + dr[direction];
            int nc = tempC + dc[direction];

            if(isWall(board, nr, nc)) {
                changeLocation(board, r, c, tempR, tempC);
                break;
            }

            if(isMarble(board, nr, nc)) {
                if(otherMarbleMoveable) {
                    otherMarbleMoveable = false;
                    moveMarble(board, nr, nc, direction);
                    continue;
                }
                changeLocation(board, r, c, tempR, tempC);
                break;
            }

            if(isHole(board, nr, nc)) {
                board[r][c] = ".";
                break;
            }

            tempR = nr;
            tempC = nc;
        }
    }

    static void changeLocation(String[][] board, int marbleR, int marbleC, int newR, int newC)
    {
        if(newR != marbleR || newC != marbleC) {
            String temp = board[marbleR][marbleC];
            board[marbleR][marbleC] = ".";
            board[newR][newC] = temp;
        }
    }

    static boolean isWall(String[][] board, int r, int c) {
        return "#".equals(board[r][c]);
    }

    static boolean isMarble(String[][] board, int r, int c) {
        return "B".equals(board[r][c]) || "R".equals(board[r][c]);
    }

    static boolean isHole(String[][] board, int r, int c) {
        return "O".equals(board[r][c]);
    }

    static boolean[] marbleExist(String[][] board) {
        boolean[] answer = new boolean[2];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if("R".equals(board[i][j])) {
                    answer[0] = true;
                }
                if("B".equals(board[i][j])) {
                    answer[1] = true;
                }
            }
        }
        return answer;
    }

    static boolean isSameBoard(String[][] board, String[][] nextBoard) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!board[i][j].equals(nextBoard[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }
}