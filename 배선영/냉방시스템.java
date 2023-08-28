import java.io.*;
import java.util.*;

public class 냉방시스템 {
	static int N, M, K;
	static int[][] map, temperature;
	static boolean[][][] walls;
	static ArrayList<Machine> machines = new ArrayList<>();
	static int[] dr = new int[] {0, -1, 0, 1};
	static int[] dc = new int[] {-1, 0, 1, 0};
	static int[] diagonalR = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] diagonalC = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		temperature = new int[N][N];
		walls = new boolean[N][N][4];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
				if(value >= 2) {
					machines.add(new Machine(i, j, value-2));
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken())-1;
			int y = Integer.parseInt(st.nextToken())-1;
			int s = (Integer.parseInt(st.nextToken()) + 1) % 2;
			
			walls[x][y][s] = true;
			if(s == 0) {
				int nx = x + dr[s];
				int ny = y + dc[s];
				if(isValidPoint(nx, ny)) {
					walls[nx][ny][2] = true;
				}
			}
			else {
				int nx = x + dr[s];
				int ny = y + dc[s];
				if(isValidPoint(nx, ny) ) {
					walls[nx][ny][3] = true;
				}
			}
		}
	}
	
	static void simulate() {
		int turn = 0;
		while(++turn <= 100) {
			cooling();
			
			airMix();
			
			subtract();
			
			if(isEnd()) {
				break;
			}
		}
		System.out.println(turn <= 100 ? turn : -1);
	}
	
	static void cooling() {
		for(Machine machine : machines) {
			runMachine(machine);
		}
	}
	
	static void runMachine(Machine machine) {
		boolean[][] isVisited = new boolean[N][N];
		int[][] tempTemperature = new int[N][N];
		
		Queue<int[]> queue = new LinkedList<>();
		
		queue.add(new int[] {machine.r + dr[machine.d], machine.c + dc[machine.d], 5});
		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			int r = info[0];
			int c = info[1];
			int d = machine.d * 2;
			int value = info[2];
			
			tempTemperature[r][c] = value;
			
			if(value == 1) continue;
			for(int i=-1; i<2; i++) {
				int nd = (d+i+8) % 8;
				int nr = r + diagonalR[nd];
				int nc = c + diagonalC[nd];
				
				if(!isValidPoint(nr, nc) || isVisited[nr][nc]) continue;
				if(i == 0 && walls[r+dr[machine.d]][c+dc[machine.d]][(machine.d+2)%4]) continue; 
				if(i != 0) {
					nd = (machine.d+i+4)%4;
					if(walls[r][c][nd]) continue;
					if(walls[r+dr[nd]][c+dc[nd]][machine.d]) continue;
				}
				queue.add(new int[] {nr, nc, value-1});
				isVisited[nr][nc] = true;
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temperature[i][j] += tempTemperature[i][j];
			}
		}
	}
	
	static void airMix() {
		int[][] tempMap = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temperature[i][j] == 0) continue;
				share(tempMap, i, j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				temperature[i][j] += tempMap[i][j];
			}
		}
	}
	
	static void share(int[][] tempMap, int r, int c) {
		for(int i=0; i<4; i++) {
			if(walls[r][c][i]) continue;
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isValidPoint(nr, nc)) continue;
			
			if(temperature[r][c] - temperature[nr][nc] >= 4) {
				int val = (temperature[r][c] - temperature[nr][nc]) / 4;
				tempMap[nr][nc] += val;
				tempMap[r][c] -= val;
			}
		}
	}
	
	static void subtract() {
		for(int i=0; i<N; i++) {
			if(temperature[0][i] > 0) temperature[0][i] -= 1;
		}
		
		for(int i=1; i<N; i++) {
			if(temperature[i][0] > 0) temperature[i][0] -= 1;
		}
		
		for(int i=1; i<N; i++) {
			if(temperature[i][N-1] > 0) temperature[i][N-1] -= 1;
		}
		
		for(int i=1; i<N; i++) {
			if(temperature[N-1][i] > 0) temperature[N-1][i] -= 1;
		}
	}
	
	static boolean isEnd() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(temperature[i][j] < K && map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static class Machine {
		int r, c, d;
		
		public Machine(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

}
