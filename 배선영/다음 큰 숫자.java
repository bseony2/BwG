class Solution {
    public int solution(int n) {
        int answer = n;
        
        while(true){
            answer++;
            if(_countOne(n) == _countOne(answer)) break;
        }
        
        return answer;
    }
    
    private int _countOne(int value) {
        int answer = 0;
        
        while(value != 0){
            answer += (value & 1);
            value = value >>> 1;
        }
        
        return answer;
    }
}