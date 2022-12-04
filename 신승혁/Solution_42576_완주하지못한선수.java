import java.util.*;
import java.util.stream.*;

public class Solution_42576_완주하지못한선수 {
	public String solution(String[] participant, String[] completion) {
		List<String> participants = Arrays.asList(participant);
		Collections.sort(participants);

		List<String> completions = Arrays.asList(completion);
		Collections.sort(completions);

		// 하나 적은 배열기준으로 순회
		for (int i = 0; i < completions.size(); i++) {
			if (!participants.get(i).equals(completions.get(i))) {
				return participants.get(i);
			}
		}

		// 맨마지막이름이 미완주인 케이스
		return participants.get(participants.size() - 1);
	}
}
