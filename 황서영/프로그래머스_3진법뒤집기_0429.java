import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 프로그래머스_3진법뒤집기_0429 {
    public static void main(String[] args) {
        int n = 45;
        System.out.println(solution(n));
    }
    public static int solution(int n) {
        int answer = 0;

        List<Integer> list = new ArrayList<>();

        while (n > 0){
            list.add(n%3);
            n /= 3;
        }

        Collections.reverse(list);

        for (int i = 0; i < list.size(); i++){
            answer += list.get(i) * Math.pow(3,i);
        }

        return answer;
    }
}
