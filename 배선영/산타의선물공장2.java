import java.io.*;
import java.util.*;

public class 산타의선물공장2 {

	static int Q, N, M;
	static int[] heads, tails, size;
	static HashMap<Integer, Node> map = new HashMap<>();
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Q = Integer.parseInt(br.readLine());

		while(Q-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int order = Integer.parseInt(st.nextToken());
			
			if(order == 100) {
				generateFactory(st);
			}
			else if(order == 200) {
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				move(src, dst);
			}
			else if(order == 300) {
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				change(src, dst);
			}
			else if(order == 400) {
				int src = Integer.parseInt(st.nextToken());
				int dst = Integer.parseInt(st.nextToken());
				devide(src, dst);
			}
			else if(order == 500) {
				int id = Integer.parseInt(st.nextToken());
				getGiftInfo(id);
			}
			else if(order == 600) {
				int belt = Integer.parseInt(st.nextToken());
				getBeltInfo(belt);
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	static void generateFactory(StringTokenizer st) {
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		heads = new int[N+1];
		tails = new int[N+1];
		size = new int[N+1];
		
		for(int i=1; i<=M; i++) {
			int belt = Integer.parseInt(st.nextToken());
			Node node = new Node(i);
			map.put(i, node);
			size[belt] += 1;
			if(heads[belt] == 0) {
				heads[belt] = i;
				tails[belt] = i;
			}
			else if(heads[belt] != 0) {
				Node tail = map.get(tails[belt]);
				tail.next = node;
				node.pre = tail;
				tails[belt] = node.id;
			}
		}
	}
	
	static void move(int src, int des) {
		if(size[src] == 0) {
			sb.append(size[des]).append("\n");
			return;
		}
		
		Node srcTail = map.get(tails[src]);
		Node desHead = map.get(heads[des]);
		
		heads[des] = heads[src];
		heads[src] = 0;
		tails[src] = 0;
		if(desHead != null) {
			srcTail.next = desHead;
			desHead.pre = srcTail;
		}
		else if(desHead == null) {
			tails[des] = srcTail.id;
		}
		size[des] += size[src];
		size[src] = 0;
		
		sb.append(size[des]).append("\n");
	}
	
	static void change(int src, int des) {
		Node srcHead = extractHead(src);
		Node desHead = extractHead(des);
		
		if(srcHead != null) {
			inputNodetoHead(srcHead, des);
		}
		
		if(desHead != null) {
			inputNodetoHead(desHead, src);
		}
		
		sb.append(size[des]).append("\n");
	}
	
	static Node extractHead(int belt) {
		if(size[belt] == 0) return null;
		
		Node node = map.get(heads[belt]);
		size[belt] -= 1;
		if(size[belt] != 0) {
			node.next.pre = node.next;
			heads[belt] = node.next.id;
		}
		else if(size[belt] == 0) {
			heads[belt] = 0;
		}
		node.next = node;
		return node;
	}
	
	static void inputNodetoHead(Node node, int des) {
		node.pre = node;
		if(size[des] == 0) {
			node.next = node;
            tails[des] = node.id;
		}
		else if(size[des] > 0) {
			Node originHead = map.get(heads[des]);
			node.next = originHead;
			originHead.pre = node;
		}
        heads[des] = node.id;
		size[des] += 1;
	}
	
	static void devide(int src, int des) {
		if(size[src] < 2) {
			sb.append(size[des]).append("\n");
			return;
		}
		int num = size[src]/2;
		
		Node head = map.get(heads[src]);
		Node tail = head;
        size[src] -= num;
		size[des] += num;
		while(num-->1) {
			tail = tail.next;
		}
		heads[src] = tail.next.id;
		tail.next.pre = tail.next;
		
		Node desHead = map.get(heads[des]);
		heads[des] = head.id;
		
		if(desHead != null) {
			tail.next = desHead;
			desHead.pre = tail;
		}
		else if(desHead == null) {
			tails[des] = tail.id;
            tail.next = tail;
		}
		
		sb.append(size[des]).append("\n");
	}
	
	static void getGiftInfo(int id) {
		Node node = map.get(id);
		int a = node.pre == node ? -1 : node.pre.id;
		int b = node.next == node ? -1 : node.next.id;
		sb.append((b*2+a)).append("\n");
	}
	
	static void getBeltInfo(int belt) {
		int a = size[belt] == 0 ? -1 : heads[belt];
        int b = size[belt] == 0 ? -1 : tails[belt];
		int c = size[belt];
		sb.append((a + 2*b + 3*c)).append("\n");
	}

	static class Node {
		int id;
		Node pre, next;
		
		public Node(int id) {
			this.id = id;
			pre = this;
			next = this;
		}
	}
}