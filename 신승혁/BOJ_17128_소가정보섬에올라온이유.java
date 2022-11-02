import java.io.*;
import java.util.Arrays;

public class BOJ_17128_소가정보섬에올라온이유 {
	public static final int RANGE = 4;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] s = br.readLine().split(" ");
		int N = Integer.parseInt(s[0]);
		int Q = Integer.parseInt(s[1]);

		String[] nStringArray = br.readLine().split(" ");
		int[] nArray = new int[N];
		for (int i = 0; i < nStringArray.length; i++) {
			nArray[i] = Integer.parseInt(nStringArray[i]);
		}
//		_print(nArray);

		String[] qStringArray = br.readLine().split(" ");
		int[] qArray = new int[Q];
		for (int i = 0; i < qStringArray.length; i++) {
			qArray[i] = Integer.parseInt(qStringArray[i]) - 1; // to index
		}

		// 구간곱배열
		int[] intervals = new int[N];
		Arrays.fill(intervals, 1);

		// mul init
		int mul = 1;
		for (int i = 0; i < RANGE; i++) {
			intervals[0] *= nArray[i];
		}
		mul = intervals[0];

		int p = 0;
		int r = RANGE;
		for (int i = 0; i < N - 1; i++) {
			int head = nArray[p % N];
			int tail = nArray[r % N];
			mul /= head;
			mul *= tail;
			intervals[i + 1] = mul;

			p++;
			r++;
//			_print(intervals);
		}

		// sum init
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += intervals[i];
		}

		for (int q = 0; q < qArray.length; q++) {
//			System.out.println("### q : " + q);
//			System.out.println("### qArray[q] : " + qArray[q]);
			// 구간 최대최소값
			int[] minMax = _getIntervalMinMaxIndexes(qArray[q], N);
			// 영향있는 (네)구간 -1 곱하기
			for (int i = minMax[0]; i < minMax[1]; i++) {
				int index = _getCalIndex(i, N);
//				System.out.println("index : " + index);
//				System.out.println("intervals[index] : " + intervals[index]);
				intervals[index] *= -1;
//				System.out.println("intervals[index] : " + intervals[index]);
				sum += intervals[index] * 2;
			}

			System.out.println(sum);
		}
	}

	// 계산된 인덱스 구하기
	private static int _getCalIndex(int index, int nLength) {
		int result = index;

		if (result < 0) {
			result += nLength;
		} else if (result >= nLength) {
			result -= nLength;
		}

		return result;
	}

	private static void _print(int[] array) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	private static int[] _getIntervalMinMaxIndexes(int index, int N) {
		int min = (index - RANGE) % N + 1;
		int max = index + 1;

		return new int[] { min, max };
	}
}
