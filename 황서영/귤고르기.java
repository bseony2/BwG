import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class 귤고르기 {
    public static void main(String[] args) {
        int k = 6;
        int[] tangerine = {1, 3, 2, 5, 4, 5, 2, 3};

        System.out.println(solution(k,tangerine));
    }
    static int solution(int k, int[] tangerine) {
        int answer = 0 ;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int t: tangerine) {
            map.put(t, map.getOrDefault(t, 0)+1);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort((o1,o2)-> map.get(o2). compareTo(map.get(o1)));
        for(Integer i :list) {
            if (k <= 0 ) break;
            answer++;
            k -= map.get(i);
        }
        return answer;
    }
}
