import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;

public class 반복수열 {
    static int A, P;
    static ArrayList<Integer> list;
    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);

        A = sc.nextInt();
        P = sc.nextInt();
        list = new ArrayList<Integer>();
        list.add(A);

        while(true) {
            int nextValue = getNextInt(list.get(list.size()-1));
            if(list.contains(nextValue)) {
                int index = list.indexOf(nextValue);
                System.out.println(index);
                break;
            } else {
                list.add(nextValue);
            }
        }
    }

    static int getNextInt(int value) {
        int result = 0;
        for(char c : String.valueOf(value).toCharArray()) {
            result += Math.pow(c-'0', P);
        }
        return result;
    }
}
