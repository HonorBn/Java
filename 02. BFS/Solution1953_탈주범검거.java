import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   SW Expert Academy
*/

public class Solution1953_탈주범검거 {
	
	static BufferedReader br;
	static StringBuilder result;
	
	static int N, M, R, C, L, dir[][];
	static boolean map[][][], possible[][];
	
	static final boolean[] T0 = new boolean[]{false, false, false, false};
	static final boolean[] T1 = new boolean[]{true, true, true, true};
	static final boolean[] T2 = new boolean[]{true, true, false, false};
	static final boolean[] T3 = new boolean[]{false, false, true, true};
	static final boolean[] T4 = new boolean[]{true, false, false, true};
	static final boolean[] T5 = new boolean[]{false, true, false, true};
	static final boolean[] T6 = new boolean[]{false, true, true, false};
	static final boolean[] T7 = new boolean[]{true, false, true, false};
	
	public static void main(String[] args) throws IOException {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		result = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine().trim());
		
		int value;
		
		for (int tc = 1; tc <= T; tc++) {
			
			input();
			
			value = process();
			
			print(tc, value);
			
		}
		
		System.out.println(result.toString());
		
	}
	
	public static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		map = new boolean[N][M][4];
		possible = new boolean[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < M; j++) {
				switch (Integer.parseInt(st.nextToken())) {
				case 1:
					map[i][j] = T1; break;
				case 2:
					map[i][j] = T2; break;
				case 3:
					map[i][j] = T3; break;
				case 4:
					map[i][j] = T4; break;
				case 5:
					map[i][j] = T5; break;
				case 6:
					map[i][j] = T6; break;
				case 7:
					map[i][j] = T7; break;
				default:
					map[i][j] = T0; break;
				}
			}
		}
		
	}
	
	public static int process() {
		
		Queue<int[]> que = new LinkedList<>();
		
		que.offer(new int[]{R, C, 1});
		possible[R][C] = true;
		
		int cnt, toR, toC, fromR, fromC, from[];
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromR = from[0];
			fromC = from[1];
			cnt = from[2];
			
			if (cnt == L) break;
			
			for (int i = 0; i < 4; i++) {
				
				toR = fromR + dir[i][0];
				toC = fromC + dir[i][1];
				
				if (toR >= 0 && toR < N &&
					toC >= 0 && toC < M &&
					!possible[toR][toC] &&
					map[toR][toC] != T0 &&
					check(i, map[fromR][fromC], map[toR][toC])) {
					
					que.offer(new int[]{toR, toC, cnt+1});
					possible[toR][toC] = true;
					
				}
				
			}
			
		}
		
		return getArea();
		
	}
	
	public static boolean check(int dir, boolean[] fromTunel, boolean[] toTunel) {
		switch (dir) {
		case 0:
			return fromTunel[0] && toTunel[1];
		case 1:
			return fromTunel[1] && toTunel[0];
		case 2:
			return fromTunel[2] && toTunel[3];
		default:
			return fromTunel[3] && toTunel[2];
		}
	}
	
	public static int getArea() {
		int area = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (possible[i][j]) area++;
			}
		}
		return area;
	}
	
	public static void print(int tc, int value) {
		
		result.append("#").append(tc).append(" ").append(value).append("\n");
		
	}
}
