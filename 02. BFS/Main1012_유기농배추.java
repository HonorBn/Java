import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/1012
*/

public class Main1012_유기농배추 {
	
	static BufferedReader br;
	static StringBuilder result;
	
	static Queue<int[]> que;
	static int M, N, K, cnt, map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		result = new StringBuilder();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			
			input();
			
			process();
			
			print(tc);
			
		}
		
		System.out.println(result.toString());
		
	}

	private static void input() throws IOException {
		
		cnt = 0;
		que = new LinkedList<>();
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = -1;
		}
		
	}

	private static void process() {
		
		for (int i = 0; i < M; i++)
			for (int j = 0; j < N; j++)
				if (map[i][j] == -1) bfs(i, j);
		
	}
	
	private static void bfs(int row, int col) {
		
		que.offer(new int[]{row, col});
		map[row][col] = ++cnt;
		
		int from[], fromR, fromC, toR, toC;
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromR = from[0];
			fromC = from[1];
			
			for (int i = 0; i < 4; i++) {
				
				toR = fromR + dir[i][0];
				toC = fromC + dir[i][1];
				
				if (toR >= 0 && toR < M &&
					toC >= 0 && toC < N &&
					map[toR][toC] == -1) {
					
					map[toR][toC] = cnt;
					que.offer(new int[]{toR, toC});
					
				}
				
			}
			
		}
		
	}

	private static void print(int tc) {
		
		result.append(cnt).append("\n");
		
	}
	
}
