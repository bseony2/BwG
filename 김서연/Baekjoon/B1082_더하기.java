package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1082_더하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), ",");

        int rslt = 0;

        while (st.hasMoreTokens()) {
            rslt += Integer.parseInt(st.nextToken());
        }

        System.out.println(rslt);
    }
}
