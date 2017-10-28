import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=880&sca=50&sfl=wr_hit&stx=1607&sop=and
*/

public class Main1607_학교가는길 {
	
	static int r, c, k, cnt, map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		school(0, 0); 
		
		print();
		
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{1, 0}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		map = new int[r][c];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
		}
	}
	
	private static void school(int fromR, int fromC) {
		
		if (fromR == r - 1 && fromC == c - 1) {
			cnt++;
			return;
		}
		
		int toR, toC;
		for (int i = 0; i < 2; i++) {
			toR = fromR + dir[i][0];
			toC = fromC + dir[i][1];
			
			if (toR < r && toC < c &&
				map[toR][toC] == 0) {
				
				school(toR, toC);
			}
		}
		
	}
	
	private static void print() {
		
		System.out.println(cnt);
		
	}
}
