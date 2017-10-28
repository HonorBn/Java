import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/3109
*/

public class Main3109_빵집 {
	
	static boolean flag;
	static int r, c, maxSize, map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		dir = new int[][]{	{-1, 1}, {0, 1}, {1 ,1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		char item;
		char[] line;
		for (int i = 0; i < r; i++) {
			line = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				item = line[j];
				switch (item) {
				case '.':
					map[i][j] = 0;
					break;
				case 'x':
					map[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < r; i++) {
			if (map[i][1] == 1) continue;
			flag = false;
			dfs(i, 0);
		}
		
		System.out.println(maxSize);
		
	}
	
	public static void dfs(int fromR, int fromC) {
		
		if (fromC == c-1) {
			map[fromR][fromC] = 1;
			maxSize++;
			flag = true;
			return;
		}
		
		int toR, toC;
		for (int i = 0; i < 3; i++) {
			
			toR = fromR + dir[i][0];
			toC = fromC + dir[i][1];
			
			if (toR >= 0 && toR < r &&
				toC >= 1 && toC < c &&
				map[toR][toC] == 0 && !flag) {
				map[toR][toC] = 1;
				dfs(toR, toC);
			}
		}
	}

}
