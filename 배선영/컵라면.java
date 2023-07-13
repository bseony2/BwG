// import java.io.IOException;
// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.lang.Comparable;
// import java.util.PriorityQueue;
// import java.util.StringTokenizer;

// class Work implements Comparable<Work>{
//     int deadline;
//     int reward;

//     public Work(int deadline, int reward){
//         this.deadline = deadline;
//         this.reward = reward;
//     }

//     @Override
//     public int compareTo(Work work) {
//         if(this.deadline != work.deadline ) {
//             return this.deadline - work.deadline;
//         }
//         else {
//             return work.reward - this.reward;
//         }
//     }
// }

// public class 컵라면 {
//     static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//     static int N;
//     static PriorityQueue<Work> pq = new PriorityQueue<>();
//     public static void main(String...args) throws IOException{
//         getInput();

//         int answer = count();

//         System.out.println(answer);

//     }

//     static void getInput() throws IOException {
//         N = Integer.parseInt(br.readLine());
//         for(int i=0; i<N; i++) {
//             StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//             int deadline = Integer.parseInt(st.nextToken());
//             int reward = Integer.parseInt(st.nextToken());
//             pq.add(new Work(deadline, reward));
//         }
//     }

//     static int count() {
//         int answer = 0;
//         int time = 1;

//         while(!pq.isEmpty()) {
//             Work work = pq.poll();
//             if(work.deadline >= time) {
//                 answer += work.reward;
//                 time += 1;
//             }
//         }

//         return answer;
//     }
// }


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Schedule implements Comparable<Schedule>{
    int deadLine;
    int cupNoodle;

    public Schedule(int deadLine, int cupNoodle)
    {
        this.deadLine = deadLine;
        this.cupNoodle = cupNoodle;
    }

    @Override
    public int compareTo(Schedule o) {
        return Integer.compare(this.deadLine, o.deadLine);
    }
    
}

public class 컵라면 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        Schedule[] scheduleArray;
        st = new StringTokenizer(br.readLine());
        int N = parseInt(st.nextToken());
        scheduleArray = new Schedule[N];
        for(int i=0; i<N; i++)
        {
            st = new StringTokenizer(br.readLine());
            scheduleArray[i] = new Schedule(parseInt(st.nextToken()), parseInt(st.nextToken()));
        }
        Arrays.sort(scheduleArray);

        for(int i=0; i<N; i++)
        {
            pq.add(scheduleArray[i].cupNoodle);
            if(pq.size()>scheduleArray[i].deadLine)
            {
                pq.poll();
            }
        }
        int answer= 0;
        while(!pq.isEmpty())
        {
            answer += pq.poll();
        }
        System.out.println(answer);
    }

    public static int parseInt(String st)
    {
        return Integer.parseInt(st);
    }
}