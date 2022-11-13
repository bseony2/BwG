package programmers;

public class P_저주의숫자3 {
    public static void main(String[] args) {

    }
    public int solution(int n) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            answer++; // 우선순위
            while (String.valueOf(answer).contains("3") || answer % 3 == 0) answer++;
        }
        return answer;
    }
}
