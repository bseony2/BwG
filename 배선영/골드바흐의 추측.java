import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Math;

public class Main {
    static String BAD_ANSWER = "Goldbach's conjecture is wrong.";

    static boolean[] isPrime = new  boolean[1000001];
    static ArrayList<Integer> arr = new  ArrayList<Integer>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException{
        getPrime();

        while(true) {
            int testCase = Integer.parseInt(br.readLine());
            if(testCase == 0)
                return;
            System.out.println(printAnswer(testCase));
        }
        
    }

    static void getPrime() { // 소수 구하기
        Arrays.fill(isPrime, true);

        for(int i=2; i<Math.sqrt((isPrime.length/2) + 1); i++) {

            if(!isPrime[i]) continue;
            arr.add(i);
            for(int j = i * 2; j< isPrime.length; j+= i) {
                isPrime[j] = false;
            }
        }
    }

    static String printAnswer(int value) {
        for(int i = 0; i<arr.size(); i++) {
            int currentInt = arr.get(i);
            if(isPrime[value - currentInt]) {
                
                return value + " = " + currentInt + " + " + (value-currentInt);
            }
        }

        return BAD_ANSWER;
    }
}