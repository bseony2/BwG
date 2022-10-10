const solution = (n, words) => {
  const array = [words[0]];
  const isFail = (word, prevWord) => {
    return (
      word.length === 1 ||
      prevWord[prevWord.length - 1] !== word[0] ||
      array.includes(word)
    );
  };

  for (let i = 1; i < words.length; i++) {
    const word = words[i];
    if (isFail(word, words[i - 1])) {
      return [(i % n) + 1, Number.parseInt(i / n) + 1];
    }
    array.push(word);
  }

  return [0, 0];
};

// const n = 3;
// const words = [
//   "tank",
//   "kick",
//   "know",
//   "wheel",
//   "land",
//   "dream",
//   "mother",
//   "robot",
//   "tank",
// ];

// console.log(solution(n, words));

// const n = 5;
// const words = [
//   "hello",
//   "observe",
//   "effect",
//   "take",
//   "either",
//   "recognize",
//   "encourage",
//   "ensure",
//   "establish",
//   "hang",
//   "gather",
//   "refer",
//   "reference",
//   "estimate",
//   "executive",
// ];

// console.log(solution(n, words));

const n = 2;
const words = ["hello", "one", "even", "never", "now", "world", "draw"];
console.log(solution(n, words));
