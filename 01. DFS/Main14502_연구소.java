import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/14502
*/

public class Main14502_연구소 {
	
	static int n, m, maxSafe, map[][], dir[][];
	static ArrayList<int[]> virus;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		research(0, 0);
		
		print();
		
	}

	private static void input() throws IOException {
		
		virus = new ArrayList<>();
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int su;
		map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < m; j++) {
				su = Integer.parseInt(st.nextToken());
				map[i][j] = su;
				if (su == 2) virus.add(new int[]{i, j});
			}
		}
	}
	
	private static void research(int index, int cnt) {
		
		if (cnt == 3) {
			
			int safe = virus();
			if (safe > maxSafe) maxSafe = safe;
			
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (map[i][j] == 3) map[i][j] = 0;
				}
			}
			return;
		}
		
		int row, col;
		for (int i = index; i < n * m; i++) {
			row = i / m; col = i % m;
			if (map[row][col] == 0) {
				map[row][col] = 1;
				research(i, cnt+1);
				map[row][col] = 0;
			}
		}
		
	}
	
	private static int virus() {
		
		Queue<int[]> que = new LinkedList<>();
		
		for (int[] v : virus) que.offer(v);
		
		int fromR, fromC, toR, toC, from[];
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromR = from[0];
			fromC = from[1];
			
			for (int to = 0; to < 4; to++) {
				
				toR = fromR + dir[to][0];
				toC = fromC + dir[to][1];
				
				if (toR >= 0 && toR < n &&
					toC >= 0 && toC < m &&
					map[toR][toC] == 0) {
					
					map[toR][toC] = 3;
					que.offer(new int[]{toR, toC});
				}
				
			}
			
		}
		
		int safe = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 0) safe++;
			}
		}
		
		return safe;
	}
	
	private static void print() {
		
		System.out.println(maxSafe);
		
	}
}
