/**
 * @param {number[]} nums
 * @return {number}
 */
const rob = function (nums) {
  const N = nums.length;
  const dp = new Array(nums.length);

  if (N == 1) {
    return nums[N - 1];
  }
  if (N == 2) {
    return Math.max(nums[0], nums[1]);
  }

  dp[0] = nums[0];
  dp[1] = Math.max(nums[0], nums[1]);
  for (let i = 2; i < N; i++) {
    dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
  }
  return dp[N - 1];
};

const nums = [15, 7, 9, 20, 1];
console.log(rob(nums));
