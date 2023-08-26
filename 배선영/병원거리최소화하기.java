import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Arrays;
import java.lang.Math;

public class 병원거리최소화하기 {
    static int N, M;
    static int[][] distance;
    static int[] dr = new int[]{-1, 0, 1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int ans = Integer.MAX_VALUE;
    static ArrayList<int[]> personList = new ArrayList<>();
    static ArrayList<Hospital> hospitalList = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        init();

        comb(0, 0, new boolean[hospitalList.size()]);

        System.out.println(ans);
    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if(value == 1) {
                    personList.add(new int[]{i, j});
                } else if(value == 2) {
                    hospitalList.add(new Hospital(i, j));
                }
            }
        }
    }
    
    static void comb(int depth, int start, boolean[] isVisited) {
        if(depth == M) {
            getAns(isVisited);
            return;
        }

        for(int i=start; i<isVisited.length; i++) {
            isVisited[i] = true;
            comb(depth+1, i+1, isVisited);
            isVisited[i] = false;
        }
    }

    static void getAns(boolean[] isVisited) {
        int[] personDistance = new int[personList.size()];
        Arrays.fill(personDistance, Integer.MAX_VALUE);

        for(int i=0; i<isVisited.length; i++) {
            if(!isVisited[i]) continue;

            Hospital hospital = hospitalList.get(i);

            for(int j=0; j<personList.size(); j++) {
                int[] person = personList.get(j);
                int r = person[0];
                int c = person[1];

                int dis = Math.abs(hospital.r - r) + Math.abs(hospital.c - c);
                personDistance[j] = Math.min(personDistance[j], dis);
            }
        }

        int sum = 0;
        for(int dis : personDistance) {
            sum += dis;
        }

        ans = ans > sum ? sum : ans;
    }

    static boolean isValidPoint(int r, int c) {
        return 0<=r && r<N && 0<=c && c<N;
    }

    static class Hospital {
        int r, c;
        ArrayList<Integer> distanceList;

        public Hospital(int r, int c) {
            this.r = r;
            this.c = c;
            distanceList = new ArrayList<>();
        }
    }
}
