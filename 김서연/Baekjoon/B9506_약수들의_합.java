package baekjoon_automata_basic;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class B9506_약수들의_합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        while (true) {
            int num = Integer.parseInt(br.readLine());

            if (num == -1) {
                bw.write(String.valueOf(result));
                bw.flush();
                bw.close();
                break;

            } else {
                int sum = 0;
                ArrayList<Integer> gcd = new ArrayList<>();

                for (int i = 1; i < num; i++) {
                    if (num % i == 0) {
                        sum += i;
                        gcd.add(i);
                    }
                }
                if (sum == num) {
                    StringBuilder numRslt = new StringBuilder(num + " = 1");

                    Iterator<Integer> it = gcd.iterator();
                    while (it.hasNext()) {
                        int next = it.next();
                        if (next == 1) continue;
                        numRslt.append(" + " + next);
                    }
                    result.append(numRslt + "\n");

                } else if (sum != num){
                    result.append(num + " is NOT perfect.\n");
                }
            }
        }
    }
}
