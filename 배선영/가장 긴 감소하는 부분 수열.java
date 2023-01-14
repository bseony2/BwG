import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Stream;
import java.util.Arrays;

public class 가장긴감소하는부분수열 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        int[] len = new int[n];

        Arrays.fill(len, 1);
        for(int i=1; i<n; i++) {
            for(int j=0; j<i; j++) {
                if(len[i] <= len[j] && arr[i] < arr[j]) {
                    len[i] = len[j] + 1;
                }
            }
        }

        System.out.println(Arrays.stream(len).max().getAsInt());

    }
}