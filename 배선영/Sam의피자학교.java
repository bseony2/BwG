import java.io.*;
import java.util.*;
import java.lang.Math;
public class Sam의피자학교 {
	static int N, K;
	static int[] flour;
	static int[][] dough;
	static int min, max;
	static int doughHeight, doughWidth, sideDoughWidth;
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		flour = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			flour[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void simulate() {
		int turn = 0;
		while(diffOfMinMax() > K) {
			
			// 1. 밀가루 양이 가장 작은 위치에 밀가루 1만큼 더 넣어줍니다.(가장 작은 위치가 여러 개라면 모두 넣기)
			addFlour();
			
			// 2. 도우를 말아줍니다.
			roll();
			
			// 3. 도우를 꾹 눌러줍니다.
			flat();
			
			// 4. 도우를 두 번 반으로 접어줍니다.
			fold();
			
			//5. 3의 과정만 한번 더 진행합니다.
			flat();
			
			turn += 1;
		}
		
		System.out.println(turn);
	}
	
	static int diffOfMinMax() {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		
		for(int i=0; i<N; i++) {
			max = Math.max(max, flour[i]);
			min = Math.min(min, flour[i]);
		}
		
		return max - min;
	}
	
	static void addFlour() {
		for(int i=0; i<N; i++) {
			if(flour[i] == min) flour[i] += 1;
		}
	}
	
	static void roll() {
		dough = new int[N][N];
		dough[N-1] = flour.clone();
		
		while(getDoughWidth(dough) - getSideDoughWidth(dough) >= getDoughHeight(dough)) {
			sideDoughWidth = sideDoughWidth == 0 ? 1 : sideDoughWidth; 
			
			int[][] copyDough = new int[sideDoughWidth+1][N];
			
			for(int i=0; i<sideDoughWidth; i++) {
				for(int j=0; j<doughHeight; j++) {
					copyDough[i][j] = dough[N-1-j][i];
				}
			}
			
			
			System.arraycopy(dough[N-1], sideDoughWidth, copyDough[copyDough.length-1], 0, doughWidth - sideDoughWidth);
			
			for(int i=0; i<copyDough.length; i++) {
				dough[N-copyDough.length+i] = copyDough[i].clone();
			}
			
		}
	}
	
	static int getDoughHeight(int[][] dough) {
		int result = 1;
		
		for(int i=N-2; i>=0; i--) {
			if(dough[i][0] == 0) break;
			
			result += 1;
		}
		
		doughHeight = result;
		return doughHeight;
	}
	
	static int getDoughWidth(int[][] dough) {
		int result = 0;
		for(int i=0; i<N; i++) {
			if(dough[N-1][i] == 0) break;
			
			result += 1;
		}
		
		doughWidth =  result;
		return doughWidth;
	}
	
	static int getSideDoughWidth(int[][] dough) {
		int result = 0;
		for(int i=0; i<N; i++) {
			if(dough[N-2][i] == 0) break;
			
			result += 1;
		}
		
		sideDoughWidth =  result;
		return sideDoughWidth;
	}
	
	static void flat() {
		int[][] calDough = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(dough[i][j] == 0) break;
				shareFlour(calDough, i, j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dough[i][j] += calDough[i][j];
			}
		}
		
		int index = 0;
		for(int j=0; j<N; j++) {
			for(int i=N-1; i>=0; i--) {
				if(dough[i][j] == 0) break;
				flour[index++] = dough[i][j];
			}
		}
	}
	
	static void shareFlour(int[][] calDough, int r, int c) {
		for(int i=0; i<4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			
			if(!isValidPoint(nr, nc) || dough[nr][nc] == 0) continue;
			
			int val = dough[r][c] - dough[nr][nc];
			if(val < 5) continue;
			int d = val / 5;
			
			calDough[r][c] -= d;
			calDough[nr][nc] += d;
		}
	}
	
	static void fold() {
		dough = new int[N][N];
		for(int i=0; i<N/2; i++) {
			dough[N-2][i] = flour[N/2-1-i];
		}
		
		for(int i=0; i<N/2; i++) {
			dough[N-1][i] = flour[i+N/2];
		}
		
		int[][] copyDough = new int[N][N];
		
		for(int i=0; i<2; i++) {
			for(int j=0; j<N/4; j++) {
				copyDough[i][j] = dough[N-1-i][N/4-1-j];
			}
		}
		
		for(int i=2; i<4; i++) {
			for(int j=0; j<N/4; j++) {
				copyDough[i][j] = dough[N-4+i][N/4 + j];
			}
		}
		
		for(int i=0; i<4; i++) {
			dough[N-4+i] = copyDough[i].clone();
		}
	}
	
	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}

}
