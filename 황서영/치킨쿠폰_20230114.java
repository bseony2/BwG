public class 치킨쿠폰_20230114 {
    public static void main(String[] args) {
        int chicken = 1081;
        int answer = 0;

        while(chicken >= 10){
            answer += chicken / 10;
            int coupon = chicken % 10;
            chicken = (chicken / 10) + coupon;
        }

        System.out.println(answer);
    }
}
