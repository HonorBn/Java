import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=935&sca=50&sfl=wr_hit&stx=1662&sop=and
*/

public class Main1662_비숍 {
	
	static int n, maxCnt, whiteCnt, blackCnt, leftNum[];
	static boolean rightCheck[], possibles[][];
	
	public static void main(String[] args) throws IOException {

		input();
		
		bisyob(0, 0);
		bisyob(1, 0);
		
		print();

	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		maxCnt = 2 * n - 1;
		leftNum = new int[maxCnt];
		rightCheck = new boolean[maxCnt];
		possibles = new boolean[maxCnt][maxCnt];
		
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++)
				if (st.nextToken().equals("1")) possibles[i+j][n-i+j-1] = true;
		}
	}

	private static void bisyob(int leftIndex, int cnt) {
		
		if (leftIndex >= maxCnt) {
			if (leftIndex % 2 == 0 && cnt > whiteCnt) whiteCnt = cnt;
			if (leftIndex % 2 == 1 && cnt > blackCnt) blackCnt = cnt;
			return;
		}
		
		boolean[] candis = possibles[leftIndex];
		
		for (int rightIndex = 0; rightIndex < maxCnt; rightIndex++)
			if (candis[rightIndex] && !rightCheck[rightIndex]) {
				leftNum[leftIndex] = rightIndex;
				rightCheck[rightIndex] = true;
				bisyob(leftIndex+2, cnt+1);
				leftNum[leftIndex] = 0;
				rightCheck[rightIndex] = false;
			}
		
		bisyob(leftIndex+2, cnt);
	}
	
	private static void print() {
		System.out.println(whiteCnt + blackCnt);
	}
}
