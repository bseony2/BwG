import java.io.*;
import java.util.*;
import java.lang.Math;

public class 토스트계란틀 {

	static int[][] map, copy;
	static int N, L, R;
	static int ans = 0;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		init();

		simulate();
	
		System.out.print(ans);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		copy = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void simulate() {
		while(true) {
			mapcopy(map, copy);
			boolean isDone = true;
			boolean[][] isVisited = new boolean[N][N];
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!isVisited[i][j] && isPossible(i, j)) {
						isDone = false;
						bfs(i, j, isVisited);
					}
				}
			}
			if(isDone) break;
			ans += 1;
			mapcopy(copy, map);
			
		}
	}
	
	static boolean isPossible(int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isValidPoint(nr, nc) && isMoveable(r, c, nr, nc)) {
				return true;
			}
		}
		return false;
	}
	
	static void bfs(int r, int c, boolean[][] isVisited) {
		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> gather = new LinkedList<>();
		
		int[] init = new int[] {r, c};
		queue.add(init);
		gather.add(init);
		isVisited[r][c] = true;
		int sum = 0;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int cr = point[0];
			int cc = point[1];
			sum += map[cr][cc];
			
			for(int i=0; i<4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(!isValidPoint(nr, nc) || !isMoveable(cr, cc, nr, nc) || isVisited[nr][nc]) continue;
				
				isVisited[nr][nc] = true;
				int[] newPoint = new int[] {nr, nc};
				queue.add(newPoint);
				gather.add(newPoint);
			}
		}
		
		int nextValue = sum / gather.size();
		for(int[] point : gather) {
			copy[point[0]][point[1]] = nextValue;
		}
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static boolean isMoveable(int r, int c, int nr, int nc) {
		int value = Math.abs(map[r][c] - map[nr][nc]);
		return L <= value && value <= R;
	}
	
	static void mapcopy(int[][] origin, int[][] target) {
		for(int i=0; i<N; i++) {
			target[i] = origin[i].clone();
		}
	}

}
