package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * 다시...
 */
public class B9375_패션왕신혜빈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map;
        String[] key;
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int testCase = Integer.parseInt(br.readLine());
        int cnt;

        for (int i = 0; i < testCase; i++) {
            int n = Integer.parseInt(br.readLine());
            map = new HashMap<>();

            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                cnt = st.countTokens();
                key = new String[cnt];

                for (int k = 0; k < cnt; k++) {
                    key[k] = st.nextToken();
                }

                if (map.get(key[cnt - 1]) != null) {
                    map.put(key[cnt - 1], map.get(key[cnt - 1]) + 1);
                } else {
                    map.put(key[cnt - 1], 1);
                }
            }

            int rslt = 1;
            Iterator<String> keys = map.keySet().iterator();
            while (keys.hasNext()) {
                String k = keys.next();
                rslt *= map.get(k) + 1;
            }
            sb.append(rslt - 1).append('\n');
        }
        System.out.println(sb);
    }
}
