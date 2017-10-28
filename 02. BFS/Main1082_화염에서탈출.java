import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=362&sca=50&sfl=wr_hit&stx=1082&sop=and
*/

public class Main1082_화염에서탈출 {

	static int r, c;
	static int[] start, end;
	static int[][] forest, dir;
	static Queue<Fire> que;

	public static void main(String[] args) throws IOException {

		que = new LinkedList<>();
		dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		forest = new int[r][c];

		char[] line = null;
		for (int i = 0; i < r; i++) {
			line = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				switch (line[j]) {
				case 'S':
					start = new int[] { i, j };
					break;
				case 'D':
					end = new int[] { i, j };
					break;
				case 'X':
					forest[i][j] = -1;
					break;
				case '*':
					forest[i][j] = -1;
					que.add(new Fire(i, j));
					break;
				}
			}
		}

		que.add(new Nom(start[0], start[1], 0));

		int newR = 0;
		int newC = 0;
		Fire temp = null;

		while (!que.isEmpty()) {

			temp = que.poll();
			if (temp.row == end[0] && temp.col == end[1] && temp instanceof Nom) {
				System.out.println(((Nom) temp).count);
				return;
			}

			for (int j = 0; j < dir.length; j++) {

				newR = temp.row + dir[j][0];
				newC = temp.col + dir[j][1];

				if (newR >= 0 && newR < r && newC >= 0 && newC < c) {

					if (temp instanceof Nom) {

						if (forest[newR][newC] == 0) {

							que.add(new Nom(newR, newC, ((Nom) temp).count + 1));
							forest[newR][newC] = ((Nom) temp).count + 1;
						}

					} else if (forest[newR][newC] != -1) {

						if (newR != end[0] || newC != end[1]) {

							que.add(new Fire(newR, newC));
							forest[newR][newC] = -1;

						}
					}
				}
			}
		}

		System.out.println("impossible");
	}
}

class Nom extends Fire {
	int count;

	public Nom(int row, int col, int count) {
		super(row, col);
		this.count = count;
	}
}

class Fire {
	int row;
	int col;

	public Fire(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
}
