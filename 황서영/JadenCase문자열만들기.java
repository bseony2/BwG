public class JadenCase문자열만들기 {

    public static void main(String[] args) {
        String s ="3people unFollowed me";
        System.out.println(solution(s));
    }

    static String solution(String s) {
        String answer = "";

        s = s.toLowerCase();
        answer += Character.toString(s.charAt(0)).toUpperCase();

        for(int i =1; i< s.length(); i++){
            answer += s.charAt(i-1)==' ' ? Character.toString(s.charAt(i)).toUpperCase() : Character.toString(s.charAt(i));
        }

        return answer;
    }
}
