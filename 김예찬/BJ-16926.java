package Boj.silver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.StringTokenizer;
 
public class Boj_16926 { 
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int arr[][];
	static int N,M,R;
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		
		N= Integer.parseInt(st.nextToken());
		M= Integer.parseInt(st.nextToken());
		R= Integer.parseInt(st.nextToken());
		
		arr= new int[N][M];
		
		for(int r=0;r<N;r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M;c++) {
				arr[r][c]= Integer.parseInt(st.nextToken());
				
			}
		}
		
//		for(int map[] : arr) {
//			System.out.println(Arrays.toString(map));
//		}
		
		for(int r=0; r<R;r++) {
			rotate();
		}
		for (int[] row : arr) {
			for (int v : row) {
				sb.append(v).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	

	private static void rotate() {
	
		int T = Math.min(N, M)/2;
		
		for(int t=0; t<T;t++) {
			
			int keep = arr[t][t];
			//스타트점은 킵에 가지고있다가 넣어주자.
			
			//윗줄을 왼쪽으로 이동시켜보자
			for(int c=t+1;c<M-t;c++) {
				arr[t][c-1]=arr[t][c];
			}
			
			//오른쪽줄을 위쪽으로 이동시켜보자 
			for(int r= t+1; r<N-t;r++) {
				arr[r-1][M-t-1]=arr[r][M-t-1];
			}
			
			//아랫 줄을 오른쪽으로 이동시키자.
			for(int c=M-1-1-t;c>=t;c--) {
				arr[N-t-1][c+1]=arr[N-t-1][c];
			}
			
			//왼쪽줄을 아래쪽 줄로 이동시켜보자.
			for(int r= N-1-1-t; r>=t;r--) {
				arr[r+1][t]=arr[r][t];
			}
			arr[t+1][t]=keep;
		}

	}
} 