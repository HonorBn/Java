import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=449&sca=50&sfl=wr_hit&stx=1169&sop=and
*/

public class Main1169_주사위던지기1 {
	 
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
		
		switch (m) {
		case 1:
			fun1(0);
			break;
		case 2:
			fun2(0);
			break;
		case 3:
			fun3(0);
			break;
		}
		for (StringBuilder sb : list) System.out.println(sb.toString());
	}
	
	public static void fun1(int loc) {
		if (loc == n) {
			StringBuilder sb = new StringBuilder();
			for (int su : arr) sb.append(su).append(" ");
			list.add(sb);
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			arr[loc] = i;
			fun1(loc+1);
		}
	}
	
	public static void fun2(int loc) {
		if (loc == n) {
			StringBuilder sb = new StringBuilder();
			for (int su : arr) sb.append(su).append(" ");
			list.add(sb);
			return;
		}
		
		for (int i = (loc-1 < 0)? 1:arr[loc-1]; i <= 6; i++) {
			arr[loc] = i;
			fun2(loc+1);
		}
	}
	
	public static void fun3(int loc) {
		if (loc == n) {
			StringBuilder sb = new StringBuilder();
			for (int su : arr) sb.append(su).append(" ");
			list.add(sb);
			return;
		}
		
		for (int i = 1; i <= 6; i++) {
			if (check(loc, i)) continue;
			arr[loc] = i;
			fun3(loc+1);
		}
	}
	
	public static boolean check(int loc, int i) {
		boolean check = false;
		for (int j = 1; j < n; j++) {
			if (check) break;
			check = ( (loc-j < 0) ? 0 : arr[loc-j] ) == i;
		}
		return check;
	}
}
