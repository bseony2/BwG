import java.util.Scanner;

class 초콜릿식사 {
    public static void main(String...args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int size = 1;

        while(size < K) {
            size *= 2;
        }

        System.out.print(size + " ");

        int cnt = 0;
        while(true) {
            if(K % size == 0) {
                System.out.print(cnt);
                break;
            }
            size /= 2;
            cnt += 1;
        }
        sc.close();
    }
}