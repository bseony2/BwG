package w1;

/**
 *  틀린풀이 XX !!
 *  BOJ  - 2659번 (십자카드 문제)
 *  https://www.acmicpc.net/problem/2659
 *  구현
 */

import java.util.Scanner;

public class p2659 {

    static int numbers[] = new int[4];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for(int i = 0; i < 4; i++) {
            numbers[i] = sc.nextInt();
        }

        // 주어진 입력에 대한 시계수 계산
        int numberOfWatches = calcNumberOfWatches();
        System.out.println("numberOfWatches = " + numberOfWatches);
        System.out.println(dfs(0, 0, numberOfWatches));
    }

    // 주어진 입력에 대한 시계수 계산
    private static int calcNumberOfWatches() {
        int numberOfWatches = 10000;
        for(int i = 0; i < 4; i++) {
            int num = 0;
            for(int j = 0; j < 4; j++){
                num  *= 10;
                num  += numbers[(i + j)%4];
            }
            numberOfWatches = Math.min(numberOfWatches, num);
        }
        return numberOfWatches;
    }

    // target 보다 작은 시계수룰 모두 count
    private static int dfs(int number, int depth, int target) {
        if(depth == 4 && number <= target){
            return 1;
        }
        if(depth >= 4) return 0;
        int ans = 0;
        for(int i = 1; i <= 9; i++){
            if(number%10 > i) continue;
            ans += dfs(number * 10 + i, depth + 1, target);
        }
        return ans;
    }
}