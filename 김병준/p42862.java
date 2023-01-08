/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/42862
 * 프로그래머스 문제풀이 - 체육복
 *
 */


package programmers;

public class p42862 {
    static int c[];

    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        c = new int[n + 1];
        for(int i = 0; i < lost.length; i++){
            c[lost[i]] = -1;
        }
        for(int i = 0; i < reserve.length; i++){
            c[reserve[i]] += 1;
        }
        for(int i = 1; i <= n; i++) {
            if(c[i] != 1 ) continue;
            if(i > 1 && c[i-1] == -1) {
                c[i-1] = 0;
            }
            else if(i + 1 <= n && c[i + 1] == -1){
                c[i + 1] = 0;
            }
            c[i]--;
        }
        int ans = n;
        for(int i = 1; i <= n; i++){
            if(c[i] == -1) ans--;
        }

        return ans;
    }
}
