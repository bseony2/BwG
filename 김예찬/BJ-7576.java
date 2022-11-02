package Boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_7576_new {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int N, M;
	static int arr[][];
	static Queue<DO> queue = new LinkedList<>();
	static int dx[] = { 1, -1, 0, 0 };
	static int dy[] = { 0, 0, 1, -1 };

	public static void main(String args[]) throws Exception {

		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					queue.add(new DO(i, j));
			}
		}
		bfs();

//		for (int r = 0; r < N; r++) {
//			System.out.println();
//			for (int c = 0; c < M; c++) {
//				System.out.print(arr[r][c] + " ");
//			}
//		}
		int answer=0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (arr[r][c] == 0) {
					System.out.println("-1");
					return;
				}
				answer = Math.max(answer, arr[r][c]);
			}
		}

		System.out.println(answer-1);
	}

	private static void bfs() {

		while (!queue.isEmpty()) {
			DO d = queue.poll();
			for (int i = 0; i < 4; i++) {
				int cx = d.x + dx[i];
				int cy = d.y + dy[i];
				int answer = arr[d.x][d.y];
				if (cx >= 0 && cx < N && cy >= 0 && cy < M) {
					if (arr[cx][cy] == 0) {
						queue.add(new DO(cx, cy));
						arr[cx][cy] = answer + 1;
					}
				}
			}
		}

	}
}  

class DO {
	int x;
	int y;

	DO(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
