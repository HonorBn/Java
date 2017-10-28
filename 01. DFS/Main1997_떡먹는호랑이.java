import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1270&sca=50&sfl=wr_hit&stx=1997&sop=and
*/

public class Main1997_떡먹는호랑이 {

	static int k;
	static int aCnt = 0;
	static int bCnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		fun(d);
		
		for (int i = k/bCnt; i > 0; i--) {
			if ((k - i*bCnt) % aCnt == 0) {
				System.out.println((k - i*bCnt) / aCnt);
				System.out.println(i);
				break;
			}
		}
	}
	
	public static void fun(int day) {
		
		if (day == 1) {
			aCnt++;
			return;
		}
		
		if (day == 2) {
			bCnt++;
			return;
		}
		
		fun(day-1);
		fun(day-2);
	}
}
