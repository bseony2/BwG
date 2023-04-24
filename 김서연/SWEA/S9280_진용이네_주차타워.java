package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// 요금 : Ri * Wi
public class S9280_진용이네주차타워2 {
    static int totalFee; // Result : 총 요금
    static boolean[] prkgSpace; // 주차공간 배열
    static int[] feePerWeight; // 무게당 요금(계산용)
    static int[] carWeight; // 차량무게 배열(계산용)
    static int[] currentLoc; // 차량위치
    static Queue<Integer> waitingQueue; // 대기차량 큐
    static PriorityQueue<Integer> emptyQueue; // 주차가능 빈자리 큐

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        // 테스트케이스 tc입력
        int tc = Integer.parseInt(br.readLine());

        for (int i = 1; i <= tc; i++) {
            // 주차공간 n, 차량대수 m 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // n: 주차공간
            int m = Integer.parseInt(st.nextToken()); // m: 차량대수

            // tc별 초기화
            totalFee = 0;
            prkgSpace = new boolean[n];
            feePerWeight = new int[n];
            currentLoc = new int[m]; // 차량 현재 주차번호(인덱스, 과거 포함)
            carWeight = new int[m];
            waitingQueue = new LinkedList<>(); // 대기차량
            emptyQueue = new PriorityQueue<>(); // 빈 주차자리

            // 처음 시작시 빈주차자리 인덱스 입력
            for (int j = 0; j < feePerWeight.length; j++) {
                emptyQueue.add(j);
            }

            // n개줄 주차공간 단위무게당 요금 Ri 입력
            for (int j = 0; j < n; j++) {
                feePerWeight[j] = Integer.parseInt(br.readLine());
            }
            // m개줄 차량i의 무게 Wi 입력
            for (int j = 0; j < m; j++) {
                carWeight[j] = Integer.parseInt(br.readLine());
            }
            // 2m개줄 차량의 출입순서
            for (int j = 0; j < 2 * m; j++) {
                int carIdx = Integer.parseInt(br.readLine()); // 차량 무게배열의 인덱스(차량으로 생각)

                processPrkg(carIdx); // 입출차 처리
            }
            // 정산
            for (int t = 0; t < currentLoc.length; t++) {
                totalFee += carWeight[t] * feePerWeight[currentLoc[t]];
            }
            sb.append("#").append(i).append(" ").append(totalFee).append("\n");
        }
        System.out.println(sb);
    }

    // 입출차 처리
    static void processPrkg(int carIdx) {
        if (carIdx >= 1) {
            in(carIdx - 1); // 입차
        } else if (carIdx <= -1) {
            out((carIdx * -1) - 1); // 출차
        }
    }

    // 입차
    static void in(int carIdx) {
        if (!emptyQueue.isEmpty()) { // 빈자리 O
            int prkgIdx = emptyQueue.poll();
            currentLoc[carIdx] = prkgIdx;
        } else { // 빈자리 X - 대기큐 삽입
            waitingQueue.offer(carIdx);
        }
    }

    // 출차
    static void out(int carIdx) {
        // 출차의 주차인덱스
        int outPrkgIdx = currentLoc[carIdx];
        // 빈 자리 큐에 삽입
        emptyQueue.offer(outPrkgIdx); // 나가서 이제 빈자리

        if (!waitingQueue.isEmpty()) { // 대기차량이 있으면 주차
            in(waitingQueue.poll());
        }
    }
}
