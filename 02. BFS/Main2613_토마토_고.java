import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1875&sca=50&sfl=wr_hit&stx=2613&sop=and
*/

public class Main2613_토마토_고 {
	
	static int day;
	static int[][] box;
	static int[][] repeat;
	static Queue<Tomato> toms;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		
		box = new int[n][m];
		toms = new LinkedList<>();
		repeat = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} }; 
		
		int temp = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				temp = Integer.parseInt(st.nextToken());
				box[i][j] = temp;
				if (temp == 1) {
					toms.add(new Tomato(i, j));
				}
			}
		}
		
		if (toms.size() == 0) {
			System.out.println(-1);
			return;
		} else if (toms.size() == n * m) {
			System.out.println(0);
			return;
		}
		
		int newRow = 0;
		int newCol = 0;
		Tomato tom = null;
		while (!toms.isEmpty()) {
				
			tom = toms.poll();
			day = box[tom.row][tom.col];
			
			for (int j = 0; j < repeat.length; j++) {
				
				newRow = tom.row + repeat[j][0];
				newCol = tom.col + repeat[j][1];
				
				if (newRow >= 0 && newRow < n &&
						newCol >= 0 && newCol < m &&
							box[newRow][newCol] == 0) {
					
					toms.add(new Tomato(newRow, newCol));
					box[newRow][newCol] = day+1;
				}
			}
		}
		
		for(int[] tomatos : box)
			for (int tomato : tomatos)
				
				if (tomato == 0)
				{
					System.out.println(-1);
					return;
				}

		System.out.println(day-1);
		
	}	
}

class Tomato {
	int row;
	int col;
	public Tomato(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[ ");
		builder.append(row);
		builder.append(", ");
		builder.append(col);
		builder.append(" ]");
		return builder.toString();
	}
	
}
