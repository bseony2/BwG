import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Comparable;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Work implements Comparable<Work>{
    int deadline;
    int reward;

    public Work(int deadline, int reward){
        this.deadline = deadline;
        this.reward = reward;
    }

    @Override
    public int compareTo(Work work) {
        if(this.deadline != work.deadline ) {
            return this.deadline - work.deadline;
        }
        else {
            return work.reward - this.reward;
        }
    }
}

public class 컵라면 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static PriorityQueue<Work> workList = new PriorityQueue<>();
    static PriorityQueue<Integer> selectedWorkReward = new PriorityQueue<>();
    public static void main(String...args) throws IOException{
        getInput();

        selectWork();

        int answer = calTotalReward();

        System.out.println(answer);

    }

    static void getInput() throws IOException {
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int deadline = Integer.parseInt(st.nextToken());
            int reward = Integer.parseInt(st.nextToken());
            workList.add(new Work(deadline, reward));
        }
    }

    static void selectWork() {
        while(!workList.isEmpty()) {
            Work work = workList.poll();
            selectedWorkReward.add(work.reward);
            if(selectedWorkReward.size() > work.deadline) {
                selectedWorkReward.poll();
            }
        }
    }

    static int calTotalReward() {
        int answer = 0;
        for(int reward : selectedWorkReward) {
            answer += reward;
        }

        return answer;
    }
}


