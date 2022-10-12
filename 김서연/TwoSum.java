package baekjoon_automata_basic;

import java.util.Scanner;
/*
   **** LeetCode 제출 ****
        class Solution {
        public int[] twoSum(int[] nums, int target) {

            int[] rlt = new int[2];

            for(int i = 0; i < nums.length; i++) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        rlt[0] = i; rlt[1] = j;
                    }
                }
            }
            return rlt;
        }
    }
 */

// 내맴 풀이
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {

        int[] rlt = new int[2];

        for(int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    rlt[0] = i; rlt[1] = j;
                }
            }
        }
        return rlt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[sc.nextInt()];
        int cnt = 0;

        while(cnt < arr.length) {
            arr[cnt] = sc.nextInt();
            cnt++;
        }

        System.out.print("target 입력 : ");
        int t = sc.nextInt();

        int[] arrRlt = twoSum(arr, t);

        for (int i = 0; i < arrRlt.length; i++) {
            System.out.print(arrRlt[i]);
        }
    }
}
