import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 잃어버린괄호 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split("-");
        int answer = 0;

        for(int i = 0 ; i < input.length ; i++){
            int sum = 0;
            if(input[i].contains("+")){
                String[] nums = input[i].split("\\+");
                for(int j = 0 ; j < nums.length ; j++){
                    sum += Integer.parseInt(nums[j]);
                }
            }
            else
                sum += Integer.parseInt(input[i]);

            answer = i == 0 ? sum : answer - sum;
        }

        System.out.println(answer);
    }
}