class 행렬테두리회전하기 {

    public static void main(String[] args) {
        행렬테두리회전하기 so = new 행렬테두리회전하기();
        
        int[][] queries = {{1,1,2,2},{1,2,2,3},{2,1,3,2},{2,2,3,3}};
        so.solution(3, 3, queries);
        
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] field = new int[rows][columns];
        int value = 1;
        for(int i=0; i<rows; i++)
        {
            for(int j=0; j<columns; j++)
            {
                field[i][j] = value++;
            }
        }
        
        int temp;
        int index = 0;
        for(int[] array : queries)
        {
            int min = rows * columns;
            temp = field[array[0]-1][array[3]-1];
            for(int i = array[3] - 1; i>array[1]-1;i--)
            {
                field[array[0] - 1][i] = field[array[0] - 1][i-1];
                min = min < field[array[0] - 1][i-1] ? min : field[array[0] - 1][i-1];
            }
                
            for(int i=array[0] - 1; i < array[2]-1;i++)
            {
                field[i][array[1]-1] = field[i + 1][array[1]-1];
                min = min < field[i + 1][array[1]-1] ? min : field[i + 1][array[1]-1];
            }
            
            for(int i = array[1] - 1;i< array[3] - 1; i++)
            {
                field[array[2]-1][i] = field[array[2]-1][i+1];
                min = min < field[array[2]-1][i+1] ? min : field[array[2]-1][i+1];
            }
            
            for(int i= array[2]-1;i>array[0];i--)
            {
                field[i][array[3]-1] = field[i-1][array[3]-1];
                min = min < field[i-1][array[3]-1] ? min : field[i-1][array[3]-1];
            }
            field[array[0]][array[3]-1] = temp;
            answer[index++] = min < temp ? min : temp;
        }
        
        return answer;
    }
}