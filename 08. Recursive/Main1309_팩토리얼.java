import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=589&sca=50&sfl=wr_hit&stx=1309&sop=and
*/

public class Main1309_팩토리얼 {
	
	public static void main(String[] args) throws IOException {
    	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine().trim());

		System.out.println(fun(n));
    	}
	
	public static long fun(int n) {
		
		if (n == 0) return 1;
		else if (n == 1) System.out.printf("%d! = %d\n", n, n);
		else System.out.printf("%d! = %d * %d!\n", n, n, n-1);
		return n * fun(--n);
		
	}
}
