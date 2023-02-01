/**
 *  BOJ - 부당한 퍼즐 ( 15501번)
 *  https://www.acmicpc.net/problem/15501
 *
 *  애드 혹
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class p15501 {

    private static int N;
    private static int[] source;
    private static int[] target;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inputValueSetting(br);


        if(solution()){
            System.out.println("good puzzle");
        } else{
            System.out.println("bad puzzle");
        }
    }

    private static void inputValueSetting(BufferedReader br) throws IOException {
        N = Integer.parseInt(br.readLine());

        source = new int[N];
        target = new int[N];

        String[] line = br.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            source[i] = Integer.parseInt(line[i]);
        }

        line = br.readLine().split(" ");

        for(int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(line[i]);
        }
    }

    private static boolean solution() {

        boolean isOk = true;

        // Source의 0번째 원소 숫자와 와 target에 숫자를 찾는다.
        int idx = -1, targetNum = source[0];
        idx = findIndexMatchingNumber(targetNum);

        if(idx == -1) return false;

        isOk = checkIsSameSequence(idx);
        if(isOk) return true;

        isOk = true;

        // target을 뒤집는다. 위의 과정을 반복한다
        flipArray();

        idx = findIndexMatchingNumber(targetNum);
        if(idx == -1) return false;

        isOk = checkIsSameSequence(idx);
        return isOk;
    }

    private static boolean checkIsSameSequence(int idx) {
        for(int i = 0; i < N; i++) {
            if(target[(i + idx) % N] != source[i]){
                return false;
            }
        }
        return true;
    }

    private static void flipArray() {
        for(int i = 0; i < N / 2; i++){
            int left = target[i];
            int right = target[N - 1 - i];

            target[N - 1 - i]  = left;
            target[i] = right;
        }
    }

    private static int findIndexMatchingNumber(int targetNum){
        int idx = -1;
        for(int i = 0; i < N; i++) {
            if(targetNum == target[i]) {
                idx = i;
                break;
            }
        }
        return idx;
    }
}