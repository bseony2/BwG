import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 백준 - 로또 (6603)
 * https://www.acmicpc.net/problem/6603
 * DFS - 순열
 */

public class p6603 {

    static int n;
    static int[] nums;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while (true) {

            String[] input = br.readLine().split(" ");
            n = Integer.parseInt(input[0]);
            if (n == 0) break;

            nums = new int[n];
            for (int i = 1; i <= n; i++) {
                nums[i-1] = Integer.parseInt(input[i]);
            }
            Deque<Integer> list = new LinkedList<>();q

            dfs(0, list);
            System.out.println();
        }
    }

    private static void dfs(int idx, Deque<Integer> list) {
        int size = list.size();
        if(size == 6) {
            for (Integer elem : list) {
                System.out.print(elem + " ");
            }
            System.out.println();
            return;
        }
        for(int i = idx; i < n; i++) {
            list.add(nums[i]);
            dfs(i + 1, list);
            list.removeLast();
        }
    }
}
