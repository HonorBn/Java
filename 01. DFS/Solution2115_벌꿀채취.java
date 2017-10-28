import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   SW Expert Academy
*/

public class Solution2115_벌꿀채취 {

	static BufferedReader br;
	static StringBuilder result;

	static int N, M, C, temp, newC;
	static int[][] map, mapPow, select;

	public static void main(String[] args) throws IOException {

		result = new StringBuilder();

		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine().trim());

		int value;

		for (int tc = 1; tc <= T; tc++) {

			input();

			value = process();

			print(tc, value);

		}

		System.out.println(result.toString());

	}

	private static void input() throws IOException {

		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		mapPow = new int[N][N];

		int su;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				su = Integer.parseInt(st.nextToken());
				map[i][j] = su;
				mapPow[i][j] = (int) Math.pow(su, 2);
			}
		}

	}

	private static int process() {

		newC = N - M + 1;
		select = new int[N][newC];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < newC; j++) {
				temp = 0;
				sum(0, 0, 0, i, j);
				select[i][j] = temp;
			}
		}

		int row1, col1, row2, col2, max = 0, num = N * newC;

		for (int i = 0; i < num; i++) {
			row1 = i / newC;
			col1 = i % newC;
			if (select[row1][col1] == 0)
				continue;
			for (int j = (col1 < newC - M) ? i + M : (row1 + 1) * newC; j < num; j++) {
				row2 = j / newC;
				col2 = j % newC;
				if (select[row2][col2] == 0)
					continue;
				max = Math.max(max, select[row1][col1] + select[row2][col2]);
			}
		}

		return max;

	}

	private static void sum(int cnt, int sum, int sumPow, int row, int col) {

		if (sum > C)
			return;

		if (cnt == M) {
			temp = Math.max(temp, sumPow);
			return;
		}

		sum(cnt + 1, sum + map[row][col], sumPow + mapPow[row][col], row, col + 1);
		sum(cnt + 1, sum, sumPow, row, col + 1);

	}

	private static void print(int tc, int value) {

		result.append("#").append(tc).append(" ").append(value).append("\n");

	}

}
