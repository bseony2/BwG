import java.io.*;
import java.util.*;

public class BOJ_15650_N과M {
	static int N, M;
	static List<String> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");

		N = Integer.parseInt(s[0]);
		M = Integer.parseInt(s[1]);
		list = new ArrayList<>();

		_nCr(0, 0);
	}

	private static void _nCr(int start, int count) {

		if (count == M) {
			System.out.println(String.join(" ", list));
			return;
		}

		for (int i = start; i < N; i++) {

			String s = String.valueOf(i + 1);

			list.add(s);
			_nCr(i + 1, count + 1);
			list.remove(list.indexOf(s));
		}
	}

}
