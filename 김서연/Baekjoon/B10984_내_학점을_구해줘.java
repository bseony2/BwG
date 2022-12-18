package baekjoon_automata_basic;

import java.io.*;
import java.util.StringTokenizer;

public class B10984_내_학점을_구해줘 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder rslt = new StringBuilder();

        for (int i = 0; i < T; i++) {
            int TNum = Integer.parseInt(br.readLine());
            int total = 0;
            double gpa = 0;

            for (int j = 0; j < TNum; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                double b = Double.parseDouble(st.nextToken());

                total += a;
                gpa += b * a;
            }
            rslt.append(total + " "  + Math.round(gpa / total * 10) / 10.0 + "\n");
        }
        bw.write(rslt.toString());
        bw.flush();
        bw.close();
    }
}
