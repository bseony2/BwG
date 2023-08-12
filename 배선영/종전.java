import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 종전 {
    static int N;
    static int[][] matrix;
    static boolean[][] isVisited;
    static int ans = Integer.MAX_VALUE;
    static int maxSize, minSize;
    static int total = 0;
    static int bottomR, bottomC, rightR, rightC, topR, topC, leftR, leftC;
    public static void main(String...args) throws IOException{
        generateMatrix();

        search();

        System.out.println(ans);
    }

    static void generateMatrix() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                int value = Integer.parseInt(st.nextToken());
                total += value;
                matrix[i][j] = value;
            }
        }
    }

    static void search() {
        for(int i=1; i<N-1; i++) {
            for(int j=1; j<N-1; j++) {
                searchLine(i, j);
            }
        }
    }

    static void searchLine(int r, int c) {
        int[] pointArr = new int[]{r, c, r, c, r, c, r, c};

        while(true) {
            int[] tempArr = pointArr.clone();

            while(true) {
                cntLands(tempArr);
                if(nextRightUpAble(tempArr)) {
                    rightUp(tempArr);
                } else {
                    break;
                }
            }

            if(nextLeftUpAble(pointArr)) {
                leftUp(pointArr);
            } else {
                break;
            }
        }
        
    }

    static boolean nextLeftUpAble (int[] pointArr) {
        return isValidCenterPoint(pointArr[4]-1, pointArr[5]-1) 
                && isValidCenterPoint(pointArr[6]-1, pointArr[7]-1);
    }

    static boolean nextRightUpAble (int[] pointArr) {
        return isValidCenterPoint(pointArr[2]-1, pointArr[3]+1) 
                && isValidCenterPoint(pointArr[4]-1, pointArr[5]+1);
    }

    static void leftUp(int[] pointArr) {
        pointArr[4] -= 1;
        pointArr[5] -= 1;
        pointArr[6] -= 1;
        pointArr[7] -= 1;
    }

    static void rightUp(int[] pointArr) {
        pointArr[2] -= 1;
        pointArr[3] += 1;
        pointArr[4] -= 1;
        pointArr[5] += 1;
    }

    static boolean isValidCenterPoint(int r, int c) {
        return 0<r && r<N-1 && 0<c && c<N-1;
    }

    static void cntLands(int[] pointArr) {

        setPoint(pointArr);
        maxSize = Integer.MIN_VALUE;
        minSize = Integer.MAX_VALUE;
        isVisited = new boolean[N][N];
        int sum = 0;
        for(int i=5; i>=2; i--) {
            int size = countLandSize(i);
            sum += size;
            calMinMax(size);
        }
        calMinMax(total-sum);

        ans = ans < maxSize-minSize ? ans : maxSize-minSize;
    }

    static void calMinMax(int size) {
        maxSize = maxSize > size ? maxSize : size;
        minSize = minSize < size ? minSize : size;
    }

    static void setPoint(int[] pointArr) {
        bottomR = pointArr[0]+1;
        bottomC = pointArr[1];
        rightR = pointArr[2];
        rightC = pointArr[3]+1;
        topR = pointArr[4]-1;
        topC = pointArr[5];
        leftR = pointArr[6];
        leftC = pointArr[7]-1;
    }

    static int countLandSize(int i) {
        int result = 0;
        switch(i) {
            case 2 :
                result = countSecondLand();
                break;
            case 3 :
                result = countThirdLand();
                break;
            case 4 :
                result = countFourthLand();
                break;
            case 5 :
                result = countFifthLand();
                break;
        }
        return result;
    }

    static int countSecondLand() {
        int result = 0;
        int c = leftC + 1;
        for(int i=leftR-1; i>=0; i--) {
            for(int j=0; j<c; j++) {
                result += matrix[i][j];
                isVisited[i][j] = true;
            }
            c = c <= topC ? c+1 : c;
        }
        return result;
    }

    static int countThirdLand() {
        int result = 0;
        int c = rightC;
        for(int i=rightR; i>=0; i--) {
            for(int j=N-1; j>c; j--) {
                result += matrix[i][j];
                isVisited[i][j] = true;
            }
            c = c > topC ? c-1 : c;
        }
        return result;
    }

    static int countFourthLand() {
        int result = 0;
        int c = leftC;
        for(int i=leftR; i<N; i++) {
            for(int j=0; j<c; j++) {
                result += matrix[i][j];
                isVisited[i][j] = true;
            }
            c = c < bottomC ? c+1 : c;
        }
        return result;
    }

    static int countFifthLand() {
        int result = 0;
        int c = rightC;
        for(int i=rightR+1; i<N; i++) {
            for(int j=c; j<N; j++) {
                result += matrix[i][j];
                isVisited[i][j] = true;
            }
            c = c > bottomC ? c-1 : c;
        }
        return result;
    }
}