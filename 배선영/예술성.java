import java.io.*;
import java.util.*;
public class 예술성 {

	static int N;
	static int[][] map, groupMap;
	static int score = 0;
	static ArrayList<Group> groupList = new ArrayList<>();
	static int[] dr = new int[] {-1, 0, 1, 0};
	static int[] dc = new int[] {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		init();
		
		simulate();
		
		System.out.println(score);
	}
	
	static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void simulate() {
		getArtScore();
		
		for(int i=0; i<3; i++) {
			spin();
			
			getArtScore();
		}
		
	}
	
	static void getArtScore() {
		setGroup();
		
		score += getScore();
	}
	
	static void setGroup() {
		groupList.clear();
		groupMap = new int[N][N];
		boolean[][] isVisited = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(!isVisited[i][j]) {
					isVisited[i][j] = true;
					Group group = makeGroup(isVisited, i, j);
					groupList.add(group);
				}
			}
		}
	}
	
	static Group makeGroup(boolean[][] isVisited, int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		queue.add(new int[] {r, c});
		int id = groupList.size();
		int amt = 0;
		int val = map[r][c];
		Group group = new Group(id, amt, val);
		
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			group.unit.add(point);
			int cr = point[0];
			int cc = point[1];
			group.amt += 1;
			groupMap[cr][cc] = id;
			
			for(int i=0; i<4; i++) {
				int nr = cr + dr[i];
				int nc = cc + dc[i];
				
				if(!isValidPoint(nr, nc) || isVisited[nr][nc] || map[nr][nc] != val) continue;
				
				isVisited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
		
		return group;
	}
	
	static int getScore() {
		int result = 0;
		for(int i=0; i<groupList.size()-1; i++) {
			for(int j=i+1; j<groupList.size(); j++) {
				int numOfLine = getLine(i, j);
				Group a = groupList.get(i);;
				Group b = groupList.get(j);
				
				int sum = (a.amt + b.amt) * a.val * b.val * numOfLine;
				result += sum;
			}
		}
		return result;
	}
	
	static int getLine(int x, int y) {
		int amt = 0;
		
		for(int[] point : groupList.get(x).unit) {
			for(int i=0; i<4; i++) {
				int nr = point[0] + dr[i];
				int nc = point[1] + dc[i];
				
				if(!isValidPoint(nr, nc) || groupMap[nr][nc] != y) continue;
				
				amt += 1;
			}
		}
		
		return amt;
	}
	
	static void spin() {
		int[][] copyMap = new int[N][N];
		for(int i=0; i<N; i++) {
			copyMap[N/2][i] = map[i][N/2];
		}
		for(int i=0; i<N; i++) {
			copyMap[i][N/2] = map[N/2][N-1-i];
		}
		
		spinCopy(copyMap, 0, 0);
		spinCopy(copyMap, 0, N/2+1);
		spinCopy(copyMap, N/2+1, 0);
		spinCopy(copyMap, N/2+1, N/2+1);
		
		map = copyMap;
	}
	
	static void spinCopy(int[][] copyMap, int r, int c) {
		for(int i=0; i<N/2; i++) {
			for(int j=0; j<N/2; j++) {
				copyMap[r+j][c+N/2-1-i] = map[r+i][c+j];
			}
		}
	}

	static boolean isValidPoint(int r, int c) {
		return 0<=r && r<N && 0<=c && c<N;
	}
	
	static class Group {
		int id, amt, val;
		ArrayList<int[]> unit;
		
		public Group(int id, int amt, int val) {
			this.id = id;
			this.amt = amt;
			this.val = val;
			unit = new ArrayList<>();
		}
	}
}