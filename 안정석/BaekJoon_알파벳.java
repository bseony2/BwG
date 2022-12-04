import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_알파벳 {

	static int M, N;
	static char map[][];
	static int maxcnt;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static boolean[] alpha;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new char[M][];
		alpha = new boolean[26];

		for (int i = 0; i < M; i++) {
			map[i] = br.readLine().toCharArray();
		}

		move(0, 0, 1);

		System.out.println(maxcnt);

	}

	static void move(int x, int y, int cnt) {

		alpha[map[x][y] - 'A'] = true;

		for (int d = 0; d < 4; d++) {

			int nx = x + dx[d];
			int ny = y + dy[d];

			if (nx >= 0 && nx < M && ny >= 0 && ny < N && !alpha[map[nx][ny] - 'A']) {  
				move(nx, ny, cnt+1); 
			}
		}
		alpha[map[x][y] - 'A'] = false;
		maxcnt = Math.max(maxcnt, cnt);
		
	}

}
