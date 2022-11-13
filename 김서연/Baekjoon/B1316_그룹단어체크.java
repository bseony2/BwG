package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 반례 : 중복, aaaa
 * 정신차려..
 *  -> j, j+1 바로 다음 문자 체크 누락
 *  스파케티 정리
 */
public class B1316_그룹단어체크 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int caseNum = Integer.parseInt(br.readLine()); // 테스트케이스
        int chkNum = 0; // 그룹단어 개수
        boolean check = true;

        for (int i = 0; i < caseNum; i++) {
            char[] wordArr = br.readLine().toCharArray();

            for (int j = 0; j < wordArr.length; j++) {
                for (int k = j + 2; k < wordArr.length; k++) {
                    if(wordArr[j] != wordArr[j + 1] && wordArr[j] == wordArr[k]) {
                        check = false;
                    }
                }
                if(check == false) break;
            }

            if(check == true || wordArr.length == 1 || wordArr.length == 2) chkNum++; // char.length == 1 || 2 예외상황 주의
            check = true; // 초기화...?
        }
        System.out.println(chkNum);
    }
}
