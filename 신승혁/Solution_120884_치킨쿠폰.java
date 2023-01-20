public class Solution_120884_치킨쿠폰 {
    public int solution(int chicken) {

        int couponCount = 0;

        while (true) {
            if (chicken < 10) {
                break;
            }
            int divided = chicken / 10;
            int rest = chicken % 10;
            chicken = divided + rest;

            couponCount += divided;
        }


        return couponCount;
    }

}
