import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class 가장큰수 {
    public static void main(String[] args) {
        int[] numbers = {6, 10, 2};

        //스트림 구조 : 객체.스트림생성().가공().결과만들기();
        String answer = IntStream.of(numbers) //스트림 생성
                .mapToObj(String::valueOf) //map 스트림 내 요소들을 하나씩 연산
                .sorted((s1, s2) -> (s2 + s1).compareTo(s1 + s2)) //sorted 요소들 정렬
                .collect(Collectors.joining()); //스트림 작업결과를 하나의 String으로 이어 붙임

        if (answer.startsWith("0")) answer = "0";
        System.out.println(answer);
    }
}
