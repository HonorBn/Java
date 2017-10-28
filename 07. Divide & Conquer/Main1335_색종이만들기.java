import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=614&sca=50&sfl=wr_hit&stx=1335&sop=and
*/

public class Main1335_색종이만들기 {
	
	static int n;
	static int[] Cnt;
	static int[][] paper;
	static boolean[][] spot;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		Cnt = new int[2];
		paper = new int[n][n];
		spot = new boolean[][]{ {true, true}, {true, false}, {false, true}, {false, false} };
		
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		cutPaper(0, 0, n);
		
		System.out.println(Cnt[0]);
		System.out.println(Cnt[1]);
	}

	private static void cutPaper(int row, int col, int len) {
		
		if (len == 1 || equal(row, col, len)) {
			Cnt[paper[row][col]]++;
			return;
		}
		
		int lenPart = len/2;
		for (int i = 0; i < spot.length; i++)
			cutPaper( (spot[i][0])? row : row+lenPart,
					  (spot[i][1])? col : col+lenPart, lenPart);
	}
	
	private static boolean equal(int row, int col, int len) {
		int sum = 0;
		for (int i = row; i < row+len; i++)
			for (int j = col; j < col+len; j++)
				sum += paper[i][j];
		return sum == 0 || sum == len * len;
	}
}
