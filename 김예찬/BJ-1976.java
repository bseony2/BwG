package Boj.gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_1976 {
	private static int[] parent;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int N = Integer.parseInt(br.readLine()); // 도시의 수
		int M = Integer.parseInt(br.readLine()); // 여행계획 수
		parent = new int[N + 1]; // [해당노드] = 최상위부모값

		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		int prev = Integer.parseInt(st.nextToken());
		for (int i = 0; i < M - 1; i++) {
			if (!isSameParent(prev, Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
	}

	private static boolean isSameParent(int x, int y) {
	    x = find(x);
        y = find(y);
        return x == y; 
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x != y) {
			parent[y] = x; 
		}
	}
 
	private static int find(int x) {
		if (parent[x] == x)
			return x;
		else
			return parent[x] = find(parent[x]);

	}
}