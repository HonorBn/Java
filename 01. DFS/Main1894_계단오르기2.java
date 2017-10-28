import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1167&sca=50&sfl=wr_hit&stx=1894&sop=and
*/

public class Main1894_계단오르기2 {
	
	static int n, cnt;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		step(0);
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
	}
	
	private static void step(int layer) {
		
		if (layer == n) {
			cnt++;
			return;
		}
		
		if (layer+1 <= n) step(layer+1);
		if (layer+2 <= n) step(layer+2);
	}
	
	private static void print() {
		
		System.out.println(cnt);
		
	}
	
}
