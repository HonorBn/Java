import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/12100
*/

public class Main12100_2048_Easy {
	
	static int n, maxValue, map[][], mapTemp[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		fun(map, 0);
		
		print();
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		map = new int[n][n];
		
		StringTokenizer st;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < n; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
	}

	private static void fun(int[][] map, int cnt) {
		
		if (cnt == 5) {
			int max = findMax(map);
			maxValue = Math.max(maxValue, max);
			return;
		}
		
		for (int i = 0; i < 4; i++)
			
			fun(sum(i, map), cnt+1);
			
	}
	
	private static int[][] sum(int dir, int[][] map) {
		
		boolean row = dir > 1;
		boolean plus = dir % 2 == 0;
		
		int interval = (plus)? 1:-1;
		int s = (plus)? 0:n-1;
		int e = (plus)? n:-1;
		
		int id, pres, keep;
		
		int[][] renew = new int[n][n];
		
		if (row) { 
			
			for (int i = 0; i < n; i++) {
				
				id = s; keep = 0;
				
				for (int j = s; j != e; j += interval) {
					
					if (map[i][j] == 0) continue;
					
					pres = map[i][j];
					
					if (keep == 0) {
						keep = pres;
					} else if (keep == pres) {
						renew[i][id] = keep + pres;
						id += interval;
						keep = 0;
					} else {
						renew[i][id] = keep;
						id += interval;
						keep = pres;
					}
				}
				
				renew[i][id] = keep;
			}
			
		} else {
			
			for (int j = 0; j < n; j++) {
				
				id = s; keep = 0;
				
				for (int i = s; i != e; i += interval) {
					
					if (map[i][j] == 0) continue;
					
					pres = map[i][j];
					
					if (keep == 0) {
						keep = pres;
					} else if (keep == pres) {
						renew[id][j] = keep + pres;
						id += interval;
						keep = 0;
					} else {
						renew[id][j] = keep;
						id += interval;
						keep = pres;
					}
				}
				
				renew[id][j] = keep;
			}
		}
		return renew;
	}
	
	private static int findMax(int[][] map) {
		
		int max = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (map[i][j] > max) max = map[i][j];
		
		return max;
		
	}
	
	private static void print() {
		
		System.out.println(maxValue);
		
	}

}
