import java.io.*;
import java.util.*;
import java.lang.Comparable;

public class 토끼와경주 {
	static int N, M, P;
	static ArrayList<Rabbit> rabbitList = new ArrayList<>();
	static HashMap<Integer, Rabbit> map = new HashMap<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		int Q = Integer.parseInt(br.readLine());
		
		
		while(Q-->0) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int order = Integer.parseInt(st.nextToken());
			
			if(order == 100) {	// 경주 시작 준비
				getReady();
			}
			if(order == 200) {	// 경주 진행
				race();
			}
			if(order == 300) {	// 이동거리 변경
				multiplyRabbitD();
			}
			if(order == 400) {	// 최고의 토끼 선정
				print();
			}
		}
	}
	
	static void getReady() {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<P; i++) {
			int id = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Rabbit rabbit = new Rabbit(id, d);
			map.put(id, rabbit);
			rabbitList.add(rabbit);
		}
	}
	
	static void race() {
		int K = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		while(K-->0) {
			Collections.sort(rabbitList);
			Rabbit rabbit = rabbitList.get(0);
			
			int[] point = new int[] {-1, -1};
			for(int i=0; i<4; i++) {
				int[] nextPoint = getNextPoint(rabbit, i);
				
				if(point[0] + point[1] != nextPoint[0] + nextPoint[1]) {
					point = point[0] + point[1] > nextPoint[0] + nextPoint[1] ? point : nextPoint;
				}
				else if(point[0] != nextPoint[0]) {
					point = point[0] > nextPoint[0] ? point : nextPoint;
				}
				else if(point[1] != nextPoint[1]) {
					point = point[1] > nextPoint[1] ? point : nextPoint;
				}
			}
			rabbit.r = point[0];
			rabbit.c = point[1];
			rabbit.j += 1;
			rabbit.isJump = true;
			
			for(int i=1; i<rabbitList.size(); i++) {
				rabbitList.get(i).score += point[0] + point[1]+2;
			}
		}
		
		rabbitList.sort((thisRabbit, thatRabbit) -> {
			if(thisRabbit.r + thisRabbit.c != thatRabbit.r + thatRabbit.c) {
				return thatRabbit.r+thatRabbit.c - (thisRabbit.r + thisRabbit.c);
			}
			else if(thatRabbit.r != thisRabbit.r) {
				return thatRabbit.r - thisRabbit.r;
			}
			else if(thatRabbit.c != thisRabbit.c) {
				return thatRabbit.c - thisRabbit.c;
			}
			return thatRabbit.id - thisRabbit.id;
		});
		boolean addAble = true;
		for(int i = 0; i<rabbitList.size(); i++) {
			Rabbit rabbit = rabbitList.get(i);
			if(rabbit.isJump && addAble) {
				rabbit.score += S;
				addAble = false;
			}
			rabbit.isJump = false;
		}
	}
	
	static int[] getNextPoint(Rabbit rabbit, int d) {
		int s = rabbit.s;
		int r = rabbit.r;
		int c = rabbit.c;
		
		if(d == 0 || d == 2) {
			s = rabbit.s % (N*2-2);
		}
		if(d == 1|| d == 3) {
			s = rabbit.s%(M*2-2);
		}
		
		int limit = getLimit(r, c, d);
		
		while(s != 0) {
			if(s <= limit) {
				r += dr[d]*s;
				c += dc[d]*s;
				s = 0;
			}
			
			if(s > limit) {
				s -= limit;
				r += dr[d]*limit;
				c += dc[d]*limit;
				d = (d+2) % 4;
			}
			limit = getLimit(r, c, d);
		}
		
		
		return new int[] {r, c};
	}
	
	static int getLimit(int r, int c, int d) {
		int limit=0;
		if(d == 0) {
			limit = r;
		}
		if(d == 2) {
			limit = N-r-1;
		}
		if(d == 1) {
			limit = M-c-1;
		}
		if(d == 3) {
			limit = c;
		}
		return limit;
	}
	
	static void multiplyRabbitD() {
		int id = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		Rabbit rabbit = map.get(id);
		rabbit.s *= L;
	}
	
	static void print() throws IOException {
		long answer = 0;
		for(Rabbit rabbit : rabbitList) {
			answer = answer > rabbit.score ? answer : rabbit.score;
		}
		
		System.out.println(answer);
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<M;
	}

	static class Rabbit implements Comparable<Rabbit>{
		int id, r, c, s, j;
		long score;
		boolean isJump;
		
		public Rabbit(int id, int s) {
			this.id = id;
			this.s = s;
			this.r = 0;
			this.c = 0;
			this.j = 0;
			this.score = 0;
			this.isJump = false;
		}
		
		public int compareTo(Rabbit rabbit) {
			if(this.j != rabbit.j) {
				int jum = this.j;
				return jum - rabbit.j;
			}
			if(this.r + this.c != rabbit.r + rabbit.c) {
				int thisR = this.r + this.c;
				int thatR = rabbit.r + rabbit.c;
				
				return thisR - thatR;
			}
			if(this.r != rabbit.r) return this.r - rabbit.r;
			if(this.c != rabbit.c) return this.c - rabbit.c;
			return this.id - rabbit.id;
		}
	}
}
