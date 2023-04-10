import java.util.Scanner;
import java.lang.Math;

public class 구구단걷기 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();
        for(int tc = 1; tc<=TC; tc++) {
            long num = scanner.nextLong();
            
            long distance = num - 1;

            for(long i=2; i<=Math.sqrt(num); i++) {
                if(num % i == 0) {
                    if((i + (num/i) - 2) < distance)
                        distance = (num/i) + i - 2;
                }
            }

            System.out.println("#"+tc + " " + distance);
        }
        scanner.close();
    }
}
