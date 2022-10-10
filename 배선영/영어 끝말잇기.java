import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int n, String[] words) {
        int turn = 0;

        List<String> wordList = new ArrayList<String>(n);
        wordList.add(words[0]);
        for(int i=1; i<words.length; i++) {
            String preWord = words[i - 1];
            String currentWord = words[i];
            if(  words[i - 1].charAt(preWord.length() - 1) != currentWord.charAt(0) // 이전 단어에 정상적인 끝말잇기가 아닌 경우
                || wordList.contains(currentWord) ) { // 이미 말한 단어를 말한 경우
                turn = i;
                break;
            }
            wordList.add(currentWord);
        }
        return turn == 0 ? new int[] {0, 0} : new int[] {(turn % n) + 1, turn/n + 1};

    }
}
