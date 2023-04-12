import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.*;

class 새샘이의735게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            int[] intArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
            boolean[] isVisited = new boolean[intArr.length];

            dfs(intArr, queue, isVisited, 0, 0, 0);
            List<Integer> result = Stream.generate(queue::poll).limit(queue.size()).distinct().collect(Collectors.toList());

            //List<Integer> result = queue.stream().distinct().collect(Collectors.toList());

            //for(int a : result) System.out.println(a);
            System.out.println("#" + t + " " + result.get(4));
        }
    }

    static void dfs(int[] intArr, PriorityQueue<Integer> queue, boolean[] isVisited, int depth, int start, int result) {
        if(depth == 3) {
            queue.add(result);
            return;
        }

        for(int i=start; i<intArr.length; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                dfs(intArr, queue, isVisited, depth + 1, i+1, result + intArr[i]);
                isVisited[i] = false;
            }
        }
    }
}