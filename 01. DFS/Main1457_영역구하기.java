import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=729&sca=50&sfl=wr_hit&stx=1457&sop=and
*/

public class Main1457_영역구하기 {
	
	static int m, n, k, cnt, cell, dir[][], square[][];
	static ArrayList<Integer> res;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (square[i][j] == 0) {
					cnt++;
					cell = 0;
					count(i, j);
					res.add(cell);
				}
			}
		}
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		dir = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		int former[], latter[];
		square = new int[n][m];
		res = new ArrayList<>();
		for (int part = 0; part < k; part++) {
			st = new StringTokenizer(br.readLine());
			former = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			latter = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			for (int i = former[0]; i < latter[0]; i++)
				for (int j = former[1]; j < latter[1]; j++)
					square[i][j] = -1;
		}
		
	}
	
	private static void count(int row, int col) {
		
		square[row][col] = ++cell;
		
		int toR, toC;
		for (int i = 0; i < dir.length; i++) {
			
			toR = row + dir[i][0];
			toC = col + dir[i][1];
			
			if (toR >= 0 && toR < n &&
				toC >= 0 && toC < m &&
				square[toR][toC] == 0) count(toR, toC);
		}
		
	}
	
	private static void print() {
		
		Collections.sort(res, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1-o2;
			}
		});
		
		StringBuilder sort = new StringBuilder(); 
		for (int i : res) sort.append(i).append(" ");
		System.out.println(cnt);
		System.out.print(sort.toString());
	}
}
