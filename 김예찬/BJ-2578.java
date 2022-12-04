package Boj.silver;

import java.util.Scanner;

public class Boj_2578 {
	static int[] arr = new int[25];
	static int[][] cheolsu = new int[5][5];
	static int a;
//	static int cnt;
	static int answer;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				cheolsu[r][c] = sc.nextInt();
			}
		}
		for (int i = 0; i < 25; i++) {
			arr[i] = sc.nextInt();
		}
		
		for (int i = 0; i < 25; i++) {
				a=arr[i];

				for (int r = 0; r < 5; r++) {
				for (int c = 0; c < 5; c++) {
					if(cheolsu[r][c]==a) {
						cheolsu[r][c]=0;
						answer++;
					}					
					if(bingo(r,c)>=3) {
						System.out.println(answer);
						return;
					}
				}
			}
		}
	}

	private static int bingo(int r, int c) {
		int check;
		int cnt=0;
		//가로빙고
		for(int i=0;i<5;i++) {
			check=0;
			for(int j=0;j<5;j++) {
				if(cheolsu[i][j]==0) {
					check++;
				}
			}
			if(check==5) {
				cnt++;
			}
		}
		
		//세로빙고

		for(int i=0;i<5;i++) {
			check=0;
			for(int j=0;j<5;j++) {
				if(cheolsu[j][i]==0) {
					check++;
				}
			}
			if(check==5) {
				cnt++;
			}
		}		
		//우하향빙고 0,0 1,1 2,2 3,3 4,4
		check=0;
		for(int i=0;i<5;i++) {
			if(cheolsu[i][i]==0) {
				check++;
			}
			if(check==5) {
				cnt++;
			}
		}
		//우상향빙고 4,0  3,1  2,2  1,3  0,4
		check=0;
		for(int i=0; i<5; i++) {
			if(cheolsu[4-i][i]==0) {
				check++;
			}
			if(check==5) {
				cnt++;
			}
		}
		return cnt;	
	}
	
}
