import java.util.Scanner;

public class p1475 {
	
	static int ten[] = new int[10]; 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String number = sc.next();
		
		for(char ch : number.toCharArray()) {
			int num = ch - '0';
			if(num == 6 || num == 9) {
				ten[ten[6] > ten[9] ? 9 : 6]++;
			}else {
				ten[num]++;
			}
		}
		int answer = 0;
		for(int i = 0; i < 10; i++) {
			answer = Math.max(answer,  ten[i]);
		}
		System.out.println(answer);
	}
}
