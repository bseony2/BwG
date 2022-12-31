class Solution {
    static int[][] memo;
    public int solution(int[][] triangle) {
        int answer = 0;
        memo = new int[triangle.length][triangle.length];
        
        memo[0][0]= triangle[0][0];
        
        for(int i=1; i<triangle.length;i++){
            
            memo[i][0] += triangle[i][0]+memo[i-1][0];
        
            for(int j=1; j<triangle[i].length;j++){
                memo[i][j] = Math.max(memo[i-1][j-1],memo[i-1][j])+triangle[i][j];
            }       
        
            memo[i][i] =memo[i-1][i-1]+triangle[i][i];
        }   
        
        for (int i = 0; i < triangle.length; i++) {
            answer = Math.max(answer, memo[triangle.length - 1][i]);
        }
        
        
        return answer;
    }
     
}