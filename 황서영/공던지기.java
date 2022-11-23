import java.util.stream.IntStream;

public class 공던지기 {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6};
        int k = 5;

        int answer = IntStream.range(1, k) // {1,2,3,4}
                .mapToObj(i -> numbers) // {1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6,1,2,3,4,5,6}
                .flatMapToInt(IntStream::of) // 일반 스트림 -> IntStream 전환
                .toArray()[2 * k - 2];

        System.out.println(answer);
    }
}
