/**
 * @param {number} num
 * @return {number}
 */
const findComplement = (num) => {
  const binary = num.toString(2);

  let str = "";
  for (let i = 0; i < binary.length; i++) {
    if (binary[i] === "0") str += "1";
    else str += "0";
  }
  const answer = parseInt(str, 2);
  return answer;
};

const answer = findComplement(5);
console.log(answer);
