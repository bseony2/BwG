import java.io.*;
import java.util.*;

public class BOJ_2309_일곱난쟁이 {
	static int N = 9;
	static int R = 7;
	static int[] dwarf;
	static List<Integer> list = new ArrayList<>();

	static boolean isFinished;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dwarf = new int[N];
		isFinished = false;
		for (int i = 0; i < N; i++) {
			dwarf[i] = Integer.parseInt(br.readLine());
		}

		nCr(0, 0, 0);

	}

	private static void nCr(int start, int count, int sum) {
		if (count == R) {
			if (isFinished) {
				return;
			}
			if (sum == 100) {
				Collections.sort(list);
				list.stream().forEach(el -> System.out.println(el));
				isFinished = true;
			}
			return;
		}
		for (int i = start; i < N; i++) {
			list.add(dwarf[i]);
			nCr(i + 1, count + 1, sum + dwarf[i]);
			list.remove(list.indexOf(dwarf[i]));
		}
	}

}
