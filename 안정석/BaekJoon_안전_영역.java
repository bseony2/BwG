package day1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_안전_영역 {

	static int N, cnt, maxCnt, maxHeight; // 맵의 크기를 저장할 int, 안전 구역 개수를 저장할 int, 최대 안전 구역 개수를 저장할 int, 최대 높이를 저장 할 int
	static int map[][];
	static boolean check[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		check = new boolean[N][N];
		cnt = 0;
		maxCnt = 0;
		maxHeight = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > maxHeight) {
					maxHeight = map[i][j];
				}
			}
		}

		for (int i = 0; i <= maxHeight; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (map[j][k] <= i) {
						check[j][k] = true;
					}
				}
			}
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					if (check[j][k] == false) {
						bfs(j, k);
						cnt++;
					}
				}
			}
			maxCnt = Math.max(cnt, maxCnt);
			cnt = 0;
			check = new boolean[N][N];
		}

		System.out.println(maxCnt);

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs(int i, int j) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(i, j));
		check[i][j] = true;
		while (!q.isEmpty()) {
			Point now = q.poll();
			for (int d = 0; d < 4; d++) {
				int nx = now.x + dx[d];
				int ny = now.y + dy[d];

				if (nx >= 0 && nx < N && ny >= 0 && ny < N && check[nx][ny] == false) {
					q.add(new Point(nx, ny));
					check[nx][ny] = true;
				}
			}
		}
	}

}
