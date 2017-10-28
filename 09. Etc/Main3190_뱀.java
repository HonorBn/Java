import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/3190
*/

public class Main3190_뱀 {
	
	static int n, k, l, resCnt, map[][], move[][];
	static HashMap<Integer, Boolean> turns;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		dummy();
		
		print();
	}

	private static void input() throws IOException {
		
		move = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		k = Integer.parseInt(br.readLine().trim());
		
		map = new int[n+2][n+2];
		
		for (int i = 0; i < n+2; i++)
		for (int j = 0; j < n+2; j++)
			if (i == 0 || i == n+1 || j == 0 || j == n+1) map[i][j] = -1;
		
		StringTokenizer st;
		for (int i = 0; i < k; i++ ) {
			st = new StringTokenizer(br.readLine().trim());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -2;
		}
		
		turns = new HashMap<>();
		
		l= Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine().trim());
			turns.put(Integer.parseInt(st.nextToken()), st.nextToken().equals("L"));
		}
		
	}
	
	private static void dummy() {
		
		int row = 1, col = 1, len = 1, dir = 3, tailR = 1, tailC = 1, tailRtemp, tailCtemp;
		resCnt = 1;	map[row][col] = resCnt;
		
		while (true) {
			
			row = row + move[dir][0]; col = col + move[dir][1]; resCnt++;
			
			if (map[row][col] == -1 || map[row][col] > 0) break;
			
			if (map[row][col] != -2) {
				
				if (len == 1) {
					
					map[tailR][tailC] = 0;
					tailR = row; tailC = col;
					
				} else {
					
					for (int i = 0; i < 4; i++) {
						tailRtemp = tailR + move[i][0]; tailCtemp = tailC + move[i][1];
						if (map[tailRtemp][tailCtemp] - map[tailR][tailC] == 1) {
							map[tailR][tailC] = 0;
							tailR = tailRtemp; tailC = tailCtemp;
							break;
						}
					}
				}
			} else len++;
			
			map[row][col] = resCnt;
			
			if (turns.containsKey(resCnt-1)) {
				if (turns.get(resCnt-1)) dir = (dir < 2)? 5-(3-dir):3-dir;
				else dir = (dir < 2)? 3-dir:1-(3-dir);
			}
		}
		
	}

	private static void print() {
		
		System.out.println(resCnt-1);
		
	}
}
