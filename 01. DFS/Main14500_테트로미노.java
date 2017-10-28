import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/14500
*/

// 이미 두었던 위치와 모양을 탐색 안하는 방법 필요

public class Main14500_테트로미노 {
	
	static int r, c, maxSum, map[][], dir[][];
	static boolean visit[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		start();
		
		print();
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

	private static void start() {
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				visit = new boolean[r][c];
				visit[i][j] = true;
				common(i, j, 0, 1);
				except(i, j);
			}
		}
		
	}

	private static void common(int fromR, int fromC, int sum, int cnt) {
		
		sum += map[fromR][fromC];
		
		if (cnt == 4) {
			if (sum > maxSum) maxSum = sum;
			return;
		}
		
		int toR, toC;
		for (int i = 0; i < 4; i++) {
			toR = fromR + dir[i][0];
			toC = fromC + dir[i][1];
			
			if (toR >= 0 && toR < r &&
				toC >= 0 && toC < c &&
				!visit[toR][toC] ) {
				
				visit[toR][toC] = true;
				common(toR, toC, sum, cnt+1);
				visit[toR][toC] = false;
			}
		}
	}
	
	private static void except(int centerR, int centerC) {
		
		int su, cnt = 0, min = 1000;
		int sum = map[centerR][centerC];
		
		int nearR, nearC;
		for (int i = 0; i < 4; i++) {
			
			nearR = centerR + dir[i][0];
			nearC = centerC + dir[i][1];
			
			if (nearR < 0 || nearR >= r || nearC < 0 || nearC >= c) continue;
			
			su = map[nearR][nearC];
			sum += su;
			if (min > su) min = su;
			cnt++;
		}
		
		if (cnt == 3 && sum > maxSum) maxSum = sum;
		if (cnt == 4 && sum-min > maxSum) maxSum = sum-min;
	}
	
	private static void print() {
		System.out.println(maxSum);
	}

}
