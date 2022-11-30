import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon_토마토 {

	static int M, N;
	static int map[][];
	static boolean zeroCheck[];
	static boolean minusCheck;
	static ArrayList<Point> oldTomato;
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static int timeCnt;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 열
		M = Integer.parseInt(st.nextToken()); // 행

		map = new int[M][N];
		oldTomato = new ArrayList<Point>();
		zeroCheck = new boolean[2];
		timeCnt = -1;
		minusCheck = false;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) { // 초기 저장값이 1과 -1로만 이루어져 있는지 체크
					zeroCheck[0] = true;
				}
				if (map[i][j] == 1) {
					zeroCheck[1] = true;
					oldTomato.add(new Point(i, j)); // 익은 초마토 초기 위치 저장
				}
			}
		}

		bfs();

		for (int i = 0; i < M; i++) { // 완탐 이후 안익은 토마토 0 이 있으면 체크
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					minusCheck = true;
				}
			}
		}

		if (zeroCheck[0] == false && zeroCheck[1] == true) {
			System.out.println("0");
		} else if (minusCheck == true) {
			System.out.println("-1");
		} else {
			System.out.println(timeCnt);
		}

	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		for (int i = 0; i < oldTomato.size(); i++) {
			q.add(new Point(oldTomato.get(i).x, oldTomato.get(i).y));
		}
		int size = q.size();
		while (!q.isEmpty()) {
			size = q.size();
			for (int i = 0; i < size; i++) { // 퍼져나가는 층을 알기위해 q에 저장된 사이즈만큼 나눠주기
				int x = q.peek().x;
				int y = q.peek().y;
				q.poll();

				for (int d = 0; d < 4; d++) {
					int nx = x + dx[d];
					int ny = y + dy[d];

					if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[nx][ny] == 0) {
						map[nx][ny] = 1;
						q.add(new Point(nx, ny));
					}
				}
			}
			
//			for(int i=0; i<M; i++) {
//				System.out.println();
//				for(int j=0; j<N; j++) {
//					System.out.print(map[i][j]+" ");
//				}
//			}
//			System.out.println();
			
			timeCnt++;
		}

	}

}
