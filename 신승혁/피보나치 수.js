const solution = (n) => {
  const dp = new Array(n + 1).fill(0);
  dp[1] = 1;

  for (let i = 2; i <= n; i++) {
    dp[i] = ((dp[i - 2] % 1234567) + (dp[i - 1] % 1234567)) % 1234567;
  }

  return dp[n];
};

const n = 5;
console.log(solution(5));
console.log(solution(100));
console.log(solution(1000));
console.log(solution(10000));
console.log(solution(100000));
