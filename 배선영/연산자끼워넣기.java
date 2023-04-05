// import java.io.BufferedReader;
// import java.io.InputStreamReader;
// import java.io.IOException;
// import java.util.stream.Stream;
// import java.util.StringTokenizer;

// public class 연산자끼워넣기 {
//     static int MAX = Integer.MIN_VALUE;
//     static int MIN = Integer.MAX_VALUE;
//     static int N;
//     static int[] value;
//     static int[] sign = new int[4];
//     public static void main(String[] args) throws IOException{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         value = new int[N];

//         // 숫자 입력 
//         StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//         for (int i = 0; i < N; i++) {
//             value[i] = Integer.parseInt(st.nextToken());
//         }

//         // 연산자 입력 
//         st = new StringTokenizer(br.readLine(), " ");
//         for (int i = 0; i < 4; i++) {
//             sign[i] = Integer.parseInt(st.nextToken());
//         }

//         // value = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//         // sign = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

//         nPr(value[0], 1);
//         System.out.println(MAX);
//         System.out.println(MIN);
//     }

//     static void nPr(int sum, int cnt) {
//         if(cnt == N) {
//             MAX = Math.max(MAX, sum);
//             MIN = Math.min(MIN, sum);
//             return;
//         }

//         for(int i=0; i < 4; i++) {
//             if(sign[i] > 0) {
//                 sign[i]--;

//                 switch(i) {
//                     case 0: nPr(sum + value[cnt], cnt + 1); break;
//                     case 1: nPr(sum - value[cnt], cnt + 1); break;
//                     case 2: nPr(sum / value[cnt], cnt + 1); break;
//                     case 3: nPr(sum * value[cnt], cnt + 1); break;
//                 }
//                 sign[i]++;
//             }
//         }
//     }
// }

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 연산자끼워넣기 {

    static int maxVal = Integer.MIN_VALUE;
    static int minVal = Integer.MAX_VALUE;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        int[] intArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sign = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        dfs(intArr, sign, 1, intArr[0]);
        System.out.println(maxVal);
        System.out.println(minVal);
    }

    static void dfs(int[] intArr, int[] sign, int depth, int result) {

        if(depth == N) {
            minVal = minVal < result ? minVal : result;
            maxVal = maxVal > result ? maxVal : result;
            return;
        }

        for(int i=0; i<4; i++) {
            if(sign[i] > 0) {
                sign[i]--;
                if(i == 0) {
                    dfs(intArr, sign, depth+1, result + intArr[depth]);
                }else if(i == 1) {
                    dfs(intArr, sign, depth+1, result - intArr[depth]);
                }else if(i == 2) {
                    dfs(intArr, sign, depth+1, result * intArr[depth]);
                }else if(i == 3) {
                    dfs(intArr, sign, depth+1, result / intArr[depth]);
                }
                sign[i]++;
            }
        }
    }
}
