import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=441&sca=50&sfl=wr_hit&stx=1161&sop=and
*/

public class Main1161_하노이의탑 {
	
	static StringBuilder builder = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine().trim());
    	fun(n, 1, 2, 3);
    	System.out.println(builder.toString());
    }
	
	public static void fun(int n, int from, int temp, int to) {
		if (n == 0) return;
		
		// n-1까지 temp에
		fun(n-1, from, to, temp);
		
		// n을 to에
		builder.append(n);
		builder.append(" : ");
		builder.append(from);
		builder.append(" -> ");
		builder.append(to);
		builder.append("\n");
		
		// n-1을 to에
		fun(n-1, temp, from, to);
		
	}
}
