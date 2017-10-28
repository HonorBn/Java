import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=731&sca=50&sfl=wr_hit&stx=1459&sop=and
*/

public class Main1459_숫자고르기 {
	
	static int n;
	static int[] num;
	static ArrayList<Integer> temp;
	static ArrayList<Integer> result;
	
	public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine().trim());
    	num = new int[n];
    	for (int i = 0; i < n; i++) num[i] = Integer.parseInt(br.readLine().trim());
    	temp = new ArrayList<Integer>();
    	result = new ArrayList<Integer>();
    	
    	for (int i = 1; i <= n; i++) {
    		fun(i);
    	}
    	
    	System.out.println(result.size());
    	for (int res : result) System.out.println(res);
    	
    }
	
	public static void fun(int su) {
		
		if (!temp.isEmpty()) {
			for (int i = 0; i < temp.size(); i++) {
				if (su == temp.get(i)) {
					if (i == 0) result.add(su);
					temp.clear();
					return;
				}
			}
		}
		temp.add(su);
		fun(num[su-1]);
		
	}
}
