package leetcode;

import java.util.Arrays;

public class L217_Contains_Duplicate {
    public static void main(String[] args) {

    }
    public boolean containsDuplicate(int[] nums) {
        /**
         *  O(N^2) - timeout(Time Limit Exceeded)
         */
//        boolean result = false;
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] == nums[j]) result = true;
//            }
//        }
//        return result;

        Arrays.sort(nums); // 정렬 후 비교
        int compare = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (compare == nums[i]) return true;

            compare = nums[i];
        }

        return false;
    }
}
