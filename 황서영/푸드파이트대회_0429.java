public class 푸드파이트대회_0429 {
    public static void main(String[] args) {
        int[] food = {1, 3, 4, 6};
        System.out.println(solution(food));
    }
    public static String solution(int[] food) {

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            int cnt = food[i] / 2;

            for (int j = 0; j < cnt; j++) {
                sb.append(i);
            }
        }

        String answer = sb.toString()+"0"+sb.reverse().toString();
        return answer;
    }
}
