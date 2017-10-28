import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=332&sca=50&sfl=wr_hit&stx=1053&sop=and
*/

public class Main1053_피보나치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = 0;
		while ( (n = Integer.parseInt(br.readLine().trim())) != -1) {  // 조건문에 선언 대입 사용 익히기!!
			
			if  (n == 0) {
				System.out.println(0);
				continue;
			}
			//memo = new int[n+1];
			//System.out.println(fivonacci1(n));
			System.out.println(fivonacci2(n)[0][1]);
		}
		
	}
	
	// 메모하기 방법
	static int[] memo = null;
	public static int fivonacci1(int n) {
		if (n == 1 || n == 2) return 1;
		if (memo[n] != 0) return memo[n];
		return memo[n] = fivonacci1(n-2) + fivonacci1(n-1);
	}
	
	static int[][] unit = { {1, 1}, {1, 0} };
	private static int[][] multiple(int[][] a, int[][] b) {
		int[][] temp = new int[2][2];
		temp[0][0] = (a[0][0]*b[0][0] + a[0][1]*b[1][0]) % 10000;
		temp[0][1] = (a[0][0]*b[0][1] + a[0][1]*b[1][1]) % 10000;
		temp[1][0] = (a[1][0]*b[0][0] + a[1][1]*b[1][0]) % 10000;
		temp[1][1] = (a[1][0]*b[0][1] + a[1][1]*b[1][1]) % 10000;
		return temp;
	}
	public static int[][] fivonacci2(int n) {
		if (n == 1) return unit;
		int[][] temp = fivonacci2(n/2);
		temp = multiple(temp, temp);
		if (n % 2 == 1) temp = multiple(temp, unit);
		return temp;
	}
}
