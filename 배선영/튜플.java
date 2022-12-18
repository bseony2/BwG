import java.util.*;

class 튜플 {

    public static void main(String[] args) {
        튜플 t = new 튜플();
        t.solution("{{2},{2,1},{2,1,3},{2,1,3,4}}");
    }

    public int[] solution(String s) {
        int[] answer = {};
        
        s = s.replace("{{", "").replace("}}", "");
        String[] str = s.split("\\}\\,\\{");
        
        Arrays.sort(str, new Comparator<String>(){
        
            public int compare(String s1, String s2)
            {
                if(s1.length() < s2.length())
                    return -1;
                else
                    return 1;
            }
        });
        
        for(String s1 : str)
        {
            System.out.println(s1);
        }
        
        return answer;
    }
}