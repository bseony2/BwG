import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.lang.Comparable;

public class 격자숫자놀이 {

    static class Pair implements Comparable<Pair>{
        int value;
        int cnt;

        public Pair(int value, int cnt) {
            this.value = value;
            this.cnt = cnt;
        }

        public int compareTo(Pair pair) {
            if(this.cnt != pair.cnt) return this.cnt - pair.cnt;
            else return this.value - pair.value;
        }
    }
    static int[][] matrix;
    static int r, c, k, size;
    static ArrayList<PriorityQueue<Pair>> queueList;
    public static void main(String...args) throws IOException {
        initialize();

        int answer = 101;
        for(int i=0; i<=100; i++) {
            if(isMatrixSizeOk() && isCorrectValue()) {
                answer = i;
                break;
            }

            play();
        }

        System.out.println(answer < 101 ? answer : -1);
    }

    static void initialize() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        matrix = new int[3][3];
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<3; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean isMatrixSizeOk() {
        return matrix.length > r && matrix[0].length > c;
    }

    static boolean isCorrectValue() {
        return matrix[r][c] == k;
    }

    static void play() {
        queueList = new ArrayList<>();
        size = 0;
        boolean isRowPlay  = isRowPlay();

        if(isRowPlay) {
            cntValueByRow();
        } else {
            cntValueByCol();
        }

        int rowSize = isRowPlay ? queueList.size() : size;
        int colSize = isRowPlay ? size : queueList.size();
        matrix = new int[rowSize][colSize];

        if(isRowPlay) {
            initMatrixByRow();
        } else {
            initMatrixByCol();
        }
    }

    static void cntValueByRow() {
        for(int i=0; i<matrix.length; i++) {
            int[] valueArray = new int[101];
            for(int j=0; j<matrix[0].length; j++) {
                valueArray[matrix[i][j]] += 1;
            }

            setQueue(valueArray);
        }
    }

    static void cntValueByCol() {
        for(int i=0; i<matrix[0].length; i++) {
            int[] valueArray = new int[101];
            for(int j=0; j<matrix.length; j++) {
                valueArray[matrix[j][i]] += 1;
            }

            setQueue(valueArray);
        }
    }

    static void setQueue(int[] array) {
        PriorityQueue<Pair> queue = arrayToPairQueue(array);
        size = queue.size() * 2 > size ? queue.size()*2 : size;
        queueList.add(queue);
    }

    static PriorityQueue<Pair> arrayToPairQueue(int[] array) {
        PriorityQueue<Pair> result = new PriorityQueue<Pair>();
        for(int i=1; i<array.length; i++) {
            if(array[i] > 0) {
                result.add(new Pair(i, array[i]));
            }
        }
        return result;
    }

    static void initMatrixByRow() {
        for(int i=0; i<matrix.length; i++) {
            PriorityQueue<Pair> queue = queueList.get(i);
            int limit = queue.size()*2;
            for(int j=0; j<limit; j+=2) {
                Pair pair = queue.poll();
                matrix[i][j] = pair.value;
                matrix[i][j+1] = pair.cnt;
            }
        }
    }

    static void initMatrixByCol() {
        for(int i=0; i<matrix[0].length; i++) {
            PriorityQueue<Pair> queue = queueList.get(i);
            int limit = queue.size()*2;
            for(int j=0; j<limit; j+=2) {
                Pair pair = queue.poll();
                matrix[j][i] = pair.value;
                matrix[j+1][i] = pair.cnt;
            }
        }
    }

    static boolean isRowPlay() {
        return matrix.length >= matrix[0].length;
    }
}
