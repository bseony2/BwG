class Solution {
    public int solution(int num) {
        int answer = 0;
        
        while(num!= 1) {
            answer++;
            
            if(num % 2 == 0) {
                num = num / 2;
            } else if (num % 2 == 1){
                num = num * 3 + 1;
            }
            
            if(answer==500) {
                answer = -1;
                break;
            }            
        }
        return answer;
    }
}
