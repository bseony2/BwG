package baekjoon.simulation.codetree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * - 격자 사이의 거리는 절대값의 합
 * - 입력값 : n, m
 * 	 > m : 선택한 병원의 수
 *
 * - 선택된 m개의 병원 중 각각의 병원에 대해 모든 사람의 거리의 총 합 중에서 최소값 구하기(병원마다 각각 구하는듯)
 * - 각 사람의 병원 거리는 가장 가까운 병원까지의 거리를 의미
 *
 * - <해결방법>
 * 	 1. 조합으로 m개의 병원선택 -> nCm 조합
 * 	 2. 선택한 각각의 병원과 한 사람당 최소거리 구하기 (조합 -> 이중반복문으로 구현)
 * 	 3. 각 사람의 병원 거리 최소값의 합
 *   4. 1사람당 1병원 들림
 */
public class CT_병원거리최소화하기2 {
    static int n, m;
//    static int[] dx = {-1, 0, 1, 0};
//    static int[] dy = {0, -1, 0, 1};
    static ArrayList<int[]> peopleList = new ArrayList<>(); // 입력된 총 사람의 좌표
    static ArrayList<Hospital> hospitalList = new ArrayList<Hospital>(); // 모든 병원의 좌표(선택되지 않음) -> peopleList처럼 Integer List로 해도 상관없지 않나?
    static int result = Integer.MAX_VALUE; // 최종 반환 결과값(모든 사람의 모든 병원에 대한 최소길이)
//	static int[][] arr; // 전체배열 정의 필수X

    public static void main(String[] args) throws IOException {

        init(); // 입력 초기화

        combination(0, 0, new boolean[hospitalList.size()]);

        System.out.println(result); // 결과값 출력
    }

    /**
     * @Desc <조합> 병원선택하기 nCm + 결과값 구하기
     */
    private static void combination(int depth, int start, boolean[] isVisited) {
        if (depth == m) {
            getTotMinDis(isVisited);
            return;
        }

        for (int i = start; i < isVisited.length; i++) {
            isVisited[i] = true;
            combination(depth + 1, i + 1, isVisited);
            isVisited[i] = false;
        }
    }

    /**
     * @Desc 최종 결과값 구하기(병원거리 최소화)
     *       combination()에서 구한 병원개수의 조합으로 각 병원에 대한 최소 거리를 구함
     *
     *       combination()에서 getTotMinDis()를 호출하는 시점은 조합 1개의 선택이 이루어진 시점
     *       즉, nCm의 경우의 수에서 1가지의 선택이 일어난 시점
     *       그 한 가지의 병원 선택 건에 대한 최소 거리를 구한다.
     */
    private static void getTotMinDis(boolean[] isVisited) {
        int[] personDis = new int[peopleList.size()]; // 사람별 병원에 대한 거리를 담을 배열
        Arrays.fill(personDis, Integer.MAX_VALUE); // 최소값 초기화

        // 선택된 각 병원 순회
        for (int i = 0; i < isVisited.length; i++) {
            if (!isVisited[i]) continue;

            Hospital hospital = hospitalList.get(i); // 1개의 병원 좌표

            // 1개의 병원에 대한 모든 사람의 거리 구하기
            // dx, dy 필요없음 = 문제의 거리구하기 공식 사용(절대값)
            for (int j = 0; j < peopleList.size(); j++) {
                int[] personCoordinate = peopleList.get(j);
                int personX = personCoordinate[0];
                int personY = personCoordinate[1];

                // 한 사람의 i번째 병원에 대한 거리 구하기 -> 사람별로 personDis배열에 할당
                int tempDis = Math.abs(hospital.r - personX) + Math.abs(hospital.c - personY);
                // 최소값을 또 구해야함? -> personDis배열의 값을 Integet.MAX_VALUE로 초기화해줬기 떄문
                // 사람마다 1개의 병원에 대한 최소거리 구해야함
                personDis[j] = Math.min(personDis[j], tempDis);
            }
        }

        int sum = Arrays.stream(personDis).sum();
        result = result > sum ? sum : result;
    }

    /**
     * @Desc 입력값 초기화
     * @throws IOException
     */
    private static void init() throws IOException { // 입력스트림을  사용하기 때문에 해당 메소드에서도 예외처리를 해준다 (예외처리 유념)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // nCm 조합
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken()); // 병원 선택 수

        // 입력을 받으면서 입력값에 따라 사람, 병원의 좌표 초기화 (1: 사람, 2: 병원)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 입력값에 따라 사람, 병원의 좌표만 할당
                // TODO 왜 코드의 최적화를 위해서 if보다 switch문을 수행해야 하는가?
                int value = Integer.parseInt(st.nextToken());
                switch (value) {
                    case 1:
                        peopleList.add(new int[]{i, j});
                        break;
                    case 2:
                        hospitalList.add(new Hospital(i, j));
                        break;

                    default:
                        break;
                }
            }
        }
    }

    // isValidPoint() 체킹 안해도 되나?

    // 병원 좌표 클래스
    // TODO 클래스로 만든 이유??
    static class Hospital{
        int r, c;

        public Hospital(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}


