import java.util.stream.IntStream;

public class 합성수찾기_20230114 {
    public static void main(String[] args) {

        int n = 10;

//        int answer = 0;
//
//        for(int i = 1; i <= n ; i++){
//            int cnt = 0;
//            for(int j = 1; j <= i ; j++){
//                if(i%j == 0){
//                    cnt++;
//                }
//            }
//
//            if(cnt >= 3){
//                answer++;
//            }
//        }

        int answer = (int)IntStream.rangeClosed(1,n).filter(i -> (int)IntStream.rangeClosed(1,i).filter(i2 -> i % i2 == 0).count() > 2).count();

        System.out.println(answer);
    }
}
