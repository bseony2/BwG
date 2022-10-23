function solution(babbling) {
  const ableWords = ["aya", "ye", "woo", "ma"];
  const notAbleWords = ["ayaaya", "yeye", "woowoo", "mama"];
  let count = 0;
  const ableSpeaking = (word) => {
    for (const notAbleWord of notAbleWords) {
      if (word.indexOf(notAbleWord) !== -1) {
        return false;
      }
    }

    for (const ableWord of ableWords) {
      const a = word.split(ableWord);
      word = a.join("");
    }
    return word === "";
  };

  babbling.map((v) => (ableSpeaking(v) ? count++ : ""));

  return count;
}

// const babbling = ["aya", "yee", "u", "maa"];
const babbling = ["ayaye", "uuu", "yeye", "yemawoo", "ayaayaa"];
const answer = solution(babbling);
console.log(answer);
