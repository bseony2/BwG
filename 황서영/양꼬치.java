public class 양꼬치 {
    public int solution(int n, int k) {
        int answer = 0;

        if(n>=10 && k>0){
            k = k - (n / 10);
            if(k < 0) k = 0;
        }

        answer = (n*12000) + (k*2000);

        return answer;
    }
}
