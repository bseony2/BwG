package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * SWEA_1280 - Flatten
 * 1. 해결방법)
 *      1) 배열 정렬
 *      2) 규칙 : 가장 큰값 -1, 작은 값 +1
 *      3) 규칙 적용 후 다시 정렬
 *      4) 인덱스[0] , [last] 차이값 반환
 */
public class S9280_진용이네주차타워2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCnt = 1; // 테스트케이스 count
        int dumpTimes;

        while (testCnt <= 10) {
            dumpTimes = Integer.parseInt(br.readLine()); // 덤프횟수, 상자의 높이값 배열 length

            StringTokenizer st = new StringTokenizer(br.readLine()); // 상자높이값
            int[] boxHeight = new int[100];

            for (int i = 0; i < 100; i++) {
                boxHeight[i] = Integer.parseInt(st.nextToken());
            }

            int dumpCnt = dumpTimes;
            Arrays.sort(boxHeight);

            if (boxHeight[0] != boxHeight[99]) { // 정렬된 상태에서 배열의 처음, 끝이 다를때만 연산수행
                while (dumpCnt > 0) {
                    boxHeight[0]++;
                    boxHeight[99]--;

                    dumpCnt--;
                    Arrays.sort(boxHeight); // 다시 정렬
                }
            }
            int diff = boxHeight[99] - boxHeight[0];

            sb.append("#").append(testCnt).append(" ").append(diff).append("\n");

            testCnt++;
        }
        System.out.println(sb);
    }
}
