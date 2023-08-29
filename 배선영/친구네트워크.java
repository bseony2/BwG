import java.util.*;
import java.io.*;

public class 친구네트워크 {
	static int[] parent;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++) {
			exec();
		}
		
		bw.write(sb.toString());
		bw.flush();
		br.close();
		bw.close();
	}
	
	static void exec() throws IOException {
		int F = Integer.parseInt(br.readLine());
		parent = new int[F*2];
		Arrays.fill(parent, -1);
		HashMap<String, Integer> map = new HashMap<>();
		int index = 0;
		
		
		for(int i=0; i<F; i++) {
			String[] names = br.readLine().split(" ");
			
			for(int j=0; j<2; j++) {
				if(!map.containsKey(names[j])) {
					map.put(names[j], index++);
				}
			}
			
			int firstIndex = map.get(names[0]);
			int secondIndex = map.get(names[1]);
			
			sb.append(union(firstIndex, secondIndex) + "\n");
		}
	}
	
	static int find(int x) {
		if(parent[x] < 0) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static int union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x != y) {
			if(parent[x] < parent[y]) {
				parent[x] += parent[y];
				parent[y] = x;
			}
			else {
				parent[y] += parent[x];
				parent[x] = y;
			}
		}
		
		return parent[find(x)] * -1;
	}
}