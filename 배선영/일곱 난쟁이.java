import java.util.Scanner;
import java.util.Arrays;
public class 일곱 난쟁이 {
    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int[] intArr = new int[9];
        int summary = 0;
        for(int i=0; i<9; i++) {
            intArr[i] = Integer.parseInt(sc.next());
            summary += intArr[i];
        }
        Arrays.sort(intArr);
        int target = summary - 100;
        int first = 0;
        int second = 0;
        for(int i=0; i<8; i++) {
            for(int j = i + 1; j<9; j++) {
                if(intArr[i] + intArr[j] < target)
                    continue;
                else if( intArr[i] + intArr[j] > target )
                    break;
                else {
                    first = i;
                    second = j;
                }
            }
        }
        for(int i=0; i<9; i++) {
            if(i != first && i != second) {
                System.out.println(intArr[i]);
            }
        }
    }
}
