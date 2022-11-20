class Solution {
    public String solution(String s) {
        String answer = "";
        int word = s.length();
        
        if(word % 2 == 0) {
            answer = s.substring((word / 2) - 1, (word / 2) + 1);
        } else {
            answer = s.substring((word / 2), (word / 2) + 1);
        }
        return answer;
    }
}
