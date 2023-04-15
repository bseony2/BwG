import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class 규영이와인영이의카드게임 {
    static BufferedReader br;
    static BufferedWriter bw;
    static int[] myCards;
    static int win = 0;
    static int lose = 0;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        for(int t=1; t<=T; t++) {
            myCards = new int[9];
            boolean[] selectedCard = new boolean[19];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0; i<9; i++) {
                int value = Integer.parseInt(st.nextToken());
                myCards[i] = value;
                selectedCard[value] = true;
            }

            int[] otherCards = new int[9];
            int index = 0;
            for(int i=1; i<=18; i++) {
                if(!selectedCard[i]) {
                    otherCards[index++] = i;
                }
            }

            win = 0;
            lose = 0;
            play(otherCards, new boolean[9], 0, 0, 0);
            
            System.out.println("#"+t + " " + win + " " + lose + "\n");
        }

    }

    // 순열 작성해야 함
    static void play(int[] otherCards, boolean[] isSelected, int depth, int myScore, int otherScore) {
        
        if(myScore > 85 || otherScore > 85) {
            int extraScore = 1;
            for(int i=9-depth; i>0; i--) {
                extraScore *= i;
            }

            if(myScore > otherScore) win += extraScore;
            else lose += extraScore;

            return;
        }

        for(int i=0; i<9; i++) {
            if(!isSelected[i]) {
                
                isSelected[i] = true;

                int sum = myCards[depth] + otherCards[i];
                if(myCards[depth] > otherCards[i]) {
                    myScore += sum;
                }
                else {
                    otherScore += sum;
                }
                
                play(otherCards, isSelected, depth+1, myScore, otherScore);

                if(myCards[depth] > otherCards[i]) {
                    myScore -= sum;
                }
                else {
                    otherScore -= sum;
                }

                isSelected[i] = false;
            }
        }
    }
}