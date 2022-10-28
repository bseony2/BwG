package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_1260 {
	static int N, M, V;
	static int[][] map;
	static boolean[] visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r][c] = 1;
			map[c][r] = 1;
		}
		
		dfs(V);
		Arrays.fill(visited, false);
		System.out.println();
		bfs(V);
	}

	

	public static void dfs(int V) {
		
		visited[V] = true;
		System.out.print(V+" ");
		for(int i=1;i<=N;i++) {
			if(map[V][i]==1&&visited[i]==false) {
				visited[i]=true;
				dfs(i);
			}
		}
	}
	
	public static void bfs(int V) {
		
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(V);
		visited[V]= true;
		
		while(!queue.isEmpty()) {
			int x = queue.poll();
			System.out.print(x+" ");
			for(int i=1;i<=N;i++) {
				if(map[x][i]==1&&visited[i]==false) {
					visited[i]=true;
					queue.add(i);
				}
			}
		}
		
	}
}