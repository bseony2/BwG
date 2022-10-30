const solution = (n) => {
  const dp = new Array(n * 2 + 1);
  dp[0] = 0;

  // 약수가 2로만 이루어져있는지?
  const isComposedOf2 = (number) => {
    while (true) {
      if (number === 1) return true;
      if (!Number.isInteger(number)) {
        return false;
      }
      number /= 2;
    }
  };

  let index = 0;
  for (let i = 1; i <= 2 * n; i++) {
    if (isComposedOf2(i)) {
      index = 0;
    }

    dp[i] = dp[index++] + 1;

    if (dp[n] !== 0 && i > n && dp[n] === dp[i]) {
      return i;
    }
  }
};

// const n = 78;
const n = 15;

console.log(solution(n));
