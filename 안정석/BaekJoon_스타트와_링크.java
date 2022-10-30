package day1030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon_스타트와_링크 {

	static int N; // N명
	static boolean result[]; // N명의 조합 판별
	static int map[][]; // 능력치 맵
	static int minValue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = new boolean[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(map[i][j]);
			}
//			System.out.println();
		}
		minValue = Integer.MAX_VALUE;
		comb(0, 0);
		System.out.println(minValue);

	}

	private static void comb(int target, int cnt) {
		if (cnt == N / 2) {
			int aTeam = 0;
			int bTeam = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i+1; j < N; j++) {
					if(result[i] && result[j]) {
						aTeam += map[i][j];
						aTeam += map[j][i];
					}
					else if(!result[i] && !result[j]){
						bTeam += map[i][j];
						bTeam += map[j][i];
					}
				}
			}
			int min = Math.abs(aTeam-bTeam);
			minValue = Math.min(min, minValue);
			
			return;
		}
		if (target == N) {
			return;
		}

		result[target] = true;
		comb(target + 1, cnt + 1);
		result[target] = false;
		comb(target + 1, cnt);
	}

}
