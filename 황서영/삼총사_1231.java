public class 삼총사_1231 {
    static int numArr[];
    static int N;
    static int temp[] = new int[3];
    static int answer = 0;

    public static void main(String[] args) {
        int[] number = {-3, -2, -1, 0, 1, 2, 3};
        N = number.length;
        numArr = number;
        DFS(0,0);
        System.out.println(answer);
    }

    // 깊이 우선 탐색 : 하나의 정점으로부터 시작하여 차례대로 모든 정점들을 한 번씩 방문하는 것
    private static void DFS(int idx, int depth){
        if(depth == 3){
            int sum = 0;
            for(int i = 0 ; i < 3; i++){
                sum += temp[i];
            }
            if(sum == 0){
                answer++;
            }
            return;
        }

        for(int i = idx; i < N; i++){
            temp[depth] = numArr[i];
            DFS(i+1, depth + 1); //재귀호출
        }
    }
}