import java.util.Scanner;

public class 날짜계산 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int E = scan.nextInt();
        int S = scan.nextInt();
        int M = scan.nextInt();

        int year = 0;
        boolean loof = true;

        while(loof)
        {
            year++;
            if(((year - E) % 15 ==0) && ((year - S) % 28 ==0) && ((year - M) % 19 ==0))
            {
                loof = false;
            }
        }

        scan.close();
        
        System.out.println(year);
    }
}
