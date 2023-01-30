package Boj.gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj_1717 {
	static int[] parent;
	static int n, m;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {

		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		parent = new int[n + 1];
		m = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= n; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int type =Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if (type== 0) {

				union(x, y);
			} else {
				if (answer(x,y) == true) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}

	}

	private static boolean answer(int x, int y) {
		x= find(x);
		y= find(y);
		if(x==y) return true;
		else return false;
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x!=y) {
			if(x<y)parent[y]=x;
			else parent[x]=y;
		}
	}

	private static int find(int x) {

		if (x == parent[x])
			return x;
		else { 
			return parent[x] = find(parent[x]);
		}
	}
}