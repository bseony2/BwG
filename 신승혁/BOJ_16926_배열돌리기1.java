import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16926_배열돌리기1 {
	static int[][] map;
	static int N, M, R;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] firstLine = br.readLine().split(" ");
		N = Integer.parseInt(firstLine[0]);
		M = Integer.parseInt(firstLine[1]);
		R = Integer.parseInt(firstLine[2]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}
		_rotateEdge(map);
	}

	// 가장자리만 회전 후 반환
	private static void _rotateEdge(int[][] array) {
		int n = array.length;
		int m = array[0].length;
		int[][] visited = new int[n][m];
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(0, 0, array[0][0], 0));
		while (!queue.isEmpty()) {
			Point p = queue.poll();

			int nx = dx[p.d] + p.x;
			int ny = dy[p.d] + p.y;
			int nd = p.d;
			System.out.println(p);
			System.out.println(ny + " " + nx + "");
			System.out.println(visited[ny][nx]);
			if (_isGo(ny, nx, n, m)) {
				if (visited[ny][nx] == 0) { // 처음왔다
					visited[ny][nx] = p.value;
				} else {
					break;
				}
			} else {
				// 한 줄 끝까지가면 방향 바꿔주기
				ny = p.y;
				nx = p.x;
				nd++;

			}
			queue.offer(new Point(ny, nx, map[ny][nx], nd));
		}
		_print(array);
		_print(visited);
	}

	private static boolean _isGo(int ny, int nx, int n, int m) {
		return ny >= 0 && ny < m && nx >= 0 && nx < n;
	}

	private static class Point {
		int y, x, value, d;

		public Point(int y, int x, int value, int d) {
			super();
			this.y = y;
			this.x = x;
			this.value = value;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}

	}

	private static void _print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
