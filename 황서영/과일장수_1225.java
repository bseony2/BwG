import java.util.Arrays;
import java.util.Stack;

public class 과일장수_1225 {

    public static void main(String[] args) {
        int k = 4;
        int m = 3;
        int[] score = {4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2};

        int answer = 0;

        score = Arrays.stream(score).sorted().toArray();

        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < score.length ; i++) {
            st.push(score[i]);
        }

        int box = score.length / m;

        for (int i = 0; i < m * box ; i++){
            if((i+1)%m == 0){
              int sc = st.peek();
              answer = answer + sc * m * 1;
            }
            st.pop();
        }

        System.out.println(answer);
    }
}
