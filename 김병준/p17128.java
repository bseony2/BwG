package w1;

import java.io.*;
import java.util.StringTokenizer;

public class p17128 {

    static int[] stickers = new int[200001];
    static int N, Q;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            stickers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < Q; i++) {
            int where = Integer.parseInt(st.nextToken());
            bw.write(calc(where)+"\n");
        }
        bw.close();
    }

    private static int calc(int where){
        stickers[where-1]*=-1;
        int S = stickers[0] * stickers[1] * stickers[2] * stickers[3];
        for(int i = 1; i < N; i++){
            int sum = 1;
            for(int j = 0; j < 4; j++) {
                sum *= stickers[(i+j)%N];
            }
            S += sum;
        }
        return S;
    }
}
