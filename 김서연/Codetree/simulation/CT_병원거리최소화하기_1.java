package baekjoon.simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CT_병원거리최소화하기_1 {
    static int rslt = Integer.MAX_VALUE; // 결과값
    static int n, m;
    static int[][] arr;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static ArrayList<int[]> peopleList = new ArrayList<>();
    static ArrayList<Hospital> hospitalList = new ArrayList<Hospital>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) peopleList.add(new int[]{i, j}); // 사람위치 좌표
                else if (val == 2) hospitalList.add(new Hospital(i, j)); // 병원위치 좌표
            }
        }

        combination(0, 0, new boolean[hospitalList.size()]);

        System.out.println(rslt);
    }

    static void combination(int depth, int start, boolean[] isVisited) {
        if (depth == m) {
            getTotMinDistance(isVisited);
            return;
        }

        for (int i = start; i < isVisited.length; i++) {
            isVisited[i] = true;
            combination(depth + 1, i + 1, isVisited);
            isVisited[i] = false;
        }
    }

    private static void getTotMinDistance(boolean[] isVisited) {
        int[] personDistance = new int[peopleList.size()]; // 한 사람당 모든 병원에 대한 거리의 총 합 배열 (per person)
        Arrays.fill(personDistance, Integer.MAX_VALUE); // 최소값 구하기 초기화

        for (int i = 0; i < isVisited.length; i++) { // 선택한 병원 순회
            if (!isVisited[i]) continue; // 방문한 병원만 체크

            Hospital hospital = hospitalList.get(i);

            for (int j = 0; j < peopleList.size(); j++) {
                int[] person = peopleList.get(j);
                int personRow = person[0];
                int personCol = person[1];

                int dis = Math.abs(hospital.r - personRow) + Math.abs(hospital.c - personCol);
                personDistance[j] = Math.min(personDistance[j], dis); // person to hospital min value
            }
        }

        int sum = Arrays.stream(personDistance).sum();
        rslt = rslt > sum ? sum : rslt;
    }

    // 유효범위 체크
//    static boolean isValidPoint(int r, int c) {
//       return r >= 0 && r < n && c >= 0 && c < n;
//    }

    // 병원 좌표
    static class Hospital {
        int r, c; // 행, 열

        public Hospital(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
