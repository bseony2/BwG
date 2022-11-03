package w2;

import java.io.*;

public class p23814 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int start = 1, end = 9, n = 1;
        long ans = 0;

        while(!(start <= N && N <= end)){
            ans += (end - start + 1) * n++;
            start *= 10;
            end *= 10; end += 9;
        }
        ans += (N - start + 1) * n;
        bw.write(ans +"\n");
        bw.flush();
        bw.close();
    }
}
