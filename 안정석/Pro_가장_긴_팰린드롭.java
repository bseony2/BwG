class Solution
     
{
    public int solution(String s)
    {
        int cnt;
        int maxCnt = 0;
        int oddCnt = 0;
        int evenCnt = 0;
        char arr[] = s.toCharArray();
        for(int i=0; i<arr.length; i++){
            cnt = 0;
            int k=1;
            while(true){
                if(i-k >= 0 && i+k < arr.length && arr[i-k] == arr[i+k]){
                    cnt++;
                    k++;
                }
                else{
                    break;
                }
               
            }
            cnt = 2*cnt+1;
            oddCnt = Math.max(cnt, oddCnt);
        }
        for(int i=0; i<arr.length; i++){
            cnt = 0;
            int k=1;
            while(true){
                if(i-k+1 >= 0 && i+k < arr.length && arr[i-k+1] == arr[i+k]){
                    cnt++;
                    k++;
                }
                else{
                    break;
                }
               
            }
            cnt = 2*cnt;
            evenCnt = Math.max(cnt, evenCnt);
        }
        
        maxCnt = Math.max(oddCnt, evenCnt);
        if(maxCnt > 0){
        return maxCnt;
        }
        else{
            return 1;
        }
    }
}

