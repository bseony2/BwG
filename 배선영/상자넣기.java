import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.stream.Stream;
import java.util.Arrays;
public class 상자넣기 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] boxSize = Stream.of(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        int[] answerArr = new int[N];

        for(int i=0; i<N; i++) {
            answerArr[i] = 1;
            for(int j=0; j<i; j++) {
                if(answerArr[i] <= answerArr[j] && boxSize[i] > boxSize[j])
                    answerArr[i] = answerArr[j] + 1;
            }
        }

        System.out.println(Arrays.stream(answerArr).max().getAsInt());
    }
}