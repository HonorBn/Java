import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=734&sca=50&sfl=wr_hit&stx=1462&sop=and
*/

public class Main1462_보물섬 {
	
	static int r, c, time, maxTime;
	static int[][] map;
	static int[][] mapTemp;
	static int[][] dir;
	static Queue<int[]> quePath;
	
	public static void main(String[] args) throws IOException {
		
		quePath = new LinkedList<>();
		dir = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    map = new int[r][c];
	    
	    char[] line = null;
	    for (int i = 0; i < r; i++) {				// 경과시간을 map에 표시하기 때문에 W는 -1로
	    	line = br.readLine().toCharArray();
	    	for (int j = 0; j < c; j++) {
	    		switch (line[j]) {
	        		case 'W':
	        			map[i][j] = -1;
	        			break;
	        		case 'L':
	        			map[i][j] = 0;
	        			break;
	    		}
	    	}
	    }
	    
	    for (int i = 0; i < r; i++) {			// 모든 L마다 모든 L에 도착하는 시간 계산
	    	for (int j = 0; j < c; j++) {
	    		if (map[i][j] == 0) {
	    			mapTemp = new int[r][c];	// 한 L에서 path()를 수행할 때마다 
					pathReset();				// map에 경과시간이 적히기 때문에 리셋 필요
					quePath.offer(new int[]{i, j});
					mapTemp[i][j] = time = 1;
					timeCnt();
	    		}
	    	}
	    }
	    
	    System.out.println(maxTime);
	    
	} // main() end
	
	private static void timeCnt() {
		
		int newR = 0;
		int newC = 0;
		int[] temp = null;
		
		while (!quePath.isEmpty()) {
			
			temp = quePath.poll();
			time = mapTemp[temp[0]][temp[1]];
			
			for (int i = 0; i < dir.length; i++) {
				newR = temp[0] + dir[i][0];
				newC = temp[1] + dir[i][1];
				
				if (newR >= 0 && newC >= 0 &&
						newR < r && newC < c &&
						(mapTemp[newR][newC] == 0 ||
						mapTemp[newR][newC] > time+1) ) {
					
					mapTemp[newR][newC] = time+1;
					quePath.offer(new int[]{newR, newC});
				}
			}
		}
		
		if (time-1 > maxTime) maxTime = time-1;
		
	} // path() end
	
	private static void pathReset() {
		
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				mapTemp[i][j] = map[i][j];
		
	} // pathReset() end
	
} // class end
