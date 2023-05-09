package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  Baekjoon 2559 수열 - Silver3
 *
 * - 누적 합, 투포인터, 슬라이딩윈도우
 */
public class B2559_수열2 {
    public static void main(String[] args) throws IOException {
        int sum = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nums = Integer.parseInt(st.nextToken()); // 전체날짜
        int seqNum = Integer.parseInt(st.nextToken()); // 누적합 연속수 개수
        int[] arr = new int[nums];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < nums; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < seqNum; i++) {
            sum += arr[i]; // 초기 sum
        }
        int max = sum;

        // 투포인터 (다음 누적합의 마지막 인덱스(+), 기존 누적합의 첫 번째 인덱스(-)
        // for loop로 구현! -> 배열에 담지마..
        for (int i = seqNum, j = 0; i < nums; i++, j++) {
            sum += arr[i] - arr[j];

            if (max < sum) max = sum;
        }
        System.out.println(max);
    }
}
