import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.lang.Math;

class 가운데를말해요 {

    static ArrayList<Integer> numbers = new ArrayList<Integer>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            inputData( Integer.parseInt(br.readLine()) );

            sb.append(getMidVal()).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void inputData(int n) {
        int left = 0;
        int right = numbers.size()-1;
        int mid;

        if(right == -1) {
            numbers.add(n);
            return;
        }

        while(left <= right) {
            mid = (left+right) / 2;

            if(n >= numbers.get(mid)) {
                left = mid+1;
            } else {
                right = mid-1;
            }
            mid = (left+right) / 2;
        }

        numbers.add(left, n);
    }

    static int getMidVal() {
        int answer = 0;

        int len = numbers.size();
        if(len % 2 == 1) {
            answer = numbers.get(len/2);
        }
        else {
            answer = Math.min(numbers.get(len/2-1), numbers.get(len/2));
        }

        return answer;
    }
}