import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1157_단어공부 {
    static int[] alphabetCount = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String word = br.readLine();

        String upperCasedWord = word.toUpperCase();
        for (char c : upperCasedWord.toCharArray()) {
            alphabetCount[_getIndex(c)]++;
        }

        PriorityQueue<Alphabet> pq = new PriorityQueue<>((a, b) -> b.count - a.count); // maxHeap

        for (int i = 0; i < alphabetCount.length; i++) {
            pq.offer(new Alphabet(i, alphabetCount[i]));
        }


        Alphabet first = pq.poll();  // 가장 큰 값
        Alphabet second = pq.poll(); // 두번째로 큰 값

        char result = first.count == second.count ? '?' : (char) (first.index + 65);
        System.out.println(result);
    }

    private static class Alphabet {
        int index;
        int count;

        public Alphabet(int index, int count) {
            this.index = index;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Alphabet{" +
                    "index=" + index +
                    ", count=" + count +
                    '}';
        }
    }

    private static int _getIndex(char alphabet) {
        return (int) alphabet - 65;
    }
}
