import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static char[] input;
    static int answer = Integer.MIN_VALUE;
    static public void main(String...args) throws IOException {
        initialize();

        calculate(input[0]-'0', 2);

        System.out.println(answer);
    }

    static void initialize() throws IOException{
        N = Integer.parseInt(br.readLine());
        input = br.readLine().toCharArray();
    }

    static void calculate(int value, int index) {
        if(index >= N) {
            answer = answer > value ? answer : value;
            return;
        }

        int nextValue = calculateOne(value, input[index-1], input[index]-'0');
        calculate(nextValue, index+2);

        if(index < N-1) {
            nextValue = calculateOne(input[index]-'0', input[index+1], input[index+2]-'0');
            nextValue = calculateOne(value, input[index-1], nextValue);
            calculate(nextValue, index+4);
        }
    }

    static int calculateOne(int a, char sign, int b) {
        int answer;
        if(sign == '+') {
            answer = a+b;
        } else if (sign == '-') {
            answer = a-b;
        } else {
            answer = a*b;
        }
        return answer;
    }
}