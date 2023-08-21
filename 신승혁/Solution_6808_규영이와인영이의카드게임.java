import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 시간초과 96/100
public class Solution_6808_규영이와인영이의카드게임 {

    static List<Integer> kyuCards;
    static List<Integer> inCards;
    static List<Integer> list;
    static int scores[];
    static int kyuWinCount;
    static int kyuLoseCount;
    static final int N = 9;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {

            kyuCards = new ArrayList<>();
            inCards = new ArrayList<>();
            list = new ArrayList<>();
            scores = new int[2];
            kyuWinCount = 0;
            kyuLoseCount = 0;

            String[] line = br.readLine().split(" ");
            for (int i = 0; i < N; i++) {
                kyuCards.add(Integer.parseInt(line[i]));
            }

            // init 인규
            for (int i = 1; i <= 2 * N; i++) {
                inCards.add(i);
            }
            inCards.removeAll(kyuCards);

            nPr(0, new boolean[9], N);


            System.out.println("#" + t + " " + kyuWinCount + " " + kyuLoseCount);
        }


    }


    public static void nPr(int count, boolean isGo[], int R) {
        if (count == R) {
            // 9라운드 끝

            scores = new int[2];

            for (int i = 0; i < N; i++) {
                int kyuCard = kyuCards.get(i);
                int inCard = list.get(i);
                int sum = kyuCard + inCard;
                if (kyuCard > inCard) {
                    scores[0] += sum;
                } else {
                    scores[1] += sum;
                }
            }

            // 총점 계산
            if (scores[0] > scores[1]) {
                kyuWinCount++;
            } else if(scores[0] < scores[1]) {
                kyuLoseCount++;
            }



            return;
        }

        for (int i = 0; i < N; i++) {
            if (isGo[i]) {
                continue;
            }

            isGo[i] = true;
            list.add(inCards.get(i));
            nPr(count + 1, isGo, R);
            list.remove(list.indexOf(inCards.get(i)));
            isGo[i] = false;

        }
    }
}
