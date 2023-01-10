import java.util.Scanner;

public class 쉬운계단수 {

    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        long answer = 0;
        int n = Integer.parseInt(sc.next());
        long[][] arr = new long[10][n];

        arr[0][0] = 0L;
        for(int i=1; i<10; i++) {
            arr[i][0] = 1L;
        }

        for(int col = 1; col < n; col++) {
            for(int row = 0; row < 10; row++) {
                switch (row) {
                    case 0:
                        arr[row][col] = (arr[row + 1][col - 1]);
                        break;
                    case 9:
                        arr[row][col] = (arr[row - 1][col - 1]);
                        break;
                    default:
                        arr[row][col] = (arr[row-1][col-1] + arr[row+1][col-1]) % 1000000000;
                        break;
                }
            }
        }

        for(int i=0; i<10; i++) {
            answer += arr[i][n-1];
        }
        System.out.println(answer % 1000000000);
    }
}