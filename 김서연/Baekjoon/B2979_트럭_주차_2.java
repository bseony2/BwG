package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2979_트럭_주차_2 {
    static int[] arr = new int[100]; // 입력으로 주어지는 시간은 1과 100사이 이다.
    static int sum = 0;
    static int feeA;
    static int feeB;
    static int feeC;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        feeA = Integer.parseInt(st.nextToken());
        feeB = Integer.parseInt(st.nextToken());
        feeC = Integer.parseInt(st.nextToken());
        int start, end = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        // 입력 받으면서 arr배열 증가처리
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            for (int j = start; j < end; j++) { // 떠난시간 포함하지 않음
                arr[j]++;
            }

            min = Math.min(min, start);
            max = Math.max(max, end);
        }

        getTotalFee(arr, min, max);
    }

    private static void getTotalFee(int[] arr, int min, int max) {
        for (int i = min; i < max; i++) {
            switch (arr[i]) {
                case 1:
                    sum = sum + feeA * arr[i];
                    break;
                case 2:
                    sum = sum + feeB * arr[i];
                    break;
                case 3:
                    sum = sum + feeC * arr[i];
                    break;
            }
        }

        System.out.println(sum);
    }
}
