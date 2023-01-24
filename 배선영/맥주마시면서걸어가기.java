import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.List;
import java.util.LinkedList;

public class 맥주마시면서걸어가기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Queue<Node> queue;
    static List<Node> location;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            System.out.println(bfs(Integer.parseInt(br.readLine())));
        }
    }
    static String bfs(int n) throws IOException {
        location = new ArrayList<Node>();
        queue = new LinkedList<Node>();
        boolean[] isVisited = new boolean[n + 2];
        for(int i=0; i<n+2; i++) {
            st = new StringTokenizer(br.readLine());
            location.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        queue.add(location.get(0));
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            for(int i=1; i<n+2; i++) {
                if(!isVisited[i]) {
                    Node tempNode = location.get(i);
                    if(Math.abs(currentNode.x - tempNode.x) + Math.abs(currentNode.y - tempNode.y) <= 1000) {
                        queue.add(tempNode);
                        isVisited[i] = true;

                        if(isVisited[n+1]) return "happy";
                    }
                }
            }
        }

        return "sad";
    }
}


class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}