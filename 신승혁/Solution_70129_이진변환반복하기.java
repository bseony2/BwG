import java.util.*;
import java.util.stream.*;

public class Solution_70129_이진변환반복하기 {
	int iterCount = 0;
	int removeCount = 0;

	public int[] solution(String s) {

		// System.out.println(_convertBinary(s));
		_recursive(s);
		return new int[] { iterCount, removeCount };
	}

	private void _recursive(String s) {
		if ("1".equals(s)) {
			return;
		}
		_recursive(_convertBinary(s));
	}

	private String _convertBinary(String s) {

		removeCount += (int) s.chars().filter(ch -> ch == '0').count();
		iterCount++;

		String s1 = s.replaceAll("0", "");
		return Integer.toBinaryString(s1.length());
	}
}
