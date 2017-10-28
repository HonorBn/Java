import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/7569
*/

public class Main7569_토마토 {

	public static void main(String[] args) throws IOException {
		
		Queue<int[]> que = new LinkedList<>();
		int[][] dir = { {1,0,0} , {-1,0,0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1} };
		
		Scanner s = new Scanner(System.in);
		StringTokenizer st = new StringTokenizer(s.nextLine());
		
		int m = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int[][][] box = new int[h][n][m];
		
		int su = 0;
		int oneCnt = 0;
		int minusCnt = 0;
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(s.nextLine());
				for (int j = 0; j < m; j++) {
					su = Integer.parseInt(st.nextToken());
					box[k][i][j] = su;
					if (su == 1) {
						que.add(new int[]{k, i, j});
						oneCnt++;
					} else if (su == -1) minusCnt++;
				}
			}
		}
		
		if (oneCnt == 0) {
			System.out.println(-1);
			s.close();
			return;
		} else if (oneCnt + minusCnt == h * n * m) {
			System.out.println(0);
			s.close();
			return;
		}
		
		int cnt = 0;
		int newH = 0;
		int newR = 0;
		int newC = 0;
		int[] temp = null;
		while (!que.isEmpty()) {
			
			temp = que.poll();
			cnt = box[temp[0]][temp[1]][temp[2]];
			
			for (int i = 0; i < dir.length; i++) {
				
				newH = temp[0] + dir[i][0];
				newR = temp[1] + dir[i][1];
				newC = temp[2] + dir[i][2];
				
				if (newH >= 0 && newH < h &&
						newR >= 0 && newR < n &&
						newC >= 0 && newC < m &&
						box[newH][newR][newC] == 0) {
					box[newH][newR][newC] = cnt+1;
					que.add(new int[]{newH, newR, newC});
				}
			}
		}
		
		int zeroCnt = 0;
		for (int k = 0; k < h; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (box[k][i][j] == 0) {
						zeroCnt++;
						break;
					}
				}
			}
		}
		if (zeroCnt > 0) System.out.println(-1);
		else System.out.println(cnt-1);
		s.close();
	}
}
