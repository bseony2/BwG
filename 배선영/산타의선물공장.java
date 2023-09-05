import java.util.*;
import java.io.*;

public class 산타의선물공장 {

	static int Q, N, M;
	static boolean[] beltState;
	static int[] head;
	static HashMap<Integer, Node> map = new HashMap<>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		Q = Integer.parseInt(br.readLine());
		while(Q-->0) {
			runFactory();
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void runFactory() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int order = Integer.parseInt(st.nextToken());
		
		if(order == 100) {
			generateFactory(st);
		}
		else if(order == 200) {
			int weight = Integer.parseInt(st.nextToken());
			getDown(weight);
		}
		else if(order == 300) {
			int id = Integer.parseInt(st.nextToken());
			removeGift(id);
		}
		else if(order == 400) {
			int id = Integer.parseInt(st.nextToken());
			checkGift(id);
		}
		else if(order == 500) {
			int beltNum = Integer.parseInt(st.nextToken());
			beltBroken(beltNum-1);
		}
	}
	
	static void generateFactory(StringTokenizer st) throws IOException {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		beltState = new boolean[M];
		Arrays.fill(beltState, true);
		head = new int[M];
		
		for(int i=0; i<N; i++) {
			int beltNum = i/(N/M);
			int id = Integer.parseInt(st.nextToken());
			Node node = new Node(id, beltNum);
			map.put(id, node);
			if(i % (N/M) == 0) {
				head[beltNum] = id;
			}
			else if(i % (N/M) != 0) {
				insertForward(node, map.get(head[beltNum]));
			}
		}
		
		for(int i=0; i<M; i++) {
			int index = 0;
			Node node = map.get(head[i]);
			while(index < N/M) {
				int weight = Integer.parseInt(st.nextToken());
				node.w = weight;
				node = node.next;
				index += 1;
			}
		}
	}
	
	static void getDown(int limit) {
		int ans = 0;
		for(int i=0; i<M; i++) {
			if(head[i] == -1) continue;
			Node node = map.get(head[i]);
			if(node.w <= limit) {
				ans += node.w;
				head[i] = node.next.id;
				removeNode(node);
			}
			else if(node.w > limit) {
				head[i] = node.next.id;
			}
		}
		sb.append(ans).append("\n");
	}
	
	static void removeGift(int id) {
		Node node = map.get(id);
		if(node != null) {
			sb.append(id).append("\n");
			removeNode(node);
		}
		else if(node == null) {
			sb.append(-1).append("\n");
		}
	}
	
	static void checkGift(int id) {
		Node node = map.get(id);
		if(node != null) {
			head[node.belt] = id;
			sb.append(node.belt+1).append("\n");
		}
		else if(node == null) {
			sb.append(-1).append("\n");
		}
	}
	
	static void beltBroken(int num) {
		if(beltState[num]) {
			sb.append(num+1).append("\n");
			beltState[num] = false;
		}
		else if(!beltState[num]) {
			sb.append(-1).append("\n");
		}
		int nextBelt = num;
		while(!beltState[nextBelt]) {
			nextBelt = (nextBelt + 1) % M;
		}
		
		if(head[num] == -1) return;
		
		Node node = map.get(head[num]);
		Node tail = node.pre;
		head[num] = -1;
		
		if(head[nextBelt] == -1) {
			head[nextBelt] = node.id;
		}
		else if(head[nextBelt] != -1) {
			Node nextHeadNode = map.get(head[nextBelt]);
			Node nextTailNode = nextHeadNode.pre;
			
			nextHeadNode.pre = tail;
			tail.next = nextHeadNode;
			
			nextTailNode.next = node;
			node.pre = nextTailNode;
		}
		
		while(node.belt == num) {
			node.belt = nextBelt;
			node = node.next;
		}
	}

	static class Node {
		int id, w, belt;
		Node pre, next;
		
		public Node(int id, int beltNum) {
			this.id = id;
			this.belt = beltNum;
			pre = this;
			next = this;
		}
	}
	
	static void insertForward(Node node, Node target) {
		Node pre = target.pre;
		pre.next = node;
		node.pre = pre;
		
		target.pre = node;
		node.next = target;
	}
	
	static void insertBack(Node node, Node target) {
		Node next = target.next;
		
		next.pre = node;
		node.next = next;
		
		target.next = node;
		node.pre = target;
	}
	
	static void removeNode(Node node) {
		map.remove(node.id);
		
		if(node.pre.id == node.id && node.next.id == node.id) {
			head[node.belt] = -1;
			return;
		}
		
		Node pre = node.pre;
		Node next = node.next;
		
		pre.next = next;
		next.pre = pre;
		
		if(head[node.belt] == node.id) {
			head[node.belt] = next.id;
		}
	}
}
