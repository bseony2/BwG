class Solution {
    public int solution(int[] numbers, int k) {
        // {1,2,3,4,5}, k=3
        int answer = 0;
        answer = numbers[(2*(k-1))% numbers.length ];
        return answer;
    }
}
