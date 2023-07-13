package baekjoon.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2644_촌수계산2 {
    static int n; // 전체 사람의 수
    static int m; // 관계의 수
    static int findP1;// 찾을 부모
    static int findP2; // 찾을 부모
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayList<ArrayList<Integer>> fam;
    static boolean[] isVisited;
    static Queue<Integer> queue;
    static int[] rsltArr;
    static int result = -1; // 촌수 (-1 기본 초기화)

    public static void main(String[] args) throws IOException {
        inputInit();

        fam = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            fam.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int relP1 = Integer.parseInt(st.nextToken());
            int relP2 = Integer.parseInt(st.nextToken());

            fam.get(relP1).add(relP2);
            fam.get(relP2).add(relP1);
        }

        searchRel();

        System.out.println(rsltArr[findP2] == 0 ? -1 : rsltArr[findP2]);
    }

    static void inputInit() throws IOException {
        n = Integer.parseInt(br.readLine());
        isVisited = new boolean[n + 1];
        rsltArr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        findP1 = Integer.parseInt(st.nextToken());
        findP2 = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
    }

    static void searchRel() {
        queue = new LinkedList<>();
        queue.add(findP1);

        while (!queue.isEmpty()) {
            int currentP = queue.poll();
            if (isVisited[currentP]) continue;

            isVisited[currentP] = true;

            operationRel(currentP);
        }
    }

    static void operationRel(int person) {
        for (int nextPerson : fam.get(person)) {
            if (isVisited[nextPerson]) continue;

            rsltArr[nextPerson] = rsltArr[person] + 1;
            queue.add(nextPerson);
        }
    }
}
