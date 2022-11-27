import java.util.*;
import java.util.stream.*;

public class Solution_12939_최댓값과최솟값 {
	public String solution(String s) {
		String[] splits = s.split(" ");
		List<Integer> list = Arrays.stream(splits).map(el -> Integer.parseInt(el)).collect(Collectors.toList());

		Collections.sort(list);

		return list.get(0) + " " + list.get(list.size() - 1);
	}
}
