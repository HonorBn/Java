import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1097&sca=50&sfl=wr_hit&stx=1824&sop=and
*/

public class Main1824_스도쿠 {
	
	static boolean flag;
	static int[][] board = new int[9][9];
	static int[][] result = new int[9][9];
	static ArrayList<int[]> zeros = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		input();

		sudoku(0);

		print();

	}
	
	private static void input() throws IOException {
		
		int su;
		StringTokenizer st = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < 9; j++) {
				su = Integer.parseInt(st.nextToken());
				board[i][j] = su;
				if (su == 0) zeros.add(new int[]{i, j});
			}
		}
	}

	private static void sudoku(int zeroSeq) {
		
		if (zeroSeq == zeros.size()) {
			for (int i = 0; i < 9; i++) result[i] = Arrays.copyOf(board[i], 9);
			flag = true;
			return;
		}
		
		int row = zeros.get(zeroSeq)[0];
		int col = zeros.get(zeroSeq)[1];
		
		ArrayList<Integer> candiList = candiCheck(row, col, new boolean[10]);
		
		if (candiList.size() == 0) return;
		
		for (Integer candi : candiList) {
			board[row][col] = candi;
			sudoku(zeroSeq+1);
			if (flag) return;
			board[row][col] = 0;
		}
	}
	
	private static ArrayList<Integer> candiCheck(int row, int col, boolean[] check) {
		
		int startR;
		if (row >= 6) startR = 6;
		else if (row >= 3) startR = 3;
		else startR = 0;
		int startC;
		if (col >= 6) startC = 6;
		else if (col >= 3) startC = 3;
		else startC = 0;
		
		for (int i = startR; i < startR+3; i++)
			for (int j = startC; j < startC+3; j++)
				if (board[i][j] != 0) check[board[i][j]] = true;
		
		loop:
		for (int i = 1; i < 10; i++) {
			if (check[i]) continue;
			for (int j = 0; j < 9; j++)
				if (board[row][j] == i || board[j][col] == i) {
					check[i] = true;
					continue loop;
				}
		}
		
		ArrayList<Integer> candiList = new ArrayList<>();
		for (int i = 1; i < 10; i++) if (!check[i]) candiList.add(i);
		
		return candiList;
	}
	
	private static void print() {
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++)
				sb.append(result[i][j]).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb.toString());
	}

}
