import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=300&sca=50&sfl=wr_hit&stx=1027&sop=and
*/

public class Main1027_좋은수열 {
	
	static int n;
	static boolean flag;
	static StringBuilder lastSequence;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		fun(new StringBuilder());
		
		print();
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
	}
	
	private static void fun(StringBuilder sequence) {
		
		if (flag) return;
		if (sequence.length() == n) {
			flag = true;
			lastSequence = sequence;
			return;
		}
		
		StringBuilder toSeq = null;
		for (int i = 1; i <= 3; i++) {
			
			toSeq = new StringBuilder(sequence).append(i);
			if (check(toSeq)) fun(toSeq);
			
		}
	}
	
	private static boolean check(StringBuilder sequence) {
		
		int len = sequence.length();
		int half = len / 2;
		
		int compLen;
		CharSequence former, latter;
		for (int i = 1; i <= half; i++) {
			
			compLen = (len-1) - (i*2-1);
			for (int j = 0; j <= compLen; j++) {
				
				former = sequence.subSequence(j, j+i);
				latter = sequence.subSequence(j+i, j+i+i);
				
				if (former.equals(latter)) return false;
			}
		}
		return true;
	}
	
	private static void print() {
		System.out.println(lastSequence.toString());
	}
}
