import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=372&sca=50&sfl=wr_hit&stx=1092&sop=and
*/

public class Main1092_제곱수출력 {
	
	static long x, y, result;
	static final int DIVIDER = 20091024;
	
	public static void main(String[] args) throws IOException {

		input();
		
		calc();
		
		print();
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
	}
	
	private static void calc() {
		result = pow(x, y);
	}
	
	private static long pow(long x, long y) {
		if (y == 1) return x % DIVIDER;
		if (y == 0) return 1;
		long temp = pow(x, y / 2) % DIVIDER;
		temp = (temp * temp) % DIVIDER;
		if (y % 2 == 1) temp = (temp * (x % DIVIDER)) % DIVIDER;
		return temp;
	}
	
	private static void print() {
		System.out.println(result);
	}
}
