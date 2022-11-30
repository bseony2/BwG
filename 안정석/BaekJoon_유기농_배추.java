import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_유기농_배추 {

	static int TC, M, N, num;
	static int map[][];
	static boolean check[][];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			num = Integer.parseInt(st.nextToken());
			map = new int[M][N];
			for (int i = 0; i < num; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[x][y] = 1;
			}
			check = new boolean[M][N];
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (check[i][j] != true && map[i][j] == 1) {
						dfs(i, j);
						cnt++;
					}
				}
			}

			System.out.println(cnt);
		}
	}

	static void dfs(int x, int y) {
		check[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < M && ny >= 0 && ny < N && check[nx][ny] != true && map[nx][ny] == 1) {
				map[nx][ny] = 2;
				dfs(nx, ny);
			}
		}

	}

}
