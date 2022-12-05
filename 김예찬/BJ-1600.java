package Boj.gold;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj_1600 {
	// 동서남북 좌우상하(말)
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[] hx = { -2, -2, 2, 2, 1, -1, 1, -1 };
	static int[] hy = { 1, -1, 1, -1, 2, 2, -2, -2 };
	static int K, W, H;
	static int[][] map;
	static boolean[][][] visit;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		K = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();

		map = new int[H][W];
		visit = new boolean[H][W][31];
		// map 입력
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		visit[0][0][0] = true;
		bfs();
	}

	private static void bfs() {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0, 0, K));

		while (!q.isEmpty()) {
			Pair cur = q.poll();
			int curX = cur.x; // 현재 x좌표
			int curY = cur.y; // 현재 y좌표
			int cnt = cur.cnt;// 카운트
			int curK = cur.k; // 말처럼 이동할 수 있는 횟수
			//도착했을경우 카운트 출력 후 리턴
			if (curX == W - 1 && curY == H - 1) {
				System.out.println(cnt);
				return;
			}
			// 초과하면 continue
			if (curX >= W || curY >= H || curX < 0 || curY < 0)
				continue;
			// 방해물이면 continue
			if (map[curY][curX] == 1)
				continue;
			// 방문한적이 있으면 continue
			if (visit[curY][curX][curK])
				continue;
			
			// 현재위치 방문 true
			visit[curY][curX][curK] = true;

			// 상 하 좌 우
			for (int i = 0; i < 4; i++) {
				int nextX = curX + dx[i];
				int nextY = curY + dy[i];

				q.add(new Pair(nextX, nextY, cnt + 1, curK));

			}

			//횟수를 다 쓰면 컨티뉴
			if (curK == 0)
				continue;
			
			// 말처럼 움직이기
			for (int i = 0; i < 8; i++) {
				int nextX = curX + hx[i];
				int nextY = curY + hy[i];

				q.add(new Pair(nextX, nextY, cnt + 1, curK - 1));

			}

		}
		System.out.println("-1");
		return;

	}

}

class Pair {
	int x;
	int y;
	int cnt;
	int k;

	Pair(int x, int y, int cnt, int k) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.k = k;
	}
}
 