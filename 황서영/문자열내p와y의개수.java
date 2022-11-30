public class 문자열내p와y의개수 {

    public static void main(String[] args) {
        String s = "pPoooyY";
        boolean answer = true;

        int p = 0;
        int y = 0;
        s = s.toLowerCase();
        for (int i = 0; i <= s.length() - 1; i++) {
            if (s.charAt(i) == 'p') {
                p++;
            } else if (s.charAt(i) == 'y') {
                y++;
            }
        }
        if (p != y) {
            answer = false;
        }
        System.out.println(answer);
    }
}
