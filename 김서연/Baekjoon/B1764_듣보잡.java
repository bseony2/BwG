package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// A∩B
public class B1764_듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();
        List<String> rslt = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            // A,B 각각의 명단에 중복되는 이름없음 -> 첫 명단 바로 적재
            map.put(br.readLine(), 1);
        }

        for (int i = 0; i < M; i++) {
            String chkName = br.readLine();

            // 듣보잡인 경우의 값 = 2
            map.put(chkName, map.getOrDefault(chkName, 0) + 1);
            if (map.get(chkName) == 2) rslt.add(chkName);
        }

        Collections.sort(rslt); // 사전순 정렬
        System.out.println(rslt.size());
        Iterator<String> it = rslt.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
