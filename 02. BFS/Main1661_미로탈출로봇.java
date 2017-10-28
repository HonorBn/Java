import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=934&sca=50&sfl=wr_hit&stx=1661&sop=and
*/

public class Main1661_미로탈출로봇 {
	
	public static void main(String[] args) throws IOException {
		
		Queue<Point> que = new LinkedList<>();
		int[][] repeat = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] temp = new int[4];
		for (int i = 0; i < 4; i++) temp[i] = Integer.parseInt(st.nextToken());
		Point start = new Point(temp[1]-1, temp[0]-1);
		Point end = new Point(temp[3]-1, temp[2]-1);
		
		char[] line = null;
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			line = br.readLine().toCharArray();
			for (int j = 0; j < m; j++) {
				if (line[j] == '1') map[i][j] = 1;
			}
		}
		
		que.add(start);
		
		int time = 0;
		int newRow = 0;
		int newCol = 0;
		Point spot = null;
		Point tempSpot = null;
		while (true) {
			
			spot = que.poll();
			if (spot.toString().equals(end.toString())) break;
			
			time = map[spot.row][spot.col];
			
			for (int j = 0; j < repeat.length; j++) {
				
				newRow = spot.row + repeat[j][0];
				newCol = spot.col + repeat[j][1];
				
				if (newRow >= 0 && newRow < n &&
						newCol >= 0 && newCol < m &&
							map[newRow][newCol] == 0) {
					
					tempSpot = new Point(newRow, newCol); 
					que.add(tempSpot);
					map[newRow][newCol] = time+1;
				}
			}
		}
		
		System.out.println(map[end.row][end.col]);
		
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
