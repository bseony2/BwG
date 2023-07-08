import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.Arrays;

public class 파티 {
    static ArrayList<ArrayList<int[]>> villageList;
    static ArrayList<ArrayList<int[]>> reverseVillageList;
    static int[] distance;
    static PriorityQueue<int[]> queue;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int M;
    static int X;
    static boolean[] isVisited;
    public static void main(String[] args) throws IOException {
        
        getInput();

        graphGenerate();

        initialize(villageList);

        dijkstra(villageList);

        int[] firstDist = new int[N+1];
        System.arraycopy(distance, 0, firstDist, 0, firstDist.length);

        initialize(reverseVillageList);

        dijkstra(reverseVillageList);

        int maxDistance = Integer.MIN_VALUE;
        for(int i=1; i<N+1; i++) {
            maxDistance = maxDistance > firstDist[i] + distance[i] ? maxDistance : firstDist[i] + distance[i];
        }
        System.out.println(maxDistance);
    }

    static void getInput() throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        villageList = new ArrayList<ArrayList<int[]>>();
        for(int i=0; i<N+1; i++) {
            villageList.add(new ArrayList<int[]>());
        }

        reverseVillageList = new ArrayList<ArrayList<int[]>>();
        for(int i=0; i<N+1; i++) {
            reverseVillageList.add(new ArrayList<int[]>());
        }
    }

    static void graphGenerate() throws IOException {
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            villageList.get(start).add(new int[]{end, time});
            reverseVillageList.get(end).add(new int[]{start, time});
        }
    }

    static void initialize(ArrayList<ArrayList<int[]>> selectedVillageList) {
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[X] = 0;
        isVisited = new boolean[N+1];
        queue = new PriorityQueue<int[]>((a, b) -> a[1] - b[1]);
    }

    static void dijkstra(ArrayList<ArrayList<int[]>> currentVillageList) {
        queue.add(new int[]{X, 0});

        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentVillage = current[0];
            int villageDistance = current[1];

            if(isFinishVillage(currentVillage)) {
                continue;
            }

            setLinkedVillageDistance(currentVillageList, currentVillage, villageDistance);
        }
    }

    static void setLinkedVillageDistance(ArrayList<ArrayList<int[]>> currentVillageList, int currentVillage, int villageDistance) {
        isVisited[currentVillage] = true;

        for(int[] next : currentVillageList.get(currentVillage)) {
            int nextVillage = next[0];
            int nextDistance = next[1];

            if(isFinishVillage(nextVillage)) {
                continue;
            }

            int newDistance = villageDistance + nextDistance;

            if(distance[nextVillage] > newDistance) {
                setNewDistance(nextVillage, newDistance);
            }
        }
    }

    static boolean isFinishVillage(int village) {
        return isVisited[village];
    }

    static void setNewDistance(int village, int newDistance) {
        distance[village] = newDistance;
        queue.add(new int[]{village, newDistance});
    }
}
