class Solution {
    public int solution(int n) {
        int answer = 0;
        
        for(int i = 0; i < n; i++) {
            answer++;
            while(Integer.toString(answer).contains("3") || answer % 3 == 0) {
                answer++;
            }
        }
        return answer;
    }
}
