import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class 부분합 {
    static int N;
    static int S;
    static int[] intArr;
    public static void main(String[] args) throws IOException {

        int answer = Integer.MAX_VALUE;
        init();

        int result = 0;
        int left = 0;
        int right = 0;

        while(right<N || result >= S) {
            if(result < S && right<N) {
                right++;
                result += intArr[right - 1];
            } else {
                left++;
                result -= intArr[left - 1];
            }
            if(result >= S) {
                answer = answer < right - left ? answer : right - left;
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);

    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[]NS = br.readLine().split(" ");
        N = Integer.parseInt(NS[0]);
        S = Integer.parseInt(NS[1]);

        intArr = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
    }
}
