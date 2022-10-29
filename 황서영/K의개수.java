public class K의개수 {

    public static void main(String[] args) {
        int i = 10;
        int j = 50;
        int k = 5;

        System.out.println(solution(i, j, k));
    }

    public static int solution(int i, int j, int k) {
        int answer = 0;

        for (int n = i; n <= j; n++) {
            int tmp = n;
            while (tmp != 0) {
                if (tmp % 10 == k) {
                    answer++;
                }
                tmp /= 10;
            }
        }
        return answer;
    }
}
