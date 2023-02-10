package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_2468 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,-1,1};
	public static void main(String[] args) throws IOException {

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		int max =0;
		for(int i=0; i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j]>max) max= map[i][j];
			}
		}
		
		int answer=0;
		for(int T=1; T<=max;T++) {
			int temp = 0;
			visited = new boolean[n][n];
			for(int i=0; i<n;i++) {
				for(int j=0; j<n; j++) {
					
					if(!visited[i][j] && map[i][j]>T) {
						dfs(i,j,T);
						temp++;
					}
				}
			}
			
			if(answer<temp) answer = temp;
		}
		if(answer==0) System.out.println(1);
		else System.out.println(answer);
	}

	private static void dfs(int x, int y, int height) {
		
		visited[x][y] = true; 
		
		for(int i=0; i<4;i++) {
			int cx = x+dx[i];
			int cy = y+dy[i];
			
			if(cx<0 || cy<0 || cx>=n || cy>=n) continue;
				
			if(!visited[cx][cy] && map[cx][cy]>height)
				dfs(cx,cy,height);
		}
		
		
	}
}
