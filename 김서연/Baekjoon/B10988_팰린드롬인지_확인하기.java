package baekjoon_automata_basic;

import java.util.Scanner;

public class B10988_팰린드롬인지_확인하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] c = sc.next().toCharArray();

        StringBuffer sb = new StringBuffer(String.valueOf(c));
        char[] r = sb.reverse().toString().toCharArray();

        int result = 0;
        if (isPalin(c, r)) result = 1;

        System.out.println(result);
    }

    static boolean isPalin(char[] c, char[] r) {
        boolean flag = true;
        for (int i = 0; i < c.length; i++) {
            if (c[i] != r[i]) {
                flag = false;
                return flag;
            }
        }

        return flag;
    }
}
