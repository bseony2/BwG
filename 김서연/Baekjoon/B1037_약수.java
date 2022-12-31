package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1037_약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] dvsr = new int[N];
        int rslt, max, min= 0;

        while (st.hasMoreTokens()) {
            for (int i = 0; i < N; i++) {
                dvsr[i] = Integer.parseInt(st.nextToken());
            }
        }

        if (N > 1) {
            max = dvsr[0];
            min = dvsr[0];

            for (int i = 1; i < N; i++) {
                if (dvsr[i] > max) max = dvsr[i];
                if (dvsr[i] < min) min = dvsr[i];
            }

            rslt = max * min;
        } else if (N == 1) {
            rslt = dvsr[0] * dvsr[0];
        } else {
            rslt = N;
        }

        System.out.println(rslt);
    }
}
