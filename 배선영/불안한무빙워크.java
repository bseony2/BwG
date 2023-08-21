import java.util.Scanner;

public class 불안한무빙워크 {

    static int n, k;
    static int[] machine;
    static int numOfZero = 0;
    static boolean[] tester;
    public static void main(String[] args) {
        init();

        test();
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        machine = new int[n*2];
        tester = new boolean[n];
        for(int i=0; i<2*n; i++) {
            machine[i] = sc.nextInt();
        }

        sc.close();
    }

    static void test() {
        int turn = 0;
        while(true) {
            turn += 1;

            spin();

            moveTester();
        }

        System.out.println(turn);
    }

    static void spin() {
        int value = machine[2*n-1];
        System.arraycopy(machine, 0, machine, 1, 2*n-1);
        machine[0] = value;

        System.arraycopy(tester, 0, tester, 1, n-1);
        tester[0] = false;
        tester[n-1] = false;
    }

    static void moveTester() {
        for(int i=n-2; i>=0; i--) {
            if(!isEmpty(i) && isEmpty(i+1) && isSafety(i+1)) {
                tester[i] = false;
                
                rideOnMachine(i+1);
            }
        }

        tester[n-1] = false;

        if(isSafety(0)) {
            rideOnMachine(0);
        }
    }

    static void rideOnMachine(int i) {
        machine[i] -= 1;
        tester[i] = true;
        if(machine[i] == 0) {
            numOfZero += 1;
        }
    }

    static boolean isEmpty(int i) {
        return !tester[i];
    }

    static boolean isSafety(int i) {
        return machine[i] > 0;
    }
}
