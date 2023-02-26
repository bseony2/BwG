package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11008_복붙의_달인 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        int tCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < tCase; i++) {
            // default token : space 잖아...?
            StringTokenizer st = new StringTokenizer(br.readLine());

            String str = st.nextToken();;
            String phrase = st.nextToken();;

            String s = str.replaceAll(phrase, ",");

            sb.append(s.length()).append('\n');
        }

        System.out.println(sb);
    }
}
