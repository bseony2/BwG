import java.util.*;
import java.io.*;

public class 꼬리잡기놀이 {
	static int N, M, K;
	static int[][] map;
	static int score = 0;
	static int[] dr = new int[] {0, -1, 0, 1};
	static int[] dc = new int[] {1, 0, -1, 0};
	static int ballR = 0, ballC = 0, ballD = 0;
	static int turn = 0;
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(score);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void simulate() {
		while(++turn <= K) {
			move();
			
			throwBall();
		}
	}
	
	static void move() {
		boolean[][] isVisited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!isVisited[i][j] && isPerson(i, j)) {
					int[] info = getTrainInfo(isVisited, i, j);
					
					try {
					map[info[6]][info[7]] = 3;
					map[info[0]][info[1]] = 2;
					map[info[2]][info[3]] = 4;
					map[info[4]][info[5]] = 1;
					
					}
					catch(Exception e) {
						System.out.println(e);
					}
				}
			}
		}
	}
	
	static int[] getTrainInfo(boolean[][] isVisited, int r, int c) {
		int headR=-1, headC=-1, tailR=-1, tailC=-1, nextHeadR=-1, nextHeadC=-1, nextTailR=-1, nextTailC=-1, lenInfo = -1;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		isVisited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int cr = point[0];
			int cc = point[1];
			
			if(map[cr][cc] == 1) {
				headR = cr;
				headC = cc;
			}
			else if(map[cr][cc] == 3) {
				tailR = cr;
				tailC = cc;
			}
			
			for(int i=0; i<4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(!isValidPoint(nr, nc) || map[nr][nc] == 0) continue;
				if((map[nr][nc] == 4 || map[nr][nc] == 3) && cr == headR && cc == headC) {
					nextHeadR = nr;
					nextHeadC = nc;
					if(map[nr][nc] == 4) isVisited[nr][nc] = true;
					continue;
				}
				if(map[nr][nc] == 2 && cr == tailR && cc == tailC) {
					nextTailR = nr;
					nextTailC = nc;
				}
				
				if(map[nr][nc] == 4 || isVisited[nr][nc]) continue;
				
				isVisited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				
			}
		}
		
		return new int[] {headR, headC, tailR, tailC, nextHeadR, nextHeadC, nextTailR, nextTailC, lenInfo};
	}
	
	static void throwBall() {
		int manR = -1;
		int manC = -1;
		for(int i=0; i<N; i++) {
			int nr = ballR + dr[ballD] * i;
			int nc = ballC + dc[ballD] * i;
			
			if(isPerson(nr, nc)) {
				manR = nr;
				manC = nc;
				break;
			}
		}
		
		int d = (ballD + 3) % 4;
		int nr = ballR + dr[d];
		int nc = ballC + dc[d];
		
		if(!isValidPoint(nr, nc)) {
			ballD = (ballD + 1) % 4;
		}
		else {
			ballR = nr;
			ballC = nc;
		}
		
		if(manR == -1) return;
		int[] trainInfo = getTrainInfo(new boolean[N][N], manR, manC);
		int len = getLen(trainInfo[0], trainInfo[1], manR, manC);
		score += len * len;
		// {headR, headC, tailR, tailC, nextHeadR, nextHeadC, nextTailR, nextTailC}
		map[trainInfo[0]][trainInfo[1]] = 3;
		map[trainInfo[2]][trainInfo[3]] = 1;
	}
	
	static int getLen(int r, int c, int manR, int manC) {
		if(r == manR && c == manC) return 1;
		int cr = r;
		int cc = c;
		boolean[][] isVisited = new boolean[N][N];
		int result = 1;
		
		while(true) {
			isVisited[cr][cc] = true;
			for(int i=0; i<4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(!isValidPoint(nr, nc) || isVisited[nr][nc] || !isPerson(nr, nc)) continue;
				if(map[nr][nc] == 3 && (cr == r && cc ==c)) continue;
				result += 1;
				cr = nr;
				cc = nc;
				
				break;
			}
			if(cr == manR && cc == manC) break;
		}
		return result;
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static boolean isPerson(int r, int c) {
		return 0<map[r][c] && map[r][c] < 4;
	}
}