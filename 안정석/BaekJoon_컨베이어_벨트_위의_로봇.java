package day1020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_컨베이어_벨트_위의_로봇 {

	static ArrayList<Integer> belt;
	static ArrayList<Integer> robot;
	static int N, K;
	static int cnt;
	static int level;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		belt = new ArrayList<Integer>();
		robot = new ArrayList<Integer>();
		level = 0;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N * 2; i++) {
			belt.add(i, Integer.parseInt(st.nextToken()));
//			System.out.println(belt.get(i));
		}
		for (int i=0; i<N; i++) {
			robot.add(i, 0);
		}
		
		// 로봇을 우선 올린다(내구도-1) -> 벨트회전 -> 로봇 이동(내구도-1)
		while (true) {
			if (cnt >= K) {
				break;
			} else {
				cnt = 0;
			}

			
			
			// 벨트 회전
			int temp = belt.get(N * 2 - 1);
			belt.remove(N * 2 - 1);
			belt.add(0, temp);
			
			temp = robot.get(N-1);
			robot.remove(N-1);
			robot.add(0, temp);
			
			
			
			// 로봇 이동
			for(int i=0; i<N; i++) {
				if(robot.get(i) == 1 && robot.get(i+1) == 0) {
					robot.set(i, 0);
					robot.set(i+1, 1);
					belt.set(i, belt.get(i) - 1);
				}
				i = i+1;
			}
			

			// 로봇 이동
//			for (int i = 1; i < N; i++) {
//				if (!robot[i] && robot[i-1]) {
//					robot[i-1] = false;
//					if (i < N) {
//						robot[i] = true;
//						belt.set(i, belt.get(i) - 1);
//					}
//				}
//				i = i+1;
//			}

			// 로봇 추가
			if (belt.get(0) != 0) {
				robot.add(0, 1);
				belt.set(0, belt.get(0) - 1);
			}
			
			for(int i=0; i<N*2; i++) {
				if(belt.get(i) <= 0) {
					cnt++;
				}
			}
			
			level++;
		} // while 종료
		
		System.out.println(level);
		
	}
}
