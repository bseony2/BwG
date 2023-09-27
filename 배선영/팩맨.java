import java.util.*;
import java.io.*;

public class 팩맨 {
	static int[][][] map = new int[4][4][8], eggs = new int[4][4][8];
	static int packmanR, packmanC;
	static int[] dr = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = new int[] { 0, -1, -1, -1, 0, 1, 1, 1 };
	static int[][] dead = new int[4][4];
	static int T, M, turn = 0;
	static List<int[]> totalMove = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		init();

		simulate();

		System.out.println(count());
	}

	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		packmanR = Integer.parseInt(st.nextToken()) - 1;
		packmanC = Integer.parseInt(st.nextToken()) - 1;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			map[r][c][d] += 1;
		}
		perm(0, new int[3]);
	}

	static void perm(int depth, int[] result) {
		if (depth == 3) {
			totalMove.add(result.clone());
			return;
		}

		for (int i = 0; i < 8; i += 2) {
			result[depth] = i;
			perm(depth + 1, result);
		}
	}

	static void simulate() {

		while (++turn <= T) {
			copyMonster();

			monsterMove();

			packManMove();

			deadBodyRemove();

			monsterBorn();
		}
	}

	static void copyMonster() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					eggs[i][j][d] = map[i][j][d];
				}
			}
		}
	}

	static void monsterMove() {
		int[][][] newMap = new int[4][4][8];

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					boolean isMove = false;
					if (map[i][j][d] == 0)
						continue;

					for (int k = 0; k < 8; k++) {
						int nd = (d + k) % 8;
						int nr = i + dr[nd];
						int nc = j + dc[nd];

						if (!isValidPoint(nr, nc) || dead[nr][nc] > 0 || isPackMan(nr, nc))
							continue;

						newMap[nr][nc][nd] += map[i][j][d];
						isMove = true;
						break;
					}
					
					if(!isMove) newMap[i][j][d] += map[i][j][d];

				}
			}
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					map[i][j][d] = newMap[i][j][d];
				}
			}
		}
	}

	static void packManMove() {
		int[] selectedMove = selectPackmanMove();

		for (int d : selectedMove) {
			int nr = packmanR + dr[d];
			int nc = packmanC + dc[d];

			if (getMonsterNum(nr, nc) > 0) {
				dead[nr][nc] = 3;
				eatMonster(nr, nc);
			}

			packmanR = nr;
			packmanC = nc;
		}
	}

	static int[] selectPackmanMove() {
		int[] result = null;
		int monsterNum = -1;

		for (int[] move : totalMove) {
			int num = 0;
			boolean isMoveAble = true;
			int r = packmanR;
			int c = packmanC;
			boolean[][] isVisited = new boolean[4][4];

			for (int d : move) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (!isValidPoint(nr, nc)) {
					isMoveAble = false;
					break;
				}

				if (!isVisited[nr][nc]) {
					num += getMonsterNum(nr, nc);
				}

				r = nr;
				c = nc;
				isVisited[nr][nc] = true;
			}

			if (!isMoveAble)
				continue;

			if (num > monsterNum) {
				monsterNum = num;
				result = move;
			}
		}

		return result;
	}

	static int getMonsterNum(int r, int c) {
		return Arrays.stream(map[r][c]).sum();
	}

	static void eatMonster(int r, int c) {
		Arrays.fill(map[r][c], 0);
	}

	static void deadBodyRemove() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (dead[i][j] > 0)
					dead[i][j] -= 1;
			}
		}
	}

	static void monsterBorn() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					map[i][j][d] += eggs[i][j][d];
					eggs[i][j][d] = 0;
				}
			}
		}
	}

	static int count() {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int d = 0; d < 8; d++) {
					result += map[i][j][d];
				}
			}
		}
		return result;
	}

	static boolean isValidPoint(int r, int c) {
		return 0 <= r && r < 4 && 0 <= c && c < 4;
	}

	static boolean isPackMan(int r, int c) {
		return r == packmanR && c == packmanC;
	}
}

// import java.util.*;
// import java.io.*;
// public class 팩맨 {
// 	static int[][] map = new int[4][4];
// 	static int[][] deadBody = new int[4][4];
// 	static ArrayList<Monster>[][] monsterMap = new ArrayList[4][4];
// 	static ArrayList<Monster>[][] monsterMapCopy = new ArrayList[4][4];
// 	static ArrayList<int[]> eggs = new ArrayList<>();
// 	static int[] dr = new int[] {-1, -1, 0, 1, 1, 1, 0, -1};
// 	static int[] dc = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
// 	static ArrayList<int[]> movements = new ArrayList<>();
// 	static int M, T;
// 	static int packmanR, packmanC;

// 	public static void main(String[] args) throws IOException {
// 		init();
		
// 		simulate();
		
// 		printAns();
// 	}
	
// 	static void init() throws IOException  {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
// 		M = Integer.parseInt(st.nextToken());
// 		T = Integer.parseInt(st.nextToken());
		
// 		st = new StringTokenizer(br.readLine(), " ");
// 		packmanR = Integer.parseInt(st.nextToken())-1;
// 		packmanC = Integer.parseInt(st.nextToken())-1;
		
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				monsterMap[i][j] = new ArrayList<Monster>();
// 				monsterMapCopy[i][j] = new ArrayList<Monster>();
// 			}
// 		}
		
