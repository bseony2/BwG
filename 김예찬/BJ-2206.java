package Boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj_2206 {
    static int map[][];  
    static int visited[][][]; 
    static int min_dis;
    public static class Node {
        int x;
        int y;
        int use;
        int dis;
        public Node(int x, int y, int use, int dis){
            this.x = x;
            this.y = y;
            this.use = use;
            this.dis = dis;

        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] str = br.readLine().split(" ");
        n = Integer.parseInt(str[0]);
        m = Integer.parseInt(str[1]);

        map = new int[n][m];
        visited = new int[n][m][2];
        for(int i=0;i<n;i++){
            String data = br.readLine();
            for(int j=0;j<m;j++){
                map[i][j] = data.charAt(j) -'0';
            }
        }
        min_dis = Integer.MAX_VALUE;
        bfs();
        if(min_dis == Integer.MAX_VALUE){
            System.out.println("-1");
        } else{
            System.out.println(min_dis);
        }



    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        q.add(new Node(0, 0, 0, 1));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.x == n - 1 && node.y == m - 1) {
                min_dis = Math.min(min_dis, node.dis);
            }
            for (int k = 0; k < 4; k++) {
                int nx = node.x + dx[k];
                int ny = node.y + dy[k];
                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                  if(visited[nx][ny][node.use] != 0){
                      continue;
                  } 

                  if (map[nx][ny] == 0){
                      visited[nx][ny][node.use] = 1;
                      q.add(new Node(nx, ny, node.use, node.dis+1));
                  } else if(map[nx][ny] == 1 && node.use == 0){
                      visited[nx][ny][1] = 1;
                      q.add(new Node(nx, ny, 1, node.dis+1));
                  }
                }
            }
        }
    }
}