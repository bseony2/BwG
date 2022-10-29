/**
 * BOJ - 순서쌍의 곱의 합
 * https://www.acmicpc.net/problem/13900
 *
 * 수학(구현)
 */

import java.io.*;
import java.util.StringTokenizer;

public class p13900 {


    static long[] arr  = new long[100002];
    static long[] Sarr = new long[100002];
    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        for(int i = N; i >= 1; i--){
            Sarr[i] += Sarr[i+1] + arr[i];
        }

        bw.write(calc()+"\n");

        bw.flush();
        bw.close();
    }
    private static long calc() {
        long result = 0;
        for(int i = 1; i < N ; i++) {
            result += ((Sarr[i] - arr[i]) * arr[i]);
//            System.out.println("result = " + result);
        }
        return result;
    }
}
