class Solution {
    public int solution(int[] array) {
        int answer = 0;
        
        for(int i =0; i <array.length; i++) {
            String oneNum = Integer.toString(array[i]);
            String[] arrNum = oneNum.split("");
            
            for(int j=0; j <arrNum.length; j++) {
                if(arrNum[j].contains("7")) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
