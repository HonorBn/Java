import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1006&sca=50&sfl=wr_hit&stx=1733&sop=and
*/

public class Main1733_오목 {
	
	static int[][] board, direction;
	
	public static void main(String[] args) throws IOException {
		
		board = new int[19][19];
		direction = new int[][]{ {-1, 0}, {-1,  1}, {0,  1}, { 1,  1},
								 { 1, 0}, { 1, -1}, {0, -1}, {-1, -1} };
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		for (int i = 0; i < 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 19; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] result = null;
		loop:
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if (board[i][j] != 0) {
					for (int k = 0; k < direction.length; k++) {
						if (fun(i, j, k, 0, board[i][j])) {
							if (k != 5) result = new int[]{i, j};
							else result = new int[]{i+4, j-4};
							break loop;
						}
					}
				}
			}
		}
		
		if (result == null) System.out.println(0);
		else {
			System.out.println(board[result[0]][result[1]]);
			System.out.println((result[0]+1) + " " + (result[1]+1));
		}
		
	}
	
	public static boolean fun(int row, int col, int dir, int cnt, int dol) {
		
		int newR = row + direction[dir][0];
		int newC = col + direction[dir][1];
		int preR = row - direction[dir][0];
		int preC = col - direction[dir][1];
		
		if (cnt == 4) {
			
			if ( newR < 0 || newR >= 19 ||
				 newC < 0 || newC >= 19 || 
				 board[newR][newC] != dol) return true;
			
		} else if (cnt < 4) {
			
			if ( cnt == 0 &&
				 preR >= 0 && preR < 19 &&
				 preC >= 0 && preC < 19 && 
				 board[preR][preC] == dol) return false;
			
			if ( newR >= 0 && newR < 19 &&
				 newC >= 0 && newC < 19 && 
				 board[newR][newC] == dol) return fun(newR, newC, dir, ++cnt, dol);
			
		}
		
		return false;
	}
}
