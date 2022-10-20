class Solution {
    public int[] solution(int[] prices) {
        int time[] = new int[prices.length]; // 시간 체크 배열
        int cnt = 0; // 시간 체크용
        for(int i=0; i<prices.length; i++){
            for(int j=i+1; j<prices.length; j++ ){ // i의 뒤쪽 인덱스를 검사해서 가격이 떨어졌으면 시간 체크 후 break;
                cnt++;
                if (prices[j]<prices[i])
                {
                    break;                        
                }
            }
            time[i] = cnt; 
            cnt = 0;
        }
        return time; 
    }
}