public class 나머지가1이되는수찾기 {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(solution(n));
    }

    public static int solution(int n) {
        int answer = 0;

        for(int i = 2 ; i < n ; i++){
            if(n % i == 1){
                answer = i;
                return answer;
            }
        }
        return answer;
    }
}
