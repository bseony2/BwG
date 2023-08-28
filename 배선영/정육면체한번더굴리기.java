import java.io.*;
import java.util.*;

public class 정육면체한번더굴리기 {
	static int N, M;
	static int[][] map;
	static int[] dice = new int[] {6, 1, 2, 5, 3, 4};
	static int diceR = 0, diceC = 0;
	static int[] dr = new int[] {-1, 0, 1, 0};	// U R D L
	static int[] dc = new int[] {0, 1, 0, -1};
	static int d = 1;
	static int score = 0;
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
		map = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void simulate() {
		while(M-->0) {
			moveDice();
			
			getScore();
			
			getNextDirection();
		}
	}
	
	static void moveDice() {
		int nr = diceR + dr[d];
		int nc = diceC + dc[d];
		
		if(!isValidPoint(nr, nc)) {
			d = (d+2) % 4;
			nr = diceR + dr[d];
			nc = diceC + dc[d];
		}
		
		diceR = nr;
		diceC = nc;
		
		int[] newDice = new int[6];
		if(d == 0) {					// {6, 1, 2, 5, 3, 4} => {5, 2, 6, 1, 3, 4}
			newDice[0] = dice[3];
			newDice[1] = dice[2];
			newDice[2] = dice[0];
			newDice[3] = dice[1];
			newDice[4] = dice[4];
			newDice[5] = dice[5];
		}
		else if (d == 1) {				// {6, 1, 2, 5, 3, 4} => {3, 4, 2, 5, 1, 6}
			newDice[0] = dice[4];
			newDice[1] = dice[5];
			newDice[2] = dice[2];
			newDice[3] = dice[3];
			newDice[4] = dice[1];
			newDice[5] = dice[0];
		}
		else if (d == 2) {				// {6, 1, 2, 5, 3, 4} => {2, 5, 1, 6, 3, 4}
			newDice[0] = dice[2];
			newDice[1] = dice[3];
			newDice[2] = dice[1];
			newDice[3] = dice[0];
			newDice[4] = dice[4];
			newDice[5] = dice[5];
		}
		else {							// {6, 1, 2, 5, 3, 4} => {4, 3, 2, 5, 6, 1}
			newDice[0] = dice[5];
			newDice[1] = dice[4];
			newDice[2] = dice[2];
			newDice[3] = dice[3];
			newDice[4] = dice[0];
			newDice[5] = dice[1];
		}
		for(int i=0; i<6; i++) {
			dice[i] = newDice[i];
		}
	}
	
	static void getNextDirection() {
		
		if(dice[0] > map[diceR][diceC]) {
			d = (d+1) % 4;
		}
		else if(dice[0] < map[diceR][diceC]){
			d = (d+3) % 4;
		}
	}
	
	static void getScore() {
		
		int value = map[diceR][diceC];
		int num = 0;
		boolean[][] isVisited = new boolean[N][N];
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {diceR, diceC});
		isVisited[diceR][diceC] = true;
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			int r = point[0];
			int c = point[1];
			num += 1;
			
			for(int i=0; i<4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				
				if(!isValidPoint(nr, nc) || map[nr][nc] != value || isVisited[nr][nc]) continue;
				
				queue.add(new int[] {nr, nc});
				isVisited[nr][nc] = true;
			}
		}
		score += num * value;
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
