public class 가까운수 {

    public static void main(String[] args) {

        int[] array = {3, 10, 28};
        int n = 20;

        int answer = 101;
        int diff = 101;
        for (int num : array) {
            int min = Math.abs(n - num);
            if (min < diff) {
                diff = min;
                answer = num;
            } else if (min == diff) {
                if (num < answer) {
                    answer = num;
                }
            }
        }

        System.out.println(answer);
    }
}
