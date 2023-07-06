import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 팬덤이넘쳐흘러 {

    static int N;
    static int startTime = Integer.MAX_VALUE;
    static int endTime = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            startTime = startTime < end ? startTime : end;
            endTime = endTime > start ? endTime : start;
        }

        System.out.println(startTime >= endTime ? 0 : endTime - startTime);
    }
}