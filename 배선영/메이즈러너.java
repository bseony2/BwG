import java.io.*;
import java.util.*;
import java.lang.Math;

public class 메이즈러너 {
	static int N, M, K;
	static int[][] map, copy;
	static int exitR, exitC, move = 0, turn = 0;
	static int[] dr = new int[] { -1, 1, 0, 0 };
	static int[] dc = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		init();

		simulate();

		System.out.println(move);
		System.out.print(exitR + 1 + " ");
		System.out.print(exitC + 1);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		copy = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			map[r][c] -= 1;
		}

		st = new StringTokenizer(br.readLine(), " ");
		exitR = Integer.parseInt(st.nextToken()) - 1;
		exitC = Integer.parseInt(st.nextToken()) - 1;
		map[exitR][exitC] = 10;
	}

	static void simulate() {
		while (++turn <= K) {
			allMove();

			if (isDone())
				break;

			rotate();
		}
	}

	static void allMove() {
		copyMap(map, copy);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isPerson(i, j)) {
					move(i, j);
				}
			}
		}

		copyMap(copy, map);
	}

	static void move(int r, int c) {
		int dis = Math.abs(exitR - r) + Math.abs(exitC - c);

		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			int nd = Math.abs(exitR - nr) + Math.abs(exitC - nc);

			if (isValidPoint(nr, nc) && !isWall(nr, nc) && nd < dis) {

				if (!isExit(nr, nc)) {
					copy[nr][nc] += map[r][c];
				}

				copy[r][c] -= map[r][c];
				move -= map[r][c];
				break;
			}
		}
	}

	static void copyMap(int[][] origin, int[][] target) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				target[i][j] = origin[i][j];
			}
		}
	}

	static void rotate() {
		int[] info = getRotateInfo();

		rotate(info);

		copyMap(copy, map);
	}

	static int[] getRotateInfo() {
		int r = -1;
		int c = -1;
		int size = 1;

		while (true) {
			for (int i = 0; i <= size; i++) {
				r = exitR - size + i;
				for (int j = 0; j <= size; j++) {
					c = exitC - size + j;
					if (isRotateAble(r, c, size)) {
						return new int[] { r, c, size };
					}
				}
			}

			size += 1;
		}
	}

	static boolean isRotateAble(int r, int c, int size) {
		if (!isValidPoint(r, c) || !isValidPoint(r + size, c + size))
			return false;

		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= size; j++) {
				if (isPerson(r + i, c + j))
					return true;
			}
		}

		return false;
	}

	static void rotate(int[] info) {
		int r = info[0];
		int c = info[1];
		int size = info[2];
		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= size; j++) {
				int value = map[r + i][c + j];
				if (isWall(r + i, c + j)) {
					value -= 1;
				}
				if (isExit(r + i, c + j)) {
					exitR = r + j;
					exitC = c + size - i;
				}

				copy[r + j][c + size - i] = value;
			}
		}
	}

	static boolean isDone() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (isPerson(i, j))
					return false;
			}
		}
		return true;
	}

	static boolean isValidPoint(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	static boolean isWall(int r, int c) {
		return 0 < map[r][c] && map[r][c] < 10;
	}

	static boolean isPerson(int r, int c) {
		return map[r][c] < 0;
	}

	static boolean isExit(int r, int c) {
		return map[r][c] == 10;
	}
}
