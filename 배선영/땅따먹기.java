class 땅따먹기 {
    int solution(int[][] land) {
        int answer = 0;

        int rowSize = land.length;
        int[][] matrix = new int[rowSize][4];
        
        matrix[0][0] = land[0][0];
        matrix[0][1] = land[0][1];
        matrix[0][2] = land[0][2];
        matrix[0][3] = land[0][3];
        
        for(int row=1; row<land.length; row++) {
            for(int col = 0; col < 4; col++) {
                if(col == 0)
                    matrix[row][col] = land[row][col] + maxVal(matrix[row - 1][1], matrix[row - 1][2], matrix[row - 1][3]);
                else if(col == 1)
                    matrix[row][col] = land[row][col] + maxVal(matrix[row - 1][0], matrix[row - 1][2], matrix[row - 1][3]);
                else if(col == 2)
                    matrix[row][col] = land[row][col] + maxVal(matrix[row - 1][0], matrix[row - 1][1], matrix[row - 1][3]);
                else
                    matrix[row][col] = land[row][col] + maxVal(matrix[row - 1][0], matrix[row - 1][1], matrix[row - 1][2]);
            }
        }

        answer = matrix[rowSize - 1][0] > matrix[rowSize - 1][1] ? matrix[rowSize - 1][0] : matrix[rowSize - 1][1];
        answer = answer > matrix[rowSize - 1][2] ? answer : matrix[rowSize - 1][2];
        answer = answer > matrix[rowSize - 1][3] ? answer : matrix[rowSize - 1][3];
        return answer;
    }
    
    int maxVal(int a, int b, int c) {
        if( a > b )
            return a > c ? a : c;
        else
            return b > c ? b : c;
    }
}