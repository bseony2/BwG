package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B1032_명령프롬프트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<char[]> all = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String fileName = br.readLine();

            all.add(fileName.toCharArray());
        }

        char[] comparison = all.get(0); // 대조군 & Result

        for (char[] one : all) { // 리스트에서 하나씩 뺴와서 대조군과 비교
            for (int i = 0; i < comparison.length; i++) {
                if ( one[i] == comparison[i]) {
                    continue;
                } else {
                    comparison[i] = '?';
                }
            }
        }

//        System.out.println(comparison.toString()); // 정신차려
        System.out.println(String.valueOf(comparison));
    }
}
