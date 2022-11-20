package w2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Info implements Comparable<Info> {
    int when;
    int how;
    Info(int when, int how){
        this.when = when;
        this.how = how;
    }

    @Override
    public int compareTo(Info o) {
        if(this.when == o.when){
            return this.how - o.how;
        } else{
            return this.when - o.when;
        }
    }
}

public class p14469 {
    static int N;
    static List<Info> infos;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        infos = new ArrayList<>(100);

        for(int i = 0 ; i < N; i++){
            int when = sc.nextInt();
            int how = sc.nextInt();
            infos.add(new Info(when, how));
        }
        Collections.sort(infos);

        for(int i = 0; i < N; i++) {
            int w = infos.get(i).when;
            int h = infos.get(i).how;
        }

        int ans = infos.get(0).how + infos.get(0).when;
        for(int i = 1; i < N; i++){
            int w = infos.get(i).when;
            int h = infos.get(i).how;
            ans = Math.max(ans, w) + h;
        }
        System.out.println(ans);
    }
}
