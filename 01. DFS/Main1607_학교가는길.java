import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=880&sca=50&sfl=wr_hit&stx=1607&sop=and
*/

public class Main1607_학교가는길 {
	
	static int r, c, k, cnt, map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		map[0][0] = 1;
		school();
		
		print();
		
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {0, -1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		map = new int[r][c];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = -1;
		}
	}
	
	private static void school() {
		
		int fromR, fromC, temp;
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if ((i == 0 && j == 0) || map[i][j] == -1) continue;
				temp = 0;
				for (int from = 0; from < 2; from++) {	// [좌측에서 오는 경우] 좌측 좌표 도착 경우의수
					fromR = i + dir[from][0];			// 				+
					fromC = j + dir[from][1];			// [상측에서 오는 경우] 상측 좌표 도착 경우의 수
					if (fromR >= 0 && fromC >= 0 && map[fromR][fromC] != -1) {
						temp += map[fromR][fromC];
					}
				}
				map[i][j] = temp;
			}
		}
	}
	
	private static void print() {
		
		System.out.println(map[r-1][c-1]);
		
	}
}
