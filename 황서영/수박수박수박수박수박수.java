public class 수박수박수박수박수박수 {
    public static void main(String[] args) {
        int n = 4;
        String answer = "";

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                answer = answer.concat("수");
            } else {
                answer = answer.concat("박");
            }
        }
        System.out.println(answer);
    }
}
