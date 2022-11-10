import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7576_토마토 {
	static int N, M;
	static final int WELL_DONE = 1;
	static final int RAW = 0;
	static final int EMPTY = -1;
	static int max;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static class Point {
		int x, y, dist;

		public Point(int y, int x, int dist) {
			this.y = y;
			this.x = x;
			this.dist = dist;
		}

		@Override
		public String toString() {
			return "Point [y=" + y + ", x=" + x + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);

		int[][] map = new int[M][N];

		for (int i = 0; i < M; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(row[j]);
			}
		}

		Queue<Point> queue = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == WELL_DONE) {
					queue.offer(new Point(i, j, 0));
				}
			}
		}

		while (!queue.isEmpty()) {

			Point p = queue.poll();

//			System.out.println(p);
			int ny, nx;
			int ndist = p.dist + 1;

			max = Math.max(max, p.dist);
			for (int d = 0; d < 4; d++) {
				ny = dy[d] + p.y;
				nx = dx[d] + p.x;

				if (_isGo(ny, nx)) {
					if (map[ny][nx] == RAW) {
						// map에 날짜로 update
						map[ny][nx] = ndist;
						queue.offer(new Point(ny, nx, ndist));
					} else if (map[ny][nx] < ndist) {
						// 이미 update된 map
						continue;
					}
				}

			}
		}
//		_print(map);
		int result = _containsRaw(map) ? -1 : max;
		System.out.println(result);
	}

	private static boolean _containsRaw(int[][] map) {
		boolean isCheck = false;
		for (int i = 0; i < map.length && !isCheck; i++) {
			for (int j = 0; j < map[i].length && !isCheck; j++) {
				if (map[i][j] == RAW) {
					isCheck = true;
				}
			}
		}
		return isCheck;
	}

	private static boolean _isGo(int ny, int nx) {
		return ny < M && nx < N && ny >= 0 && nx >= 0;
	}

	private static void _print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();

	}
}
