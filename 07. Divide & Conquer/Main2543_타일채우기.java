import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1804&sca=50&sfl=wr_hit&stx=2543&sop=and
*/

public class Main2543_타일채우기 {
	
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
	
	// 사분면 순서
	// 1 2
	// 3 4
	// row, col : 바닥 좌상단 좌표 /  len : 바닥 면 길이 /  x, y : 구멍(또는 타일) 좌표
	private static void divideTile(int row, int col, int len, int x, int y) {
		
		// 탈출하기
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
