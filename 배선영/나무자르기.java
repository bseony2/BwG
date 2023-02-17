import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class 나무자르기 {

    static int N;
    static int M;
    static int[] trees;
    static int answer = Integer.MIN_VALUE;
    static int tallTree = Integer.MIN_VALUE;

    static void input() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        trees = new int[N];
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) {
            int height = Integer.parseInt(st.nextToken());
            tallTree = tallTree > height ? tallTree : height;
            trees[i] = height;
        }
        Arrays.sort(trees);
    }

    static int cutting(int height) {
        int result = 0;
        for(int i=0; i<trees.length; i++) {
            int value = trees[i] - height;
            result += value > 0 ? value : 0;
        }

        return result;
    }

    public static void main(String[] args) throws IOException{
        input();

        int left = 0;
        int right = tallTree;

        while(left <= right) {
            int mid = (right + left) / 2;
            if(cutting(mid) >= M) {
                answer = answer > mid ? answer : mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(answer);
    }
}


// 4 7
// 20 15 10 17

// 15


// 5 20
// 4 42 40 26 46

// 36