package baekjoon_automata_basic;

import java.io.*;

public class B1748_수이어쓰기1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int nLength = (int)(Math.log10(N) + 1); // 숫자 자릿수 구하기
        int len1 = nLength - 1;
        int len2 = nLength - 2;
        StringBuilder sb1= new StringBuilder("1");
        StringBuilder sb9 = new StringBuilder("9");
        int result = 0;

        while (len1 > 0) {
            sb1.append("0");

            len1 -= 1;
        }

        result += ((N - Integer.parseInt(String.valueOf(sb1))) + 1) * nLength;

        while (len2 > 0) {
            sb9.append("0");

            len2 -= 1;
        }

        while(sb9.length() > 0) {
            nLength -= 1;
            result += Integer.parseInt(String.valueOf(sb9)) * nLength;

            sb9.delete(sb9.length() - 1, sb9.length());
        }
        System.out.println(result);
    }
}

