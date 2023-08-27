import java.io.*;
public class 두개의사탕 {
	static int N, M;
	static char[][] map;
	static int ans = Integer.MAX_VALUE;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new char[N][M];
		
		for(int i=0; i<N; i++) {
			char[] charArr = br.readLine().toCharArray(); 
			for(int j=0; j<M; j++) {
				map[i][j] = charArr[j];
			}
		}
	}
	
	static void simulate() {
		dfs(0, 0);
	}
	
	static void dfs(int depth, int d) {
		if(depth >= ans) return;
		boolean isBlueExist = isCandyExist('B');
		boolean isRedExist = isCandyExist('R');
		
		if(!isBlueExist) return;
		if(!isRedExist) {
			ans = ans > depth ? depth : ans;
			return;
		}
		
		if(depth == 10) {
			return;
		}
		
		char[][] tempMap = copyMap(map);
		
		int end = depth == 0 ? 4 : 3;
		for(int i=0; i<end; i++) {
			d = (d+1) % 4;
			move(d);
			dfs(depth+1, d);
			map = copyMap(tempMap);
		}
	}
	
	static boolean isCandyExist(char color) {
		boolean result = false;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == color) return true;
			}
		}
		
		return result;
	}

	static char[][] copyMap(char[][] originMap) {
		char[][] result = new char[N][M];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				result[i][j] = originMap[i][j];
			}
		}
		
		return result;
	}
	
	static void move(int d) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(isCandy(i, j)) {
					moveCandy(i, j, d, true);
				}
			}
		}
	}
	
	static void moveCandy(int r, int c, int d, boolean isFirst) {
		char color = map[r][c];
		map[r][c] = '.';
		while(true) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(isBlock(nr, nc)) break;
			
			if(isCandy(nr, nc) && isFirst) {
				moveCandy(nr, nc, d, false);
				if(isCandy(nr, nc)) break;
			}
			
			if(isExit(nr, nc)) return;
			
			r = nr;
			c = nc;
		}
		
		map[r][c] = color;
	}
	
	static boolean isCandy(int r, int c) {
		return map[r][c] == 'R' || map[r][c] == 'B';
	}
	
	static boolean isBlock(int r, int c) {
		return map[r][c] == '#';
	}
	
	static boolean isExit(int r, int c) {
		return map[r][c] == 'O';
	}
}