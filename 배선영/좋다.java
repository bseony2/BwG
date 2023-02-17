import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class 좋다{
    
	public static void main (String[] args) throws IOException {

        int answer = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int[] arr = Arrays.stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .sorted()
                            .toArray();

        for(int i=0; i<N; i++) {
            int target = arr[i];
            int left = 0;
            int right = N - 1;

            while(true) {
                if(left == i) left++;
                if(right == i) right--;
                if(left>=right) break;
                int sum = arr[left] + arr[right];

                if(sum < target) {
                    left++;
                } else if(sum > target) {
                    right--;
                } else {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);
	}
}