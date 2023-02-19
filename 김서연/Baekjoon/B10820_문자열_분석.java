package baekjoon_automata_basic;

import java.io.*;

public class B10820_문자열_분석 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str;
        // BufferedReader EOF
        while ((str = br.readLine()) != null) {
            StringBuilder sb = new StringBuilder();
            int[] arr = new int[4];

            for (int i = 0; i < str.length(); i++) {
                // 소문자
                if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                    arr[0] += 1;
                }
                // 대문자
                if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
                    arr[1] += 1;
                }
                // 숫자
                if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
                    arr[2] += 1;
                }
                // 공백
                if (str.charAt(i) == ' ') {
                    arr[3] += 1;
                }
            }
            for(int i : arr) sb.append(i).append(" ");

            System.out.println(sb);
//            System.out.println("\n" + arr[0] + " " + arr[1] + " " + arr[2] + " " + arr[3]);
/*            for (int i : arr) {
                bw.write(i + " ");
            }
            bw.write('\n');
            bw.flush();*/
        }
    }
}
