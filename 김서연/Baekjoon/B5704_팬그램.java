package baekjoon_automata_basic;

import java.io.*;

public class B5704_팬그램 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String str = "";

        StringBuilder sb = new StringBuilder();
        while (true) {
            int chkAlphabet = 0;
            str = br.readLine().replace(" ", "");

//            String strSpace = rmSpace(str);

            if (str.equals("*")) break;

            isPangram(str, chkAlphabet, sb);
        }

        bw.write(sb.toString());
        bw.flush();
    }
    static StringBuilder isPangram(String pangramWord, int checkNum, StringBuilder sb) {

        int[] arr = new int['Z'-'A'+1]; // 26

        for (int i = 0; i < pangramWord.length(); i++) {
            char idx = pangramWord.charAt(i);

            // 한 번씩 사용됐을떄만 count
            if (++arr[idx - 'a'] == 1) checkNum++;
        }

        sb.append(checkNum == 26? "Y" : "N").append("\n");

        return sb;
    }

    static String rmSpace(String word) {
        word.replace(" ", "");
        System.out.println(word);
        return word;
    }
}
