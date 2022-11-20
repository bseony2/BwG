// 명
// s와 reverse(s)와 비교하기
public class Solution_214_ShortestPalindrome_답3 {

	public static void main(String[] args) {
		Solution_214_ShortestPalindrome_답3 sol = new Solution_214_ShortestPalindrome_답3();
//		String s = "cacacabc";
		String s = "abacd";
//		String s = "aacecaaa";
//		String s = "abcd";
//		String s = "aaaceaa";
//		String s = "aaceca";
//		String s = "";
//		String s = "abcda";

//		String s = "abacaddaaa";
		String answer = sol.shortestPalindrome(s);
		System.out.println(answer);
	}

	public String shortestPalindrome(String s) {
		StringBuilder result = new StringBuilder();
		int N = s.length();
		StringBuilder reverse = new StringBuilder(s);
		reverse.reverse();
		StringBuilder sb = new StringBuilder(s);
		for (int i = 0; i < N; i++) {
			System.out.println(sb);
			System.out.println(reverse);
			System.out.println();
			if (sb.toString().equals(reverse.toString())) {
				String tail = s.substring(N - i, N);
				StringBuilder head = new StringBuilder(tail);
				head.reverse();
				result.append(head);
				result.append(sb);

				head.reverse();
				result.append(head);

				break;
			}
			sb.deleteCharAt(N - 1 - i);
			reverse.deleteCharAt(0);
		}
		return result.toString();
	}
}
