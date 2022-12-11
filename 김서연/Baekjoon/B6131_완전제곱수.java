package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B6131_완전제곱수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        for (int i = 1; i < 501; i++) {
            double A = Math.pow(i, 2);

            for (int j = 1; j < 501; j++) {
                double B = Math.pow(j, 2);

                if (A == B + N) cnt++;
            }
        }

        System.out.println(cnt);
    }
}