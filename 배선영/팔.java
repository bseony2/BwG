import java.lang.Math;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class 팔 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        int answer = 0;

        if ((int)(Math.log10(L) + 1) != (int)(Math.log10(R) + 1)) { // 정수의 자리수가 다르면 결과는 0이다.
            System.out.println(answer);
            return;
        } else {
            String l = String.valueOf(L);
            String r = String.valueOf(R);

            for(int i=0; i<l.length(); i++) { // 제일 높은 자리수부터 비교하는데, 
                if(l.charAt(i) != r.charAt(i)) { 
                    break; // 각 자리수의 숫자가 다르면 비교중지
                } else if(l.charAt(i) == '8') { 
                    answer++; // 각 자리수의 수가 8로 같으면 정답에 1추가
                }
            }
        }

        System.out.println(answer);
    }
}