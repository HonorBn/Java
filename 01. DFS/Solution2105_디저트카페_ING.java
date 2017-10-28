import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   SW Expert Academy
*/

/* Input
2              
4                
9 8 9 8
4 6 9 4
8 7 7 8
4 5 3 5
5                
8 2 9 6 6
1 9 3 3 4
8 2 3 3 6
4 3 4 4 9
7 4 6 3 5
*/

/* Output
#1 6
#2 -1
*/

public class Solution2105_디저트카페_ING {
	
	static BufferedReader br;
	static StringBuilder result;
	
	static int N, startR, startC, maxDessert, map[][], dir[][];
	static boolean checkDessert[], visit[][];
	
	public static void main(String[] args) throws IOException {
		
		result = new StringBuilder();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		int value;
		
		for (int tc = 1; tc <= T; tc++) {
			
			input();
			
			value = process();
			
			print(tc, value);
			
		}
		
		System.out.println(result.toString());
		
	}

	private static void input() throws IOException {
		
		maxDessert = -1;
		checkDessert = new boolean[101];
		dir = new int[][]{	{1, 1}, {1, -1}, {-1, -1}, {-1, 1}	};
		
		N = Integer.parseInt(br.readLine().trim());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
	}

	private static int process() {
		
		for (int i = 0; i < N-2; i++) {
			for (int j = 1; j < N-1; j++) {
				startR = i; startC = j;
				checkDessert[map[i][j]] = true;
				devour();
			}
		}
		
		return 0;
		
	}
	
	private static void devour() {
		
		
		int toR, toC;
		int rightCnt = 1, leftCnt = 1;
		int rightSum, leftSum, restSum;
		
		rightSum = 0;
		while (true) {
			
			toR = dir[0][0] * rightCnt;
			toC = dir[0][1] * rightCnt;
			
			if (toR >= 0 && toR < N &&
				toC >= 0 && toC < N &&
				!checkDessert[map[toR][toC]]) {
				
				rightSum += map[toR][toC];
				checkDessert[map[toR][toC]] = true;
				
				leftSum = 0;
				
				left:
				while (true) {
					
					toR = dir[1][0] * leftCnt;
					toC = dir[1][1] * leftCnt;
					
					if (toR >= 0 && toR < N &&
							toC >= 0 && toC < N &&
							!checkDessert[map[toR][toC]]) {
						
						leftSum += map[toR][toC];
						checkDessert[map[toR][toC]] = true;
						
						restSum = 0;
						for (int i = 1; i <= rightCnt; i++) {
							
							toR = dir[2][0] * i;
							toC = dir[2][1] * i;
							
							if (!checkDessert[map[toR][toC]]) {
								
								restSum += map[toR][toC];
								checkDessert[map[toR][toC]] = true;
								
							} else continue left;
						}
						
						for (int i = 1; i <= leftCnt; i++) {
							
							toR = dir[3][0] * i;
							toC = dir[3][1] * i;
							
							if (!checkDessert[map[toR][toC]]) {
								
								restSum += map[toR][toC];
								checkDessert[map[toR][toC]] = true;
								
							} else continue left;
						}
						
						maxDessert = Math.max(maxDessert, leftSum + rightSum + restSum);
						
					} else break;
					
					leftCnt++;
				}
				
			} else break;
			
			rightCnt++;
		}
		
		
	}

	private static void print(int tc, int value) {
		
		result.append("#").append(tc).append(" ").append(value).append("\n");
		
	}
	
}
