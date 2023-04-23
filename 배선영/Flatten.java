import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

class Flatten {
    static int n;
    static int[]height;
    static int left;
    static int right;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=10; tc++) {
            n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            height = new int[101];
            right = Integer.MIN_VALUE;
            left = Integer.MAX_VALUE;
            for(int i=0; i<100; i++) {
                int value = Integer.parseInt(st.nextToken());
                height[value] += 1;
                if(value < left) left = value;
                if(value > right) right = value;
            }

            int result = dump();
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
    // 투 포인터 방식의 풀이
    static int dump() {

        for(int i=0; i<n; i++) {
            if(right == left || right < left) break;

            height[right]--;
            height[right - 1]++;

            height[left + 1]++;
            height[left]--;

            if(height[right] == 0) right--;
            if(height[left] == 0) left++;
            
        }
        return right - left;
    }    
}