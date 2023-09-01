import java.io.*;
import java.util.*;
import java.lang.Math;

public class 술래잡기 {
	static int score = 0;
	static int N, M, H, K;
	static boolean[][] trees;
	static int[][][] thieves;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int policeR, policeC, policeD = 0;
	static boolean[][] isVisited;
	static int turn = 0;
	static boolean isPoliceReverse = false;
	
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
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		trees = new boolean[N][N];
		thieves = new int[N][N][4];
		policeR = N/2;
		policeC = N/2;
		isVisited = new boolean[N][N];
		isVisited[policeR][policeC] = true;
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			
			thieves[r][c][d] += 1;
		}
		
		for(int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			trees[r][c] = true;
		}
	}
	
	static void simulate() {
		while(++turn <= K) {
			thievesMove();
			
			policeMove();
			
			catchThieves();
		}
	}
	
	static void thievesMove() {
		int[][][] copyMap = new int[N][N][4];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(Math.abs(i-policeR) + Math.abs(j-policeC) > 3) {
					copyMap[i][j][0] += thieves[i][j][0];
					copyMap[i][j][1] += thieves[i][j][1];
					copyMap[i][j][2] += thieves[i][j][2];
					copyMap[i][j][3] += thieves[i][j][3];
				}
				else {
					for(int d=0; d<4; d++) {
						if(thieves[i][j][d] == 0) continue;
						int nd = d;
						int nr = i + dr[nd];
						int nc = j + dc[nd];
						
						if(!isValidPoint(nr, nc)) {
							nd = (nd+2) % 4;
							nr = i + dr[nd];
							nc = j + dc[nd];
						}
						if(policeR == nr && policeC == nc) {
							copyMap[i][j][nd] += thieves[i][j][d];
						} else {
							copyMap[nr][nc][nd] += thieves[i][j][d];
						}
						thieves[i][j][d] = 0;
					}
				}
			}
		}
		
		thieves = copyMap;
	}
	
	static void policeMove() {
		policeR += dr[policeD];
		policeC += dc[policeD];
		isVisited[policeR][policeC] = true;
		
		int nd = isPoliceReverse ? policeD : (policeD + 1) % 4;
		int nr = policeR + dr[nd];
		int nc = policeC + dc[nd];
		
		if(!isPoliceReverse) {
			if(isValidPoint(nr, nc) && !isVisited[nr][nc]) {
				policeD = nd;
			}
		}
		else {
			if(!isValidPoint(nr, nc) || isVisited[nr][nc]) {
				policeD = (policeD+3)%4;
			}
		}
		
		
		if((policeR == 0 && policeC == 0) || (policeR == N/2 && policeC == N/2)) {
			isVisited = new boolean[N][N];
			isVisited[policeR][policeC] = true;
			isPoliceReverse = !isPoliceReverse;
			policeD = isPoliceReverse ? 2 : 0;
		}
	}
	
	static void catchThieves() {
		int numOfThief = 0;
		for(int i=0; i<=2; i++) {
			int nr = policeR + dr[policeD]*i;
			int nc = policeC + dc[policeD]*i;
			
			if(!isValidPoint(nr, nc)) break;
			
			if(trees[nr][nc]) continue;
			
			for(int d=0; d<4; d++) {
				numOfThief += thieves[nr][nc][d];
				thieves[nr][nc][d] = 0;
			}
		}
		
		if(numOfThief > 0) {
			M -= numOfThief;
			score += numOfThief * turn;
		}
		
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
}
