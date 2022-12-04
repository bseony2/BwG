class 피로도 {

    int answer = Integer.MIN_VALUE;
    public boolean[] isVisited;

    public int solution(int k, int[][] dungeons) {

        isVisited = new boolean[dungeons.length];

        dfs(0, k, dungeons);

        return answer;
    }

    public void dfs(int depth, int k, int[][]dungeons)
    {
        for(int i=0; i<dungeons.length; i++)
        {
            if(!isVisited[i] && k >= dungeons[i][0])
            {
                isVisited[i] = true;
                dfs(++depth, k-dungeons[i][1], dungeons);
                isVisited[i] = false;
            }
        }

        answer = answer > depth ? answer : depth;
    }
}