// 		for(int i=0; i<M; i++) {
// 			st = new StringTokenizer(br.readLine(), " ");
// 			int r = Integer.parseInt(st.nextToken())-1;
// 			int c = Integer.parseInt(st.nextToken())-1;
// 			int d = Integer.parseInt(st.nextToken())-1;
			
// 			Monster monster = new Monster(r, c, d);
// 			monsterMap[r][c].add(monster);
// 		}
		
// 		perm(0, new int[3]);
// 	}
	
// 	static void perm(int depth, int[] result) {
// 		if(depth == 3) {
// 			movements.add(result.clone());
// 			return;
// 		}
		
// 		for(int i=0; i<8; i+=2) {
// 			result[depth] = i;
// 			perm(depth+1, result);
// 		}
// 	}
	
// 	static void simulate() {
// 		while(T-->0) {
// 			copyMonster();
			
// 			moveMonsters();
			
// 			movePackman();
			
// 			deadBodyRemove();
			
// 			generateMonster();
// 		}
// 	}
	
// 	static void copyMonster() {
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				for(Monster monster : monsterMap[i][j]) {
// 					eggs.add(new int[] {monster.r, monster.c, monster.d});
// 				}
// 			}
// 		}
// 	}
	
// 	static void moveMonsters() {
		
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				monsterMapCopy[i][j] = new ArrayList<Monster>();
// 			}
// 		}
		
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				for(Monster monster : monsterMap[i][j]) {
// 					move(monster);
// 				}
// 			}
// 		}
		
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				monsterMap[i][j] = monsterMapCopy[i][j];
// 			}
// 		}
// 	}
	
// 	static void move(Monster monster) {
// 		int r = monster.r;
// 		int c = monster.c;
// 		int d = monster.d;
		
// 		for(int i=0; i<8; i++, d=(d+1)%8) {
// 			int nr = r + dr[d];
// 			int nc = c + dc[d];
			
// 			if(!isValidPoint(nr, nc) || isDeadBodyLocate(nr, nc) || isPackManLocate(nr, nc)) continue;
			
// 			r = nr;
// 			c = nc;
			
// 			break;
// 		}
// 		Monster newMonster = new Monster(r, c, d);
// 		monsterMapCopy[r][c].add(newMonster);
// 	}
	
// 	static void movePackman() {
// 		int[] selectedMove = selectMove();
		
// 		for(int d : selectedMove) {
// 			int nr = packmanR + dr[d];
// 			int nc = packmanC + dc[d];
			
// 			if(monsterMap[nr][nc].size() > 0) {
// 				deadBody[nr][nc] = 3;
// 				monsterMap[nr][nc].clear();
// 			}
			
// 			packmanR = nr;
// 			packmanC = nc;
// 		}
// 	}
	
// 	static int[] selectMove() {
// 		int[] result = null;
// 		int selectedMovementMonsterAmt = -1;
		
// 		for(int[] move : movements) {
// 			int r = packmanR;
// 			int c = packmanC;
// 			int monsterAmt = 0;
// 			boolean isImpossibleMove = false;
// 			boolean[][] isVisited = new boolean[4][4];
			
// 			for(int d : move) {
// 				int nr = r + dr[d];
// 				int nc = c + dc[d];
// 				if(!isValidPoint(nr, nc)) {
// 					isImpossibleMove = true;
// 					break;
// 				}
// 				if(!isVisited[nr][nc]) {
// 					monsterAmt += monsterMap[nr][nc].size();
// 				}
// 				isVisited[nr][nc] = true;
				
// 				r = nr;
// 				c = nc;
// 			}
			
// 			if(isImpossibleMove) continue;
			
// 			if(selectedMovementMonsterAmt < monsterAmt) {
// 				selectedMovementMonsterAmt = monsterAmt;
// 				result = move;
// 			}
// 		}
		
// 		return result;
// 	}
	
// 	static void deadBodyRemove() {
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				deadBody[i][j] -= 1;
// 			}
// 		}
// 	}
	
// 	static void generateMonster() {
// 		for(int[] egg : eggs) {
// 			int r = egg[0];
// 			int c = egg[1];
// 			int d = egg[2];
			
// 			Monster monster = new Monster(r, c, d);
// 			monsterMap[r][c].add(monster);
// 		}
// 		eggs.clear();
// 	}
	
// 	static void printAns() {
// 		int ans = 0;
// 		for(int i=0; i<4; i++) {
// 			for(int j=0; j<4; j++) {
// 				ans += monsterMap[i][j].size();
// 			}
// 		}
		
// 		System.out.println(ans);
// 	}
	
// 	static boolean isValidPoint(int r, int c) {
// 		return 0<=r && r<4 && 0<=c && c<4;
// 	}
	
// 	static boolean isDeadBodyLocate(int r, int c) {
// 		return deadBody[r][c] > 0;
// 	}
	
// 	static boolean isPackManLocate(int r, int c) {
// 		return packmanR == r && packmanC == c;
// 	}
	
// 	static class Monster {
// 		int r, c, d;
		
// 		public Monster(int r, int c, int d) {
// 			this.r = r;
// 			this.c = c;
// 			this.d = d;
// 		}
// 	}

// }
