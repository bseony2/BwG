public class Solution_42898_등굣길 {
    public static void main(String[] args){
        Solution sol = new Solution();

        int m = 4;
        int n = 3;
        int[][] puddles = {{2,2}};

        int result = sol.solution(m,n,puddles);
        System.out.println(result);
    }
    static class Solution {
        final int PUDDLE = -1;
        public int solution(int m, int n, int[][] puddles) {
            int[][] counts = new int[n][m];

            // 선웅덩이
            for (int[] puddle : puddles) {
                counts[puddle[1] - 1][puddle[0] - 1] = PUDDLE;
            }

            // 1행
            boolean isPuddle = false;
            for(int i = 0; i < n; i++) {
                if(counts[i][0] == PUDDLE) isPuddle = true;
                counts[i][0] = isPuddle ? PUDDLE : 1;
            }
            // 1열
            isPuddle = false;
            for(int j = 0; j < m; j++) {
                if(counts[0][j] == PUDDLE) isPuddle = true;
                counts[0][j] = isPuddle ? PUDDLE : 1;
            }



            //_print(counts);
            for(int i = 1 ; i < n ; i++) {
                for(int j = 1; j < m; j++) {
                    if(counts[i][j] == PUDDLE){
                        continue;
                    }
                    counts[i][j] = (_convert(counts[i-1][j]) + _convert(counts[i][j-1]));
                    if(counts[i][j] > 1000000007) counts[i][j] %= 1000000007;
                }
            }

            return counts[n - 1][m - 1] ;

        }

        private int _convert(int count){
            if(count == PUDDLE) return 0;
            return count;
        }
    }
}
