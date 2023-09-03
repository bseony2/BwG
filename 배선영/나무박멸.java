import java.io.*;
import java.util.*;

public class 나무박멸 {
	static int N, M, K, C;
	static int ans = 0;
	static int[][] treeMap, dead;
	static ArrayList<int[]> tempLand;
	static ArrayList<int[]> selectedLand;
	static int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dc = new int[] {0, 1, 1, 1, 0, -1, -1, -1};
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(ans);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		treeMap = new int[N][N];
		dead = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				treeMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void simulate() {
		while(M-->0) {
			// 1. 인접한 네 개의 칸 중 나무가 있는 칸의 수만큼 나무가 성장
			grow();
			
			// 2. 기존에 있었던 나무들은 인접한 4개의 칸 중 벽, 다른 나무, 제초제 모두 없는 칸에 번식을 진행합니다
			breeding();
			
			// 3. 각 칸 중 제초제를 뿌렸을 때 나무가 가장 많이 박멸되는 칸에 제초제를 뿌립니다.
			kill();
		}
	}
	
	static void grow() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isTreeExist(i, j)) {
					growTree(i, j);
				}
			}
		}
	}
	
	static void growTree(int r, int c) {
		int amt = 0;
		for(int i=0; i<8; i+=2) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isValidPoint(nr, nc) && isTreeExist(nr, nc))
				amt += 1;
		}
		treeMap[r][c] += amt;
	}
	
	static void breeding() {
		int[][] tempMap = new int[N][N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isTreeExist(i, j)) {
					breedTree(tempMap, i, j);
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				treeMap[i][j] += tempMap[i][j];
			}
		}
	}
	
	static void breedTree(int[][] tempMap, int r, int c) {
		int amt = 0;
		for(int i=0; i<8; i+=2) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isValidPoint(nr, nc) && isBlank(nr, nc) && !isDeadLand(nr, nc) && !isWall(nr, nc))
				amt += 1;
		}
		
		if(amt == 0) return;
		int size = treeMap[r][c] / amt;
		
		for(int i=0; i<8; i+=2) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(isValidPoint(nr, nc) && isBlank(nr, nc) && !isDeadLand(nr, nc) && !isWall(nr, nc))
				tempMap[nr][nc] += size;
		}
	}
	
	static void kill() {
		selectDeadLand();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dead[i][j] > 0)
					dead[i][j] -= 1;
			}
		}
		
		for(int[] point : selectedLand) {
			int r = point[0];
			int c = point[1];
			
			ans += treeMap[r][c];
			treeMap[r][c] = 0;
			dead[r][c] = C;
		}
	}
	
	static void selectDeadLand() {
		int deadTreeAmt = 0;
		selectedLand = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(isTreeExist(i, j)) {
					int amt = getKillingTreeAmt(i, j);
					if(amt > deadTreeAmt) {
						selectedLand = tempLand;
						deadTreeAmt = amt;
					}
				}
			}
		}
	}
	
	static int getKillingTreeAmt(int r, int c) {
		int result = treeMap[r][c];
		tempLand = new ArrayList<>();
		
		tempLand.add(new int[] {r, c});
		
		for(int i=1; i<8; i+=2) {
			for(int j=1; j<=K; j++) {
				int nr = r + dr[i] * j;
				int nc = c + dc[i] * j;
				if(!isValidPoint(nr, nc) || isWall(nr, nc)) break;
				result += treeMap[nr][nc];
				tempLand.add(new int[] {nr, nc});
				
				if(isWall(nr, nc) || isBlank(nr, nc)) break;
			}
		}
		return result;
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static boolean isDeadLand(int r, int c) {
		return dead[r][c] > 0;
	}
	
	static boolean isTreeExist(int r, int c) {
		return treeMap[r][c] > 0;
	}
	
	static boolean isWall(int r, int c) {
		return treeMap[r][c] == -1;
	}
	
	static boolean isBlank(int r, int c) {
		return treeMap[r][c] == 0;
	}

}
