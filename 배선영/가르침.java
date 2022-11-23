import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.stream.Stream;
import java.util.Arrays;
public class 가르침 {
    static int N;
    static int K;
    static boolean[] isVisited = new boolean[26];
    static String[] words;
    static int answer = 0;
    public static void main(String[] args) throws IOException { //acitn
        
        init();

        dfs(0, 0);

        System.out.println(answer);
        

    }

    static void dfs(int start, int depth) {
        if(depth == K) {
            int result = 0;
            for(int i=0; i<words.length; i++) {
                if(teach(words[i])) {
                    result++;
                }
            }
            answer = answer > result ? answer : result;
            return;
        }

        for(int i=start; i<isVisited.length; i++) {
            if(!isVisited[i]) {
                isVisited[i] = true;
                dfs(i + 1, depth + 1);
                isVisited[i] = false;
            }
        }
    }

    static boolean teach(String word) {
        for(char c : word.toCharArray()) {
            if(!isVisited[c - 'a']) return false;
        }
        return true;
    }

    static void init() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력값 받기
        String[] firstLine = br.readLine().split(" ");
        N = Integer.parseInt(firstLine[0]);
        K = Integer.parseInt(firstLine[1]) - 5;
        words = new String[N];
        for(int i=0; i<N; i++) {
            String input = br.readLine();
            words[i] = input.substring(4, input.length() - 4);
        }

        isVisited['a' - 'a'] = true;
        isVisited['c' - 'a'] = true;
        isVisited['i' - 'a'] = true;
        isVisited['n' - 'a'] = true;
        isVisited['t' - 'a'] = true;
    }
}