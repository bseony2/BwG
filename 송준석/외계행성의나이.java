class Solution {
    public String solution(int age) {
        String answer = "";
        String Alpha = Integer.toString(age);
        String[] divArr = Alpha.split("");
        int [] newArr = new int[divArr.length];
        for(int i = 0; i < divArr.length; i++) {
            newArr[i] = Integer.parseInt(divArr[i]);
            int words = newArr[i] + 97;
            char word = (char) words;
            answer += word;
        }
        return answer;
    }
}
