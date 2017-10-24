import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* ÀÔ·Â µ¥ÀÌÅÍ
8
2 3
 */

public class Main2543_Å¸ÀÏÃ¤¿ì±â {
	
	static int n;
	static int[] hole;
	static int[][] floor;
	static boolean[][] divide;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		input();
		
		divideTile(0, 0, n, hole[0], hole[1]);
		
		print();
	}
	
	private static void input() throws NumberFormatException, IOException {
		
		divide = new boolean[][]{ {true, true}, {true, false}, {false, true}, {false, false} };
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		floor = new int[n][n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		hole = new int[]{Integer.parseInt(st.nextToken()),
						 Integer.parseInt(st.nextToken())};
	}
	
	// »çºÐ¸é ¼ø¼­
	// 1 2
	// 3 4
	// row, col : ¹Ù´Ú ÁÂ»ó´Ü ÁÂÇ¥ /  len : ¹Ù´Ú ¸é ±æÀÌ /  x, y : ±¸¸Û(¶Ç´Â Å¸ÀÏ) ÁÂÇ¥
	private static void divideTile(int row, int col, int len, int x, int y) {
		
		// Å»ÃâÇÏ±â
		if (len == 1) return;
		
		int half = len / 2;
		int site = findTile(row, col, len, x, y);
		
		int newR = 0;
		int newC = 0;
		int newX = 0;
		int newY = 0;
		for (int i = 0; i < divide.length; i++) {
			
			newR = (divide[i][0])? row:row+half;
			newC = (divide[i][1])? col:col+half;
			
			if (i+1 == site) {
				newX = x;
				newY = y;
			} else {
				newX = row + half;
				newY = col + half;
				newX += (divide[i][0])? -1:0;
				newY += (divide[i][1])? -1:0;
				floor[newX][newY] = site;
			}
			
			
			divideTile(newR, newC, half, newX, newY);
		}
	}
	
	private static int findTile(int row, int col, int len, int x, int y) {
		
		int half = len/2;
		int site = 0;
		
		site += (x < row + half)? 1:3;
		site += (y < col + half)? 0:1;
		
		return site;
	}
	
	private static void print() {
		StringBuilder print = new StringBuilder();
		for (int[] line : floor) {
			for (int tile : line) {
				print.append(tile).append(" ");
			}
			print.append("\n");
		}
		System.out.println(print);
		
	}
}
