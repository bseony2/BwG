package baekjoon.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1620_나는야포켓몬마스터이다솜 {
    static BufferedReader br;
    static int n, m;
    static String[] problems;
    static String[] idxToStr;
    static HashMap<String, Integer> strToIdx = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();

        process(problems);
    }

    static void process(String[] problems) throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < m + 1; i++) {
            char[] c = problems[i].toCharArray();
            boolean isDigit = isDigit(c);

            if (isDigit) {
                sb.append(idxToStr[Integer.parseInt(problems[i])]).append('\n');
            } else {
                sb.append(strToIdx.get(problems[i])).append("\n");
            }
        }
        System.out.println(sb);
    }

    static boolean isDigit(char[] c) {
//        Character.isDigit();
        if (c[0] - 0 >= 48 && c[0] - 0  <= 57) {
            return true;
        }
        else return false;
    }
    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        idxToStr = new String[n + 1];
        problems = new String[m + 1];

        for (int i = 1; i <= n; i++) {
            String input = br.readLine();
            idxToStr[i] = input;
            strToIdx.put(input, i);
        }

        for (int i = 1; i < m + 1; i++) {
            problems[i] = br.readLine();
        }
    }
}
