import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/14503
*/

public class Main14503_로봇청소기 {
	
	static int r, c, area, start[], map[][], dir[][];
	static boolean stop;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		clean(start[0], start[1], start[2]);
		
		print();
		
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {0, 1}, {1, 0}, {0, -1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		start = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		
		map = new int[r][c];
		for (int i = 0; i < r; i++) {
			 st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < c; j++) {
				map[i][j] = -1 * Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	private static void clean(int fromR, int fromC, int move) {
		
		if(map[fromR][fromC] == 0) map[fromR][fromC] = ++area;
		
		int toR, toC;
		
		for (int i = 0; i < 4; i++) {
			
			if (stop) return;
			
			move = left(move);
			toR = fromR + dir[move][0];
			toC = fromC + dir[move][1];
			
			if (toR >=0 && toR < r &&
				toC >=0 && toC < c &&
				map[toR][toC] == 0) clean(toR, toC, move);
		}
		
		int back = back(move);
		toR = fromR + dir[back][0];
		toC = fromC + dir[back][1];
		
		if (map[toR][toC] != -1) clean(toR, toC, move);
		else {
			stop = true;
			return;
		}
		
	}
	
	private static int left(int dir) {
		return (dir < 1)? dir+3:dir-1;
	}
	
	private static int back(int dir) {
		return (dir < 2)? dir+2:dir-2;
	}
	
	private static void print() {
		
		System.out.println(area);
		
	}
}
