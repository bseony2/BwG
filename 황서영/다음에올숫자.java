public class 다음에올숫자 {

    public static void main(String[] args) {
        int[] common = {2, 4, 8};
        System.out.println(solution(common));
    }

    public static int solution(int[] common) {
        int answer = 0;

        if (common[1] - common[0] == common[2] - common[1]) {
            answer = common[common.length - 1] + (common[common.length - 1] - common[common.length - 2]);
        } else {
            answer = common[common.length - 1] * (common[common.length - 1] / common[common.length - 2]);
        }

        return answer;
    }
}
