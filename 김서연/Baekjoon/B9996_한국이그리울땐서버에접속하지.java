package baekjoon.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class B9996_한국이그리울땐서버에접속하지 {
    static String regex;
    static boolean isMatch;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        regex = "^" + br.readLine().replaceAll("\\*", ".*") + "$";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            isMatch =  validateStr(br.readLine(), regex);

            if (isMatch) sb.append("DA\n");
            else sb.append("NE\n");
        }

        System.out.println(sb);
    }

    /**
     * @Desc 정규표현식에 맞는 문자열인지 검증
     */
    static boolean validateStr(String str, String regex) {
//        StringTokenizer st = new StringTokenizer(regex, "*");

        boolean result = Pattern.matches(regex, str);
        return result;
    }
}
