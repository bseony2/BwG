import java.util.*;

class Solution {

    public int solution(String[][] clothes) {

        HashMap <String,Integer> hm = new HashMap<>();
        int n = clothes[0].length;        
        for(int i=0; i<clothes.length;i++){
           hm.put(clothes[i][1], hm.getOrDefault(clothes[i][1],0)+1);
            for(int j=0; j<n;j++){
                System.out.print(clothes[i][j] + " ");
        }
        System.out.println();
    }
        
        //얼굴 2 , 안경 3 -> 1 : 5 , 2: 6 = 11
        //모자 2 , 안경 1 -> 1 : 3 , 2 : 2 = 5  
        // System.out.println(clothes[0][0]);
        // System.out.println(clothes[1][0]);
        // System.out.println(clothes[2][0]);
       
        // Set<String> keySet = clothesMap.keySet();
        int answer = 1;
        
        for(String key:hm.keySet()){
            answer *= hm.get(key)+1;
        }
        
        return answer-1;
    }
}