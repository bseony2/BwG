import java.io.*;
import java.util.*;

public class 싸움땅 {
	static int N, M, K;
	static PriorityQueue<Integer>[][] guns;
	static int[][] gunners;
	static ArrayList<Gunner> gunnerList = new ArrayList<>();
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	static int[] scores;

	public static void main(String[] args) throws IOException {
		init();

		simulate();
		
		print();
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		guns = new PriorityQueue[N][N];
		gunners = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				guns[i][j] = new PriorityQueue<>(Collections.reverseOrder());
				int value = Integer.parseInt(st.nextToken());
				if(value> 0) {
					guns[i][j].add(value);
				}
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			Gunner gunner = new Gunner(gunnerList.size()+1, r, c, d, s);
			gunnerList.add(gunner);
			gunners[r][c] = gunner.id;
		}
		
		scores = new int[M+1];
	}
	
	static void simulate() {
		while(K-->0) {			
			moveGunners();
		}
	}
	
	static void moveGunners() {
		for(Gunner gunner : gunnerList) {
			gunners[gunner.r][gunner.c] = 0;
			move(gunner);
			
			if(!isGunnerExist(gunner.r, gunner.c)) {
				gunners[gunner.r][gunner.c] = gunner.id;
				getGun(gunner);
			}
			else if(isGunnerExist(gunner.r, gunner.c) ) {
				fight(gunner);
			}
		}
	}
	
	static void move(Gunner gunner) {
		int nr = gunner.r + dr[gunner.d];
		int nc = gunner.c + dc[gunner.d];
		
		if(!isValidPoint(nr, nc)) {
			gunner.d = (gunner.d + 2) % 4;
			nr = gunner.r + dr[gunner.d];
			nc = gunner.c + dc[gunner.d];
		}
		
		gunner.r = nr;
		gunner.c = nc;
	}
	
	static void getGun(Gunner gunner) {
		if(guns[gunner.r][gunner.c].size() == 0) return;
		
		int gun = guns[gunner.r][gunner.c].peek();
		if(gun > gunner.gun) {
			dropGun(gunner);
			gunner.gun = guns[gunner.r][gunner.c].poll();
		}
	}
	
	static void dropGun(Gunner gunner) {
		if(gunner.gun == 0) return;
		guns[gunner.r][gunner.c].add(gunner.gun);
		gunner.gun = 0;
	}
	
	static void fight(Gunner gunner) {
		Gunner winner = null;
		Gunner loser = null;
		Gunner existGunner = gunnerList.get(gunners[gunner.r][gunner.c]-1);
		
		if(existGunner.getPower() != gunner.getPower()) {
			winner = existGunner.getPower() > gunner.getPower() ? existGunner : gunner;
			loser = existGunner.getPower() > gunner.getPower() ? gunner : existGunner;
		}
		else {
			winner = existGunner.s > gunner.s ? existGunner : gunner;
			loser = existGunner.s > gunner.s ? gunner : existGunner;
		}
		
		scores[winner.id] += winner.getPower() - loser.getPower();
		
		dropGun(loser);
		getGun(winner);
		
		gunners[winner.r][winner.c] = winner.id;
		moveLoser(loser);
	}
	
	static void moveLoser(Gunner gunner) {
		for(int i=0; i<4; i+=1, gunner.d = (gunner.d+1) % 4) {
			int nr = gunner.r + dr[gunner.d];
			int nc = gunner.c + dc[gunner.d];
			
			if(!isValidPoint(nr, nc) || isGunnerExist(nr, nc)) continue;
			
			move(gunner);
			gunners[gunner.r][gunner.c] = gunner.id;
			getGun(gunner);
			break;
		}
	}
	
	static void print() {
		for(int i=1; i<=M; i++) {
			System.out.print(scores[i] + " ");
		}
	}

	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static boolean isGunnerExist(int r, int c) {
		return gunners[r][c] > 0;
	}
	
	static class Gunner {
		int id, r, c, d, s, gun;
		public Gunner(int id, int r, int c, int d, int s) {
			this.id = id;
			this.r = r;
			this.c = c;
			this.d = d;
			this.s = s;
			this.gun = 0;
		}
		
		public int getPower() {
			return this.gun + this.s;
		}
	}
}