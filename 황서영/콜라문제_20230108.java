public class 콜라문제_20230108 {
    public static void main(String[] args) {
        int a = 2;
        int b = 1;
        int n = 20;

        int answer = 0;

        while (n >= a){
            answer += (n / a) * b;
            int remain = n % a;
            n = (n / a) * b + remain;
        }

        System.out.println(answer);
    }
}
