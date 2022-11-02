package w2;

import java.io.*;

public class p1769 {

    static int ans;
    static String x;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int y = 0;

        x = br.readLine();
        // 1의 자리수가 될 때 까지.
        while(x.length() >= 2) {
           ans++;
           y = 0;
           for(char c : x.toCharArray()) {
               y += (c - '0');
           }
           x = Integer.toString(y);
        }
        bw.write(ans+ "\n" + (Integer.parseInt(x) % 3 == 0 ? "YES" : "NO"));
        bw.flush();
        bw.close();
    }
}
