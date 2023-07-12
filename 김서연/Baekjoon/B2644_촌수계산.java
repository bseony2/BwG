package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B2644_촌수계산 {
    static int n; // 전체 사람의 수
    static int m; // 부모 자식 관계의 수
    static int findP1;
    static int findP2;
    static boolean[] isVisited;
    static int[] arrRslt;
    static int result = -1;
//    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); // 전체인원

        StringTokenizer st = new StringTokenizer(br.readLine());
        findP1 = Integer.parseInt(st.nextToken());
        findP2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine()); // 관계의 수
        isVisited = new boolean[n + 1];
        arrRslt = new int[n + 1];

        ArrayList<ArrayList<Integer>> fam = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            fam.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int relP1 = Integer.parseInt(st.nextToken());
            int relP2 = Integer.parseInt(st.nextToken());

            // 양방향 인접리스트
            fam.get(relP1).add(relP2);
            fam.get(relP2).add(relP1);
        }

        DFS(fam, isVisited, findP1, 0);

        System.out.println(result);
        br.close();
    }

    static void DFS(ArrayList<ArrayList<Integer>> fam, boolean[] isVisited, int findP1, int rsltCnt) {
        isVisited[findP1] = true;

        for (int idx : fam.get(findP1)) {
            if (!isVisited[idx]) {
                if (idx == findP2) {
                    result = rsltCnt + 1;
                    return;
                }

                DFS(fam, isVisited, idx, rsltCnt + 1);
            }
        }
    }
}
