import java.time.LocalDate;

public class 문제_2016년_20230108 {
    public static void main(String[] args) {
        int a = 5;
        int b = 24;

        String answer = LocalDate.of(2016, a, b).getDayOfWeek().toString().substring(0,3);

        System.out.println(answer);
    }
}
