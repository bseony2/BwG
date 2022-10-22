public class 옹알이 {

    public static void main(String[] args) {
        String[] babbling = {"ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"};
        System.out.println(solution(babbling));
    }

    public static int solution(String[] babbling) {
        int answer = 0;

        for(String b : babbling){
            if(b.contains("ayaaya")|| b.contains("yeye") || b.contains("woowoo") || b.contains("mama")){
                continue;
            }

            String s = b.replaceAll("aya", "").replaceAll("ye","")
                    .replaceAll("woo","").replaceAll("ma","");

            if(s.length() == 0){
                answer++;
            }
        }

        return answer;
    }

}
