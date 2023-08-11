import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기 {
    static int answer = Integer.MAX_VALUE;
    static int[][] board = new int[10][10];
    static int[] paper = new int[]{5, 5, 5, 5, 5, 5};

    public static void main(String...args) throws IOException{
        getInput();

        dfs(0, 0, 0);

        System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
    }

    static void getInput() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void dfs(int r, int c, int depth) {
        if(depth >= answer)  {
            return;
        }
        if(c >= 10) {
            if(r >= 9) {
                answer = answer > depth ? depth : answer;
            }
            else {
                dfs(r+1, 0, depth);
            }
            return;
        }
        
        if(board[r][c] == 1) {
            for(int i=5; i>=1; i--) {
                if(attatchAble(r, c, i) && paper[i] > 0) {
                    doPaperAction(r, c, i);
                    dfs(r, c+i, depth+1);
                    doPaperAction(r, c, i);
                }
            }
        }
        else {
            dfs(r, c+1, depth);
        }
    }

    static boolean attatchAble(int r, int c, int size) {
        if(r+size>10 || c+size >10) {
            return false;
        }

        for(int i=r; i<r+size; i++) {
            for(int j=c; j<c+size; j++) {
                if(board[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void doPaperAction(int r, int c, int size) {
        int actionValue;
        if(board[r][c] == 1) {
            actionValue = 0;
            paper[size] -= 1;
        } else {
            actionValue = 1;
            paper[size] += 1;
        }

        for(int i=r; i<r+size; i++) {
            for(int j=c; j<c+size; j++) {
                board[i][j] = actionValue;
            }
        }
    }
}
