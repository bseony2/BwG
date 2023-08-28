import java.io.*;
import java.util.*;
public class 두개의사탕 {
	static int N, M;
	static char[][] map;
	static int ans = -1;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int[] initRedCandy = new int[2];
	static int[] initBlueCandy = new int[2];

	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(ans);
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
				if(map[i][j] == 'R') {
					initRedCandy[0] = i;
					initRedCandy[1] = j;
				}
				else if(map[i][j] == 'B') {
					initBlueCandy[0] = i;
					initBlueCandy[1] = j;
				}
			}
		}
	}
	
	static void simulate() {
		ans = bfs();
	}

	static int bfs() {
		boolean[][][][] isVisited = new boolean[N][M][N][M];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[]{initRedCandy[0], initRedCandy[1], initBlueCandy[0], initBlueCandy[1], 1});
		isVisited[initRedCandy[0]][initRedCandy[1]][initBlueCandy[0]][initBlueCandy[1]] = true;

		while(!queue.isEmpty()) {
			int[] candys = queue.poll();
			int depth = candys[4];

			for(int i=0; i<4; i++) {
				
				int[] newRedCandy = moveCandy(candys[0], candys[1], i);
				int[] newBlueCandy = moveCandy(candys[2], candys[3], i);

				boolean isRedExist = newRedCandy[0] > -1;
				boolean isBlueExist = newBlueCandy[0] > -1;
				if(!isBlueExist) continue;
				if(!isRedExist) {
					return depth;
				}
				
				if(sameLocation(newRedCandy, newBlueCandy)) {
					if(i == 0) {
						if(candys[0] < candys[2]) {
							newBlueCandy[0] += 1;
						} else {
							newRedCandy[0] += 1;
						}
					}
					else if(i == 1) {
						if(candys[1] < candys[3]) {
							newRedCandy[1] -= 1;
						} else {
							newBlueCandy[1] -= 1;
						}
					}
					else if(i == 2) {
						if(candys[0] < candys[2]) {
							newRedCandy[0] -= 1;
						} else {
							newBlueCandy[0] -= 1;
						}
					} else {
						if(candys[1] < candys[3]) {
							newBlueCandy[1] += 1;
						} else {
							newRedCandy[1] += 1;
						}
					}
				}
				
				if(depth == 10) continue;
				if(isVisited[newRedCandy[0]][newRedCandy[1]][newBlueCandy[0]][newBlueCandy[1]]) continue;
				queue.add(new int[]{newRedCandy[0], newRedCandy[1], newBlueCandy[0], newBlueCandy[1], depth+1});
				isVisited[newRedCandy[0]][newRedCandy[1]][newBlueCandy[0]][newBlueCandy[1]] = true;
			}
		}
		return -1;
	}

	static int[] moveCandy(int r, int c, int d) {
		int[] result = new int[2];
		while(true) {

			int nr = r + dr[d];
			int nc = c + dc[d];

			if(!isValidPoint(nr, nc) || isBlock(nr, nc)) break;
			if(map[nr][nc] == 'O') {
				result[0] = -1;
				result[1] = -1;
				return result;
			}
			r = nr;
			c = nc;
		}
		result[0] = r;
		result[1] = c;

		return result;
	}

	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

	static boolean isBlock(int r, int c) {
		return map[r][c] == '#';
	}

	static boolean sameLocation(int[] redCandy, int[] blueCandy) {
		return redCandy[0] == blueCandy[0] && redCandy[1] == blueCandy[1];
	}
}