import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=934&sca=50&sfl=wr_hit&stx=1661&sop=and
*/

public class Main1661_미로탈출로봇 {
	
	static int n;
	static int m;
	static int res;
	static int[][] map;
	
	static Point start;
	static Point end;
	static int[][] repeat = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] temp = new int[4];
		for (int i = 0; i < 4; i++) temp[i] = Integer.parseInt(st.nextToken());
		start = new Point(temp[1]-1, temp[0]-1);
		end = new Point(temp[3]-1, temp[2]-1);
		
		map = new int[n][m];
		char[] line = null;
		for (int i = 0; i < n; i++) {
			line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (line[j] == '1') map[i][j] = 1;
			}
		}
		
		res = 10000;
		fun(start, 0);
		
		System.out.println(res);
		
	}
	
	public static void fun(Point spot, int time) {
		
		if (spot.toString().equals(end.toString())) {
			if (res > time) {
				res = time;
				return;
			}
		}
		
		int newRow = 0;
		int newCol = 0;
		Point tempSpot = null;
		for (int j = 0; j < repeat.length; j++) {
			
			newRow = spot.row + repeat[j][0];
			newCol = spot.col + repeat[j][1];
			
			if (newRow >= 0 && newRow < n &&
					newCol >= 0 && newCol < m &&
						map[newRow][newCol] != 1) {
				
				if (map[newRow][newCol] == 0 ||
						time + 1 < map[newRow][newCol]) {
					
					map[newRow][newCol] = time+1;
					tempSpot = new Point(newRow, newCol);
					fun(tempSpot, time+1);
					
				}
			}
		}
	}
}

class Point {
	int row;
	int col;
	public Point(int row, int col) {
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("(");
		builder.append(row);
		builder.append(", ");
		builder.append(col);
		builder.append(")");
		return builder.toString();
	}
}
