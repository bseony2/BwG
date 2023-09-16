import java.io.*;
import java.util.*;

public class 전투로봇 {
	static int robbotPower = 2, killing = 0, robbotR, robbotC;
	static int[][] map, distance;
	static boolean[][] isVisited;
	static int time = 0, N;
	static List<Monster> monsterList = new ArrayList<>();
	static int[] dr = new int[] { -1, 0, 1, 0 };
	static int[] dc = new int[] { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		init();

		simulate();

		System.out.println(time);
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		distance = new int[N][N];
		isVisited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (value > 0 && value <= 6) {
					Monster monster = new Monster(i, j, value);
					monsterList.add(monster);
				} else if (value == 9) {
					robbotR = i;
					robbotC = j;
				}

				if (value != 9) {
					map[i][j] = value;
				}
			}
		}
	}

	static void simulate() {
		while (true) {
			setDistance();

			Monster target = selectMonster();

			if (target == null)
				break;

			kill(target);
		}
	}

	static void setDistance() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(distance[i], 0);
			Arrays.fill(isVisited[i], false);
		}
		Queue<int[]> queue = new LinkedList<int[]>();

		queue.add(new int[] { robbotR, robbotC });
		isVisited[robbotR][robbotC] = true;

		while (!queue.isEmpty()) {
			int[] point = queue.poll();
			int cr = point[0];
			int cc = point[1];

			for (int i = 0; i < 4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];

				if (!isValidPoint(nr, nc) || isStrongMonster(nr, nc) || isVisited[nr][nc])
					continue;

				queue.add(new int[] { nr, nc });
				isVisited[nr][nc] = true;
				distance[nr][nc] = distance[cr][cc] + 1;
			}
		}
	}

	static Monster selectMonster() {
		Monster result = null;
		for (Monster monster : monsterList) {
			if (monster.power < robbotPower && isVisited[monster.r][monster.c]) {
				if (result == null) {
					result = monster;
					continue;
				}

				int monsterD = distance[monster.r][monster.c];
				if (distance[result.r][result.c] != monsterD) {
					result = monsterD < distance[result.r][result.c] ? monster : result;
					continue;
				} else if (monster.r != result.r) {
					result = result.r < monster.r ? result : monster;
					continue;
				} else if (monster.c != result.c) {
					result = result.c < monster.c ? result : monster;
				}
			}
		}

		return result;
	}

	static void kill(Monster monster) {
		time += distance[monster.r][monster.c];
		map[monster.r][monster.c] = 0;
		robbotR = monster.r;
		robbotC = monster.c;
		monsterList.remove(monster);

		killing += 1;

		if (robbotPower == killing) {
			robbotPower += 1;
			killing = 0;
		}
	}

	static boolean isStrongMonster(int r, int c) {
		return map[r][c] > robbotPower;
	}

	static boolean isMonster(int r, int c) {
		return 1 <= map[r][c] && 6 <= map[r][c];
	}

	static boolean isValidPoint(int r, int c) {
		return 0 <= r && r < N && 0 <= c && c < N;
	}

	static class Monster {
		int power, r, c;

		public Monster(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;
		}
	}

}