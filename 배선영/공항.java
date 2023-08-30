import java.io.*;
import java.util.*;

public class 공항 {
	static int G, P; 
	static int[] gates;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		
		
		gates = new int[G+1];
		Arrays.fill(gates, -1);
		
		for(int i=0; i<P; i++) {
			int val = Integer.parseInt(br.readLine());
			int parent = find(val);
			if(parent == 0) {
				System.out.println(i);
				System.exit(0);
			}
			
			union(parent, parent-1);
		}
		
		System.out.println(P);
	}
	
	static int find(int x) {
		if(gates[x] < 0) return x;
		
		return gates[x] = find(gates[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		gates[y] += gates[x];
		gates[x] = y;
	}

}
