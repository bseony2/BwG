package Boj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_2667_ver2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int n;
	static int map[][];
	static boolean visited[][];
	static Queue<point> queue = new LinkedList<>();
	static int apt_num;
	static ArrayList al = new ArrayList();

	public static void main(String[] args) throws NumberFormatException, IOException {
		n = Integer.parseInt(br.readLine());
		map = new int[n + 1][n + 1];
		visited = new boolean[n + 1][n + 1];
		for (int r = 0; r < n; r++) {
			String temp = br.readLine();
			for (int c = 0; c < n; c++) {
				map[r][c] = temp.charAt(c) - '0';
			}
		}
		
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				if (map[r][c] == 1 && visited[r][c] == false) {
					bfs(r, c);
					apt_num++;
				}
			}
		}
		System.out.println(apt_num);
		Collections.sort(al);
		for (int i = 0; i < al.size(); i++)
			System.out.println(al.get(i));
 
	}

	static void bfs(int r, int c) {
		visited[r][c] = true;
		queue.add(new point(r, c));
		int local_apt = 1;
		int[] dx = { 1, -1, 0, 0 };
		int[] dy = { 0, 0, 1, -1 };
		while (!queue.isEmpty()) {
			point p = queue.poll();

			for (int i = 0; i < 4; i++) {
				int cx = p.x + dx[i];
				int cy = p.y + dy[i];

				if (cx >= 0 && cx < n && cy >= 0 && cy < n) {
					if (visited[cx][cy] == false && map[cx][cy] == 1) {
						queue.add(new point(cx, cy));
						visited[cx][cy] = true;
						local_apt++;
					}
				}
			}
		}
		al.add(local_apt);
	}

	static class point {
		int x;
		int y;

		public point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
