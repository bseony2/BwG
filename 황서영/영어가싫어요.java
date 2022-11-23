import java.util.Arrays;

public class 영어가싫어요 {

    public static void main(String[] args) {

        String numbers = "onetwothreefourfivesixseveneightnine";
        long answer = 0;

        String[] num = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (String n : num) {
            numbers = numbers.replace(n, Integer.toString(Arrays.asList(num).indexOf(n)));
        }
        answer = Long.parseLong(numbers);

        System.out.println(answer);
    }
}
