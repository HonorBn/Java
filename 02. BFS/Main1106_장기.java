import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=386&sca=50&sfl=wr_hit&stx=1106&sop=and
*/

public class Main1106_장기 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] horse = {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1};
		int[] minimi = {Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1};
		
		int[][] board = new int[n][m];
		Queue<int[]> que = new LinkedList<>();
		int[][] dir = { {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2} };
		
		que.add(horse);
		
		int cnt = 0;
		int newRow = 0;
		int newCol = 0;
		int[] spot = null;
		
		loop:
		while (!que.isEmpty()) {
			
			spot = que.poll();
			cnt = board[spot[0]][spot[1]];
			
			if (spot[0] == minimi[0] && spot[1] == minimi[1]) break;
			
			for (int i = 0; i < dir.length; i++) {
				
				newRow = spot[0] + dir[i][0];
				newCol = spot[1] + dir[i][1];
				
				if (newRow == horse[0] && newCol == horse[1]) continue;
				if (newRow == minimi[0] && newCol == minimi[1]) break loop;
				
				if (newRow >= 0 && newRow < n &&
						newCol >= 0 && newCol < m &&
							board[newRow][newCol] == 0) {
					board[newRow][newCol] = cnt+1;
					que.add(new int[]{newRow, newCol});
				}
			}
		}

		System.out.println(cnt+1);
		
	}
}
