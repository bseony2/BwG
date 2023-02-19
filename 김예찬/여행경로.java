import java.util.*;

class Solution {
    static boolean[] visited;
    static ArrayList<String> answers;
    public String[] solution(String[][] tickets) {
        
        visited = new boolean[tickets.length];
        answers = new ArrayList<String>();
        
        int cnt = 0;
        dfs(cnt,"ICN","ICN",tickets);
        Collections.sort(answers);
        System.out.print(answers);
        String[] answer = answers.get(0).split(" ");
        
        return answer;
            
    }
    
    private void dfs(int cnt, String present, String answer, String[][]tickets){
        
        if(cnt == tickets.length){
            answers.add(answer);
            return;
        }
        
        for(int i=0; i<tickets.length;i++){
            if(!visited[i] && tickets[i][0].equals(present)){
                visited[i]=true;
                dfs(cnt+1,tickets[i][1],answer+" "+tickets[i][1],tickets);
                visited[i] = false;
            }
        }
    }
}