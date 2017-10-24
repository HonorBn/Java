import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1863_Á¾±³ {

	static int n, m, religions[], inputs[][];

	public static void main(String[] args) throws Exception {

		input();

		religion();

		print();
	}

	private static void input() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		religions = new int[n + 1];
		Arrays.fill(religions, -1);

		inputs = new int[m][2];

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 2; j++) {
				inputs[i][j] = Integer.parseInt(st.nextToken());
			}
		}

	}

	private static void religion() {

		for (int i = 0; i < m; i++) {

			int rootA = find(inputs[i][0]);
			int rootB = find(inputs[i][1]);
			if (rootA != rootB)
				religions[rootB] = rootA;

		}
	}

	private static int find(int index) {

		if (religions[index] == -1)
			return index;
		return religions[index] = find(religions[index]);

	}

	private static void print() {

		int cnt = 0;
		for (int i = 1; i < n + 1; i++)
			if (religions[i] == -1)
				cnt++;
		System.out.println(cnt);

	}
}