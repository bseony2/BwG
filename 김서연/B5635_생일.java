package baekjoon_automata_basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class B5635_생일 {
    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[][] arr = new String[N][4]; // list가 아니라 2차원배열 좀 써..

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = st.nextToken();
            arr[i][1] = st.nextToken();
            arr[i][2] = st.nextToken();
            arr[i][3] = st.nextToken();
        }

        // arr 정렬, Comparator
        Arrays.sort(arr, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) { 
                if(o1[3].equals(o2[3])) { // Year
                    if(o1[2].equals(o2[2])) { // Month
                        return Integer.parseInt(o1[1]) - Integer.parseInt(o2[1]);
                    }
                    return Integer.parseInt(o1[2]) - Integer.parseInt(o2[2]);
                }
                return Integer.parseInt(o1[3]) - Integer.parseInt(o2[3]);
            }
        });
        // 나이와 sort정렬 역순
        System.out.println(arr[N - 1][0] + "\n" + arr[0][0]);
    }
}
