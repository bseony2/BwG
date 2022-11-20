import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.stream.Stream;

public class 빗물{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] size = Stream.of(br.readLine().split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
        int H = size[0];
        int W = size[1];
        int[] array = Stream.of(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        int answer = 0;
        int leftIndex = 0;
        int rightIndex = W - 1;
        for(int height=1; height<=H; height++) {
            boolean isOK = false;

            for(int left = leftIndex; left < W; left++) {
                if(array[left] >= height) {
                    leftIndex = left;
                    break;
                }
            }

            for(int right = rightIndex; right > 0; right--) {
                if(array[right] >= height) {
                    rightIndex = right;
                    if(leftIndex != rightIndex)
                        isOK = true;
                    break;
                }
            }

            if(isOK) {
                answer += cal(array, leftIndex, rightIndex, height);
            } else {
                break;
            }
        }
        System.out.println(answer);
    }

    static int cal(int[] array, int left, int right, int height) {
        int answer = 0;
        for(int i = left + 1; i < right; i++) {
            if(array[i] < height)
                answer++;
        }

        return answer;
    }
}
