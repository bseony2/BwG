import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

public class 괄호의 값 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println(solution(br.readLine()));
    }

    static int solution(String str) {
        int answer = 0;
        int temp = 1;
        String print = "";
        Stack<Character> stack = new Stack<Character>();

        for(int i=0; i<str.length(); i++) {
            char currentVal = str.charAt(i);
            if(currentVal == '(' || currentVal == '[') {
                temp *= currentVal == '(' ? 2 : 3;
                stack.push(currentVal);
            } else { //값이 ')'나 ']'
                if(stack.isEmpty())
                    return 0;

                else if(currentVal == ')') {
                    if(stack.peek() == '[') {
                        return 0;
                    } else {
                        if(str.charAt(i-1) == '(') {
                            answer += temp;
                        }
                        temp /= 2;
                    }
                    stack.pop();
                } else { // currentVal == ']'
                    if(stack.peek() == '(') {
                        return 0;
                    } else {
                        if(str.charAt(i-1) == '[') {
                            answer += temp;
                        }
                        temp /= 3;
                    }
                    stack.pop();
                }
            }
            print = stack.toString().substring(1, stack.toString().length() - 1);
        }

        return stack.isEmpty() ? answer : 0;
    }
    
}