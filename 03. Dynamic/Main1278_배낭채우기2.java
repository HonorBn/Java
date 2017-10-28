import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=561&sca=50&sfl=wr_hit&stx=1278&sop=and
*/

public class Main1278_배낭채우기2 {
	
	static int n, w, dynamic[][], data[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		bag();
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		data = new int[n+1][2];
		dynamic = new int[n+1][w+1];
		
		for (int i = 1; i <= n; i++ ) {
			st = new StringTokenizer(br.readLine().trim());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void bag() {
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= w; j++)
				if (j >= data[i][0])
					dynamic[i][j] = Math.max(dynamic[i-1][j], dynamic[i-1][j-data[i][0]] + data[i][1]);
				else dynamic[i][j] = dynamic[i-1][j];
	}
	
	private static void print() {
		
		System.out.println(dynamic[n][w]);
		
	}
}
