import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

public class 정곤이의단조증가하는수 {

    static int T;
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr = new int[N];
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int result = cal(arr);
            
            sb.append("#").append(t).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static int cal(int[] arr) {
        int answer = -1;

        for(int i=0; i<arr.length-1; i++) {
            for(int j=i+1; j<arr.length; j++) {
                int val = arr[i] * arr[j];
                String s = String.valueOf(val);
                boolean isPossible = true;

                for(int k=1; k<s.length(); k++) {
                    if(s.charAt(k-1) > s.charAt(k)) {
                        isPossible = !isPossible;
                        break;
                    }
                }

                if(isPossible) answer = answer > val ? answer : val;
            }
        }

        return answer;
    }
}
