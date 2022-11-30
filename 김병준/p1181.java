package w3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class p1181 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        sc.nextLine();
        ArrayList<String> arr = new ArrayList<>();
        while(N-- > 0) {
            String s = sc.nextLine();
            if(!arr.contains(s)) arr.add(s);
        }
        Collections.sort(arr, (o1, o2) -> {
            if (o1.length() == o2.length()) {
                return o1.compareTo(o2);
            } else return o1.length() - o2.length();
        });
        for(String s : arr){
            System.out.println(s);
        }
    }
}