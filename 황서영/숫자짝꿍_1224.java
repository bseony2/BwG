import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class 숫자짝꿍_1224 {

    public static void main(String[] args) {
        String X = "12321";
        String Y = "42531";

        String answer = "";

        int[] cntX = new int[10];
        int[] cntY = new int[10];

        for(int i = 0; i < X.length() ; i++){
           cntX[X.charAt(i)-'0']++;
        }
        for(int i = 0; i < Y.length() ; i++){
            cntY[Y.charAt(i)-'0']++;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < cntX.length ; i++){
            if(cntX[i] == 0) continue;
            sb.append(String.valueOf(i).repeat(Math.min(cntX[i],cntY[i])));
        }

        answer = sb.toString();

        answer = Stream.of(answer.split("")).sorted(Collections.reverseOrder()).collect(Collectors.joining());

        if(answer.isEmpty()){
            answer = "-1";
        }

        if(answer.startsWith("0")){
            answer = "0";
        }

        System.out.println(answer);
    }
}
