package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class B1159_농구_경기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] c = new int[26];
        boolean isSurrender = false;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            char player = br.readLine().charAt(0); // 성의 첫 글자
            c[player - 97]++;

            if (c[player - 97] >= 5) {
                isSurrender = true;
                sb.append(player);
            }
        }

        if (isSurrender) System.out.println(removeDuplicates(sb.toString()));
        else System.out.println("PREDAJA");
    }

    static String removeDuplicates(String str) {
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();

        // 사전순 정렬
        char[] charArr = str.toCharArray();
        Arrays.sort(charArr);
        
        // 중복제거
        for (char c : charArr) {
            if (set.add(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
