import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/3055
*/

public class Main3055_탈출 {

	static boolean alive;
	static boolean[][] visit;
	static int r, c, minTime;
	static int[] start, cave;
	static int[][] map, dir;
	static ArrayList<Flood> floods;

	public static void main(String[] args) throws Exception {

		input();

		escape();

		print();

	}

	private static void input() throws IOException {

		dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new int[r][c];
		visit = new boolean[r][c];
		floods = new ArrayList<>();

		char[] line;
		for (int i = 0; i < r; i++) {
			line = br.readLine().trim().toCharArray();
			for (int j = 0; j < c; j++) {
				switch (line[j]) {
				case 'S':
					map[i][j] = 0;
					start = new int[] { i, j };
					break;
				case 'D':
					map[i][j] = 0;
					cave = new int[] { i, j };
					break;
				case '.':
					map[i][j] = 0;
					break;
				case 'X':
					map[i][j] = 1;
					break;
				case '*':
					map[i][j] = 2;
					floods.add(new Flood(i, j));
					break;
				}
			}
		}
	}

	private static void escape() {

		Queue<Flood> que = new LinkedList<>();

		for (Flood flood : floods)
			que.offer(flood);

		visit[start[0]][start[1]] = true;
		que.offer(new Hog(start[0], start[1], 0));

		Flood from;
		int fromR, fromC, toR, toC, cnt = 0;
		while (!que.isEmpty()) {

			from = que.poll();
			fromR = from.row;
			fromC = from.col;

			if (from instanceof Hog) {
				cnt = ((Hog) from).cnt;
				if (fromR == cave[0] && fromC == cave[1]) {
					alive = true;
					minTime = cnt;
					break;
				}
			}

			for (int i = 0; i < 4; i++) {

				toR = fromR + dir[i][0];
				toC = fromC + dir[i][1];

				if (toR >= 0 && toR < r && toC >= 0 && toC < c && map[toR][toC] != 1 && map[toR][toC] != 2) {

					if (from instanceof Hog && !visit[toR][toC]) {
						visit[toR][toC] = true;
						que.offer(new Hog(toR, toC, cnt + 1));
					} else if (toR != cave[0] || toC != cave[1]) {
						map[toR][toC] = 2;
						que.offer(new Flood(toR, toC));
					}

				}
			}
		}
	}

	private static void print() {

		if (alive)
			System.out.println(minTime);
		else
			System.out.println("KAKTUS");

	}
}

class Flood {
	int row, col;

	public Flood(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("홍수 [");
		builder.append(row);
		builder.append(", ");
		builder.append(col);
		builder.append("]");
		return builder.toString();
	}

}

class Hog extends Flood {
	int cnt;

	public Hog(int row, int col, int cnt) {
		super(row, col);
		this.cnt = cnt;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("도치 [");
		builder.append(row);
		builder.append(", ");
		builder.append(col);
		builder.append("][");
		builder.append(cnt);
		builder.append("]");
		return builder.toString();
	}
}
