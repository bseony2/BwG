import java.util.*;
class Solution {
    static int height, width;
    static boolean[][][] isVisited;
    static String[] graph;
    
    public LinkedList<Integer> solution(String[] grid) {
        height = grid.length;
        width = grid[0].length();
        graph = grid;
        isVisited = new boolean[height][width][4];
        
        LinkedList<Integer> answer = new LinkedList<Integer>();
        
        for(int i=0; i<height; i++)
        {
            for(int j=0; j<width; j++)
            {
                for(int k = 0; k<4; k++)
                {
                    if(!isVisited[i][j][k])
                        answer.add(check(i, j, k));
                }
            }
        }
        Collections.sort(answer);
        return answer;
    }
    
    static int check(int y, int x, int k) //y좌표, x좌표, 방향
    {
        int[] dy = {-1, 0, 1, 0}; //위, 오른쪽, 아래, 왼쪽
        int[] dx = {0, 1, 0, -1};
        int result = 0;
        while(!isVisited[y][x][k])
        {
            result++;
            isVisited[y][x][k] = true;
            
            //다음 좌표 지정
            y = (y + dy[k] + height) % height;
            x = (x + dx[k] + width) % width;
            
            //다음 좌표의 방향 지정(k)
            //가는 방향 지정 S이면 그냥 같은 방향
            if(graph[y].charAt(x) == 'L')
                k = (k + 3) % 4;
                //k가 0이었으면 3
                //k가 1이었으면 0
                //k가 2었으면 1
                //k가 3이었으면 2
            else if(graph[y].charAt(x) == 'R')
                //k가 0이었으면 1
                //k가 1이었으면 2
                //k가 2었으면 3
                //k가 3이었으면 0
                k = (k + 1) % 4;
        }
        return result;
    }
}