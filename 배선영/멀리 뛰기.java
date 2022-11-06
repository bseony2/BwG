class 멀리뛰기 {
    public long solution(int n) {        
        int[] arr = new int[n + 2];
        arr[1] = 1;
        arr[2] = 2;
        
        for(int i=3; i<n + 1; i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % 1234567;
        }
        return arr[n];
    }
}


// n =1 일때 1   1 증가
// n =2 일때 2   1 증가
// n =3 일때 3   1 증가
// n =4 일때 5   2 증가
// n =5 일때 8   3 증가
// n =6 일때 13  5 증가
// n =7 일때 21  8 증가
// n =8 일때 34  13 증가
// n =9 일때 55  21 증가

