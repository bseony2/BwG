class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        // a = 2, b = 1, n = 20
        while(true) {
        int cal = (n / a) * b;        
            if(n < a) {
                break;
            } else {
                answer += cal;
                int left = n % a;
                n = cal + left;
            }   
        }
        return answer;
    }
}
