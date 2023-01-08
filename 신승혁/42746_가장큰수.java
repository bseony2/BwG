import java.util.*;

class Solution{
    public String solution(int[] numbers){
        String answer = "";
    
        List<String> list = new ArrayList<>();
        for(int number : numbers){
            list.add(String.valueOf(number));
        }
        Collections.sort(list, new SC());
        
        StringBuilder sb = new StringBuilder();
        for(String s : list){
            sb.append(s);
        }
        
        answer = sb.toString();
        if(_isZero(answer)) return "0";
        return answer;
    }
    
    boolean _isZero(String s){
        boolean result = true;
        for(int i = 0; i< s.length(); i++){
            if(s.charAt(i) != '0'){
                return false;
            }
        }
        return result;
    }
    
    class SC implements Comparator<String> {
        @Override
        public int compare(String s1, String s2){
            String a = s1 + s2;
            String b = s2 + s1;
            int result = a.compareTo(b);
            
            if(result > 0){
                return -1;
            }else if (result == 0){
                return 0;
            }else{
                return 1;
            }
        }
        
    }
        
}
