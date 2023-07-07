package baekjoon.greedy;

import java.io.*;
import java.util.StringTokenizer;

/*
    제발 문제 좀 잘 읽어 삽질을 얼마나 해야하는거야 대체
    '단 한 번의 왕복만 한다.'

    근데 왜 이게 그리디야
*/
public class B17262_팬덤이_넘쳐흘러 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 팬의 수
        int start = -1;
        int end = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s > start) start = s;
            if (e < end) end = e;
        }

        System.out.println(start - end < 0 ? 0 : start - end);
    }
}
