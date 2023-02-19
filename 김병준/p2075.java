import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class p2075 {

    private static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        List<Integer> arrs = new ArrayList<>();

        for(int i = 0; i < N; i++){
            String line[] = br.readLine().split(" ");
            for(String num : line){
                arrs.add(Integer.parseInt(num));
            }
        }

        Collections.sort(arrs, (a, b) -> (b - a));
        bw.write(arrs.get(N - 1) + "\n");

        bw.flush();
        bw.close();
    }
}
