import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1162&sca=50&sfl=wr_hit&stx=1889&sop=and
*/

public class Main1889_N퀸1 {
	
	static int n, count;
	static boolean[] left, right, result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		count = 0;
		left = new boolean[2*n];
		right = new boolean[2*n];
		result = new boolean[n];
		
		fun(0);
		
		System.out.println(count);
	}
	
	public static void fun(int row) {
		
		if (row == n) {
			count++;
			return;
		}
		
		for (int col = 0; col < n; col++){
			
			if (result[col] || left[row+col] || right[n-row+col-1]) continue;
			
			result[col] = true; left[row+col] = true;
			right[n-row+col-1] = true;
			fun(row+1);
			result[col] = false; left[row+col] = false;
			right[n-row+col-1] = false;
			
		}
	}
}
