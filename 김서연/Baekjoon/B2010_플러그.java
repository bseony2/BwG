package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B2010_플러그 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tCase = Integer.parseInt(br.readLine());
        int rslt = 0;

        for (int i = 0; i < tCase; i++) {
            if (i == tCase - 1) {
                rslt += Integer.parseInt(br.readLine());
            } else {
                rslt += Integer.parseInt(br.readLine()) - 1;
            }
        }

        System.out.println(rslt);
    } 
}
