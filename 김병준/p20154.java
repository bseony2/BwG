/**
 *  BOJ: 이 구역의 승자는 누구야?!
 *  https://www.acmicpc.net/problem/20154
 *  
 *  구현
 */


import java.util.LinkedList;
import java.util.Scanner;

public class p20154 {

    static int point[] = {3, 2, 1, 2, 3, 3, 3, 3, 1, 1, 3, 1, 3, 3, 1, 2, 2, 2, 1, 2, 1, 1, 2, 2, 2, 1};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        LinkedList<Integer> step = new LinkedList<>();
        // 초기과정 세팅
        for (int i = 0; i < str.length(); i++) {
            step.add(point[str.charAt(i) - 'A']);
        }

        while (step.size() > 1) {
            LinkedList<Integer> next = new LinkedList<>();
            while (step.size() >= 2) {
                int first = step.pollFirst();
                int second = step.pollFirst();
                int sum = first + second;
                next.add(sum);
            }
            if (step.size() == 1) next.add(step.pollFirst());
            step = next;
        }
        int result = step.get(0);
        if (result % 2 == 0) System.out.println("You're the winner?");
        else System.out.println("I'm a winner!");
    }
}