import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.lang.StringBuilder;
import java.util.StringTokenizer;

class 규영이와인영이의카드게임 {
    static int win;
    static int lose;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        
        for(int t=1; t<=T; t++) {
            int[] myCards = new int[9];
            boolean[] selectedCard = new boolean[19];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int i=0; i<9; i++) {
                int value = Integer.parseInt(st.nextToken());
                myCards[i] = value;         // 내 카드
                selectedCard[value] = true;
            }

            int[] opponentCards = new int[9];
            for(int i=1,index=0; i<19; i++) {
                if(!selectedCard[i]) opponentCards[index++] = i;    // 상대방 카드
            }

            win = lose = 0;
            play(myCards, opponentCards, new boolean[9], 0, 0, 0);
            sb.append("#"+t).append(" " + win).append(" " + lose + "\n");
        }

        bw.write(sb.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    static void play(int[] myCards, int[] opponentCards, boolean[] isVisited, int depth, int myScore, int opponentScore) {
        if(myScore > 85 || opponentScore > 85 || depth ==9) {    // 1부터 18까지 다 더해봐야 171임
            int afterValue = 1;
            for(int i = 9-depth; i>1; i--) {
                afterValue *= i;    // 한쪽이 이미 85점을 넘는다면 그 아래 depth에 있는 모든 경우의 수의 승패도 똑같다 그러니까 팩토리얼로 그 아래 경우의 수를 구한다
            }
            if(myScore > 85) win += afterValue;
            else if(opponentScore > 85) lose += afterValue;

            return;
        }

        for(int i=0; i<9; i++) {    // 상대방이 내는 카드의 순서가 유의미 하므로 조합이 아닌 순열로 짜야함
            if(!isVisited[i]) {
                isVisited[i] = true;

                int score = myCards[depth] + opponentCards[i];  // 내 카드의 순서는 고정적이므로 depth 값을 사용해야 한다

                if(myCards[depth] > opponentCards[i]) {
                    myScore += score;
                } else {
                    opponentScore += score;
                }

                play(myCards, opponentCards, isVisited, depth + 1, myScore, opponentScore);

                if(myCards[depth] > opponentCards[i]) {
                    myScore -= score;
                } else {
                    opponentScore -= score;
                }
                isVisited[i] = false;
            }
        }
    }
}