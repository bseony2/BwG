package baekjoon.two_pointer;

import java.io.*;
import java.util.StringTokenizer;

public class B1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];
        int startIdx = 0;
        int endIdx = 0;
        int sum = 0;
        int length = Integer.MAX_VALUE; // Math.min 비교위해 MAX 초기화

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (startIdx <= N && endIdx <= N) {
            if (sum >= S) {
                sum -= arr[startIdx++];
                length = Math.min(length, (endIdx - startIdx) + 1);
            }
            else if (sum < S) sum += arr[endIdx++];
        }

        System.out.println(Integer.MAX_VALUE == length ? 0 : length);
    }
}
