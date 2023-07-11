import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class 촌수계산 {
    static boolean[] isVisited;
    static int n;
    static int m;
    static int person1;
    static int person2;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Integer>> family;
    static int[] result;
    static Queue<Integer> queue;
    public static void main(String[] args) throws IOException {
        
        inputInitialize();

        graphtInit();

        search();

        print();
    }

    static void inputInitialize() throws IOException {
        n = Integer.parseInt(br.readLine());
        String[] persons = br.readLine().split(" ");
        person1 = Integer.parseInt(persons[0]);
        person2 = Integer.parseInt(persons[1]);
        m = Integer.parseInt(br.readLine());
        isVisited = new boolean[n+1];
        result = new int[n+1];
    }

    static void graphtInit() throws IOException {
        family = new ArrayList<ArrayList<Integer>>();
        for(int i=0; i<n+1; i++) {
            family.add(new ArrayList<Integer>());
        }
        for(int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int inputPerson1 = Integer.parseInt(st.nextToken());
            int inputPerson2 = Integer.parseInt(st.nextToken());

            family.get(inputPerson1).add(inputPerson2);
            family.get(inputPerson2).add(inputPerson1);
        }
    }

    static void search() {
        queue = new LinkedList<Integer>();
        queue.add(person1);

        while(!queue.isEmpty()) {
            int currentPerson = queue.poll();
            if(isVisited[currentPerson]) {
                continue;
            }
            isVisited[currentPerson] = true;

            setRelation(currentPerson);
        }
    }

    static void setRelation(int person) {
        for(int nextPerson : family.get(person)) {
            if(isVisited[nextPerson]) {
                continue;
            }

            result[nextPerson] = result[person] + 1;
            queue.add(nextPerson);
        }
    }

    static void print() {
        System.out.println(result[person2] == 0 ? -1 : result[person2]);
    }
}
