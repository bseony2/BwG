package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B9095_123더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringBuilder rslt = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            int[] d = new int[11];

            d[1] = 1;
            d[2] = 2;
            d[3] = 4;

            for (int j = 4; j <= num; j++) {
                d[j] = d[j - 1] + d[j - 2] + d[j - 3];
            }

            rslt.append(d[num] + "\n");
        }

        System.out.println(rslt);
    }
}