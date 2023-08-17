import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.LinkedList;

public class 이차원테트리스 {
    static boolean[][] yellowBox = new boolean[6][4];
    static boolean[][] redBox = new boolean[6][4];
    static Queue<int[]> signs = new LinkedList<>();
    static int score = 0;
    public static void main(String[] args) throws IOException{
        initialize();

        play();

        System.out.println(score);
        System.out.println(countBlock());
    }

    static void initialize() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        for(int i=0; i<k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            signs.add(new int[]{t, x, y});
        }
    }

    static void play() {
        while(!signs.isEmpty()) {
            int[] sign = signs.poll();

            setBlock(sign);
        }
    }

    static void setBlock(int[] sign) {
        int[] yellowSign = convertSigntoYellowSign(sign);
        int[] redSign = convertSigntoRedSign(sign);

        dropBlock(yellowBox, yellowSign);
        dropBlock(redBox, redSign);

        getScore(yellowBox);
        getScore(redBox);

        checkSoftRow(yellowBox);
        checkSoftRow(redBox);
    }

    static int[] convertSigntoYellowSign(int[] sign) {
        return new int[]{sign[0], sign[2]};
    }

    static int[] convertSigntoRedSign(int[] sign) {
        int t, c;
        if(sign[0] == 1) {
            t = 1;
            c = 3 - sign[1];
        } else if(sign[0] == 2) {
            t = 3;
            c = 3 - sign[1];
        } else {
            t = 2;
            c = 3 - sign[1] -1;
        }

        return new int[]{t, c};
    }

    static void dropBlock(boolean[][] box, int[] sign) {
        int r = 0;
        int c = sign[1];
        int t = sign[0];

        while(true) {
            if(t == 1) {
                if(!isValidPoint(r) || !isEmptyPoint(box, r, c)) {
                    break;
                }
            }
            else if(t == 2) {
                if(!isValidPoint(r) || !isEmptyPoint(box, r, c) || !isEmptyPoint(box, r, c+1)) {
                    break;
                }
            }
            else if(t == 3) {
                if(!isValidPoint(r) || !isValidPoint(r+1) || !isEmptyPoint(box, r, c) || !isEmptyPoint(box, r+1, c)) {
                    break;
                }
            }
            r += 1;
        }
        r -= 1;

        if(t == 1) {
            box[r][c] = true;
        } else if(t == 2) {
            box[r][c] = true;
            box[r][c+1] = true;
        } else if(t ==3) {
            box[r][c] = true;
            box[r+1][c] = true;
        }
    }

    static boolean isEmptyPoint(boolean[][] box, int r, int c) {
        return !box[r][c];
    }

    static boolean isValidPoint(int r) {
        return 0<= r & r<6;
    }

    static void getScore(boolean[][] box) {
        for(int i=2; i<6; i++) {
            if(isFullRow(box, i)) {
                score += 1;
                removeRow(box, i);
            }
        }
    }

    static boolean isFullRow(boolean[][] box, int row) {
        boolean result = true;
        for(int i=0; i<4; i++) {
            if(!box[row][i]) {
                result = false;
                break;
            }
        }
        return result;
    }

    static void removeRow(boolean[][] box, int row) {
        for(int i=row; i>=1; i--) {
            box[i] = box[i-1];
        }
        box[0] = new boolean[4];
    }

    static void checkSoftRow(boolean[][] box) {
        while(true) {
            if(!rowHasBlock(box, 1)) {
                break;
            }
            
            moveDown(box);
        }
    }

    static boolean rowHasBlock(boolean[][] box, int row) {
        boolean result = false;

        for(int i=0; i<4; i++) {
            if(box[row][i]) {
                result = true;
                break;
            }
        }
        return result;
    }

    static void moveDown(boolean[][] box) {
        for(int i=5; i>=1; i--) {
            box[i] = box[i-1];
        }
        box[0] = new boolean[4];
    }

    static int countBlock() {
        int sum = 0;

        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                if(yellowBox[i][j]) {
                    sum += 1;
                }
                if(redBox[i][j]) {
                    sum += 1;
                }
            }
        }
        return sum;
    }
}
