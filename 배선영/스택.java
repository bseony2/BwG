import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class 스택 {
    public static void main(String[] args) throws IOException {
        Stack<String> stack = new Stack<String>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String order = st.nextToken();
            String value = "";
            if("push".equals(order)) {
                value = st.nextToken();
            }

            switch(order) {
                case "push" :
                    stack.push(value);
                    break;

                case "pop" :
                    if(stack.size()> 0) {
                        System.out.println(stack.pop());
                    }
                    else {
                        System.out.println(-1);
                    }
                    break;

                case "size" :
                    System.out.println(stack.size());
                    break;

                case "empty" :
                    if(stack.size() == 0) System.out.println(1);
                    else System.out.println(0);
                    break;

                case "top" :
                    if(stack.size()> 0) {
                        System.out.println(stack.peek());
                    }
                    else {
                        System.out.println(-1);
                    }
                    break;
            }
        }
    }
}
