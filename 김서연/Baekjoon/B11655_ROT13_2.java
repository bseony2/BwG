package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B11655_ROT13_2 {
    static char[] c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        c = str.toCharArray();

        // 65 - 90, 97 - 122
        for (int i = 0; i < c.length; i++) {
            c[i] = rot13(c[i]);
        }

        System.out.println(String.valueOf(c));
    }

    static char rot13(char c){
        int rslt = 0;

        if (c >= 'a' && c <= 'z') {
            int convert = c + 13;

            if (convert - 0 > 122) {
                rslt = convert - 26; // -90 + 64
            } else {
                rslt = convert;
            }

        } else if (c >= 'A' && c <= 'Z') {
            int convert = c + 13;

            if (convert - 0 > 90) {
                rslt = convert - 26; // -122 + 96
            } else {
                rslt = convert;
            }
        } else {
            rslt = c - 0;
        }

        return (char) rslt;
    }
}
