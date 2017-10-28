import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=458&sca=50&sfl=wr_hit&stx=1175&sop=and
*/

public class Main1175_주사위던지기2 {
	
	static int n;
	static int m;
	static int[] arr;
	static ArrayList<StringBuilder> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		fun(0, 0);
		for (StringBuilder sb : list) System.out.println(sb.toString());
	}
	
	public static void fun(int loc, int sum) {
		if (loc == n) {
			if (sum == m) {
				StringBuilder sb = new StringBuilder();
				for (int su : arr) sb.append(su).append(" ");
				list.add(sb);
			}
			return;
		}
		for (int i = 1; i <= 6; i++) {
			arr[loc] = i;
			sum += i;
			fun(loc+1, sum);
			sum -= i;
		}
	}
}
