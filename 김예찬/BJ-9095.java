package Boj.silver;
import java.io.*;

public class Boj_9095 {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static int[] memo;
   public static void main(String[] args) throws NumberFormatException, IOException {
      

      memo = new int[12];
      memo[1] = 1;
      memo[2] = 2;
      memo[3] = 4;
      memo[4] = 7;
      int tc = Integer.parseInt(br.readLine());
      for(int t = 0; t<tc; t++) {
         int n = Integer.parseInt(br.readLine());
         System.out.println(find(n));
      }
   }
 
   private static int find(int n) {
      if(memo[n] > 0) return memo[n];
      
      memo[n] = find(n-1) + find(n-2) + find(n-3);
      return memo[n];
   }
}