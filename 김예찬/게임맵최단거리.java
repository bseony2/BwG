import java.util.*;
class Solution {
    static boolean[][] visited;
    static int[] dx= {1,-1,0,0};
    static int[] dy ={0,0,1,-1};
    public int solution(int[][] maps) {
        
        int answer = 0;

        int N = maps.length;
        int M=0;
        for(int[] i:maps){
            M=0;
            for(int j : i){
                M++;
            }
        }
        
        System.out.print(M);
        
        visited = new boolean[N][M];
        

        Queue<node> queue = new LinkedList<>();
        queue.add(new node(0,0,1));
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            node nd = queue.poll();
            
            if(nd.x==N-1 && nd.y==M-1) answer= nd.cnt;
            
            for(int i=0; i<4; i++){
                 int nx = nd.x+dx[i];
                int ny = nd.y+dy[i];
            
                if(nx<0 || ny<0 || nx>=N || ny>=M) continue;
 
                if(!visited[nx][ny] && maps[nx][ny]==1){
                    queue.add(new node(nx,ny,nd.cnt+1));
                    visited[nx][ny]=true;
                    
                }
            }
        }     
        
        if(answer==0)answer= -1; 
        
        return answer;
    }
    public static class node{
        int x;
        int y;
        int cnt;
        node(int x, int y,int cnt){
            this.x= x;
            this.y= y;
            this.cnt=cnt;
        } 
    }
}