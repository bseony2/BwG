class Solution {
    public int solution(int n) {
        int answer = 1;
        int pre = 1;
        int post = 1;
        
        for(int i= 3; i<n + 1; i++) {
            answer = (pre + post) % 1234567;
            //n에 대한 피보나치 수만 구하면 되기 때문에 n 이전의 수자는 메모리에 할당하지 않음
            pre = post;
            post = answer;
        }
        
        return answer;
    }

}