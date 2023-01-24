package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B11098_챌시를_도와줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] rsltArr = new String[n];

        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(br.readLine());
            String[][] arr = new String[p][2];

            int cost = 0;
            String player = null;

            for (int j = 0; j < p; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                arr[j][0] = st.nextToken();
                arr[j][1] = st.nextToken();

                if ((cost < Integer.parseInt(arr[j][0]))) {
                    cost = Integer.parseInt(arr[j][0]);
                    player = arr[j][1];
                }
            }
            rsltArr[i] = player;
        }

        for (int i = 0; i < rsltArr.length; i++) {
            System.out.println(rsltArr[i]);
        }
    }
}
