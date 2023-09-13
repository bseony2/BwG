import java.util.*;
import java.io.*;
import java.lang.Math;

public class 바이러스백신 {
	static List<int[]> hospitals = new ArrayList<>();
	static List<int[]> virus = new ArrayList<>();
	static int N, M;
	static int[][] map;
	static int[][][] times;
	static final int VIRUS = 0, WALL = 1, HOSPITAL = 2;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(ans != Integer.MAX_VALUE ? ans : -1);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				if(value == HOSPITAL) {
					hospitals.add(new int[] {i, j});
				}
				else if(value == VIRUS) {
					virus.add(new int[] {i, j});
				}
			}
		}
		
		times = new int[hospitals.size()][N][N];
	}
	
	static void simulate() {
		if(virus.size() == 0) {
			ans = 0;
			return;
		}

		setTimes();
		
		selectHospital(0, 0, new boolean[hospitals.size()]);
	}
	
	static void setTimes() {
		int index=0;
		for(int[] point : hospitals) {
			bfs(point, index++);
		}
	}
	
	static void bfs(int[] input, int hospitalIndex) {
		boolean[][] isVisited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(input);
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int cr = point[0];
			int cc = point[1];
			
			for(int i=0; i<4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(!isValidPoint(nr, nc) || isVisited[nr][nc] || map[nr][nc] == WALL) continue;
				
				times[hospitalIndex][nr][nc] = times[hospitalIndex][cr][cc] + 1;
				isVisited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
	}
	
	static void selectHospital(int depth, int start, boolean[] isVisited) {
		if(depth == M) {
			int time = getTime(isVisited);
			if(time != -1) {
				ans = Math.min(ans, time);
			}
			return;
		}
		
		for(int i=start; i<hospitals.size(); i++) {
			if(!isVisited[i]) {
				isVisited[i] = true;
				selectHospital(depth + 1, i+1, isVisited);
				isVisited[i] = false;
			}
		}
	}
	
	static int getTime(boolean[] isVisited) {
		int result = Integer.MIN_VALUE;
		
		for(int[] point : virus) {
			int minTime = Integer.MAX_VALUE;
			
			for(int i=0; i<isVisited.length; i++) {
				int r = point[0];
				int c = point[1];
				if(isVisited[i]) {
					int time = times[i][r][c];
					if(time == 0) continue;
					minTime = Math.min(minTime, time);
				}
			}
			
			if(minTime == Integer.MAX_VALUE) return -1;
			result = Math.max(result, minTime);
		}
		
		return result;
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}