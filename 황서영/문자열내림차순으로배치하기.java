import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 문자열내림차순으로배치하기 {
    public static void main(String[] args) {
        String s = "Zbcdefg";

        String answer = Stream.of(s.split("")).sorted(Collections.reverseOrder()).collect(Collectors.joining());

        System.out.println(answer);
    }
}