import java.util.*;
class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        ArrayList<Point> list = new ArrayList<>();
        
        for(int i=0; i<priorities.length; i++){
            list.add(new Point(i,priorities[i]));
        }
        int cnt = 0;
        while(true){
            int max = 0;
            for(int i=0; i<list.size(); i++){
                int temp = list.get(i).weight;
                if(temp > max){
                    max = temp;
                }
            }
            if(list.get(0).weight == max){
                cnt++;
               
                if(list.get(0).index == location){
                    break;
                }
                list.remove(0);
            } else {
                list.add(new Point(list.get(0).index, list.get(0).weight));
                list.remove(0);
            }
        }
        answer = cnt;
        return answer;
    }
    static class Point {
        int index;
        int weight;
        
        public Point(int index, int weight){
            this.index = index;
            this.weight = weight;
        }
    }
}