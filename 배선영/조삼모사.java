import java.io.*;
import java.util.*;
import java.lang.Math;
public class 조삼모사 {
	static int[][] map;
	static int N, total = 0;
	static int ans = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		init();
		
		dfs(0, 0, new boolean[N]);
		
		System.out.println(ans);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				int value = Integer.parseInt(st.nextToken());
				map[i][j] = value;
			}
		}
	}
	
	static void dfs(int depth, int start, boolean[] isVisited) {
		if(depth == N/2) {
			int morning = getTotalHardness(isVisited, true);
			int night = getTotalHardness(isVisited, false);
			
			int result = Math.abs(morning-night);
			ans = ans < result ? ans : result;
			return;
		}
		
		for(int i=start; i<N; i++) {
			isVisited[i] = true;
			dfs(depth+1, i+1, isVisited);
			isVisited[i] = false;
		}
	}
	
	static int getTotalHardness(boolean[] isVisited, boolean isMorning) {
		int result = 0;
		for(int i=0; i<isVisited.length; i++) {
			if(isVisited[i] == isMorning) {
				result += getWorkHardness(i, isVisited, isMorning);
			}
		}
		return result;
	}
	
	static int getWorkHardness(int index, boolean[] isVisited, boolean isMorning) {
		int result = 0;
		for(int i=index+1; i<isVisited.length; i++) {
			if(isVisited[i] == isMorning) {
				result += map[index][i] + map[i][index];
			}
		}
		
		return result;
	}

}