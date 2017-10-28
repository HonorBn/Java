import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* URL
   SW Expert Academy
*/

public class Solution1949_등산로조성 {
	
	static BufferedReader br;
	static StringBuilder result;
	
	static int N, K, maxLength, map[][], dir[][];
	static boolean visit[][];
	
	static ArrayList<int[]> peekList;
	
	public static void main(String[] args) throws IOException {
		
		result = new StringBuilder();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			
			input();
			
			process();
			
			print(tc, maxLength);
			
		}
		
		System.out.println(result.toString());
		
	}
	
	public static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		int su, high = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				su = Integer.parseInt(st.nextToken());
				high = Math.max(su, high);
				map[i][j] = su;
			}
		}
		
		maxLength = 0;
		peekList = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == high) peekList.add(new int[]{i, j});
			}
		}
		
	}
	
	public static void process() {
		
		int peekR, peekC;
		
		for (int[] peek : peekList) {
			
			peekR = peek[0]; peekC = peek[1];
			
			visit[peekR][peekC] = true;
			makeRoad(peekR, peekC, 1, 1);
			visit[peekR][peekC] = false;
			
		}
		
	}
	
	public static void makeRoad(int fromR, int fromC, int length, int cut) {
		
		int toR, toC;
		for (int i = 0; i < 4; i++) {
			
			toR = fromR + dir[i][0];
			toC = fromC + dir[i][1];
			
			if (toR >= 0 && toR < N &&
				toC >= 0 && toC < N	&&
				!visit[toR][toC])
				
				if (map[fromR][fromC] > map[toR][toC]) {
					
					visit[toR][toC] = true;
					makeRoad(toR, toC, length+1, cut);
					visit[toR][toC] = false;
					
				} else if (cut != 0) {
					
					visit[toR][toC] = true;
					
					for (int k = 1; k <= K; k++) {
						
						map[toR][toC] -= k;
						if (map[fromR][fromC] > map[toR][toC])
							makeRoad(toR, toC, length+1, cut-1);
						 map[toR][toC] += k;
						
					}
					
					visit[toR][toC] = false;
					
				}
			
		}
		
		maxLength = Math.max(length, maxLength);
		
	}
	
	public static void print(int tc, int value) {
		
		result.append("#").append(tc).append(" ").append(value).append("\n");
		
	}
}
