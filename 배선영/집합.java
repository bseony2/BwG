import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class 집합 {
    static int N;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static public void main(String args[]) throws IOException {
        N = Integer.parseInt(br.readLine());
        int answer = 0;
        int value = 0;
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            String[] input = br.readLine().split(" ");
            switch (input[0]) {
                
                case "add":
                    value = Integer.parseInt(input[1]) - 1;
                    answer |= 1<<value;
                    break;

                case "remove":
                    value = Integer.parseInt(input[1]) - 1;
                    answer = answer & ~(1<<value);
                    break;

                case "check":
                    value = Integer.parseInt(input[1]) - 1;
                    sb.append((answer & (1 << value)) != 0 ? "1\n" : "0\n");
                    break;

                case "toggle":
                    value = Integer.parseInt(input[1]) - 1;
                    answer ^= (1<<value);
                    break;

                case "all":
                    answer |= (~0);
                    break;

                case "empty":
                    
                    answer &= 0;
                    break;
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
