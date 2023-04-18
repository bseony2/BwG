package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_6808 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int T;
	static boolean[] allCard = new boolean[18];
	static boolean[] check;
	static int[] player1 = new int[9];
	static int[] player2 = new int[9];
	static int answer,cnt,draw;
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			answer = 0;
			cnt = 0;
			draw = 0;
			check = new boolean[9];
			allCard = new boolean[19];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<9; i++) {
				player1[i]=Integer.parseInt(st.nextToken());
				allCard[player1[i]]=true; 
			} // 규영이한테카드를 집어넣자. 카드는 중복이 안되므로 불린형배열 올카드에 트루로 바꿔주자
			int tmp=0;
			for(int i=1; i<=18; i++) {
				if(!allCard[i])
					player2[tmp++]=i; // 남은 카드를 인영이한테 넣어줌 
			}
			permutation(0,0,0);
			sb.append("#").append(tc).append(" ").append(answer).append(" ").append(cnt-answer-draw).append("\n");
		}
		System.out.println(sb);
	}
	public static void permutation(int round,int sum1, int sum2) {
		if(round==9) {   
			cnt++; 
			if(sum1 > sum2) answer++;  
			if(sum1 == sum2) draw++;  
			return;
		}
		for(int i=0; i<9; i++) { 
			if(!check[i]) {
				check[i]=true;
				if(player1[round]>player2[i]) {
					permutation(round+1,sum1+player1[round]+player2[i],sum2);
				}else {
					permutation(round+1,sum1,sum2+player1[round]+player2[i]);
				}
				check[i]=false;
			}
		}
	}

}