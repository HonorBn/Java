import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/13460
*/

public class Main13460_째로탈출2 {
	
	static Marble start;
	static int r, c, resCnt, hole[], map[][], dir[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		jjaero();
		
		print();
	}

	private static void input() throws IOException {
		
		dir = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];	// 1: 벽, 0: 통로 // 구멍 좌표 및 구슬 초기 좌표 저장
		
		char item, line[];
		int redR = 0, redC = 0, blueR = 0, blueC = 0;
		for (int i = 0; i < r; i++) {
			line = br.readLine().trim().toCharArray();
			for (int j = 0; j < c; j++) {
				item = line[j];
				switch (item) {
				case '#':
					map[i][j] = 1; break;
				case 'O':
					hole = new int[]{i, j}; break;
				case 'R':
					redR = i; redC = j; break;
				case 'B':
					blueR = i; blueC = j; break;
				}
			}
		}
		
		resCnt = -1;	// Red가 구멍에 빠진 경우를 제외하고는 모두 -1을 출력하므로 초기값 -1로 설정
		start = new Marble(redR, redC, blueR, blueC, 0);
		
	}

	private static void jjaero() {
		
		Queue<Marble> que = new LinkedList<>();
		que.offer(start);
		
		Marble from;
		int cnt, cha;
		int fromRedR, fromRedC, fromBlueR, fromBlueC;
		int toRedR, toRedC, toBlueR, toBlueC;
		
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromRedR = from.redR; fromRedC = from.redC;
			fromBlueR = from.blueR; fromBlueC = from.blueC;
			cnt = from.cnt;
			
			if (cnt > 10) return;	// cnt가 10보다 크면 resCnt 갱신안하고 BFS 종료 -> resCnt = -1인 상태
			if (fromRedR == hole[0] && fromRedC == hole[1])	// Red가 구멍이면 성공이므로 resCnt 갱신
				{resCnt = cnt; return;}
			
			for (int i = 0; i < 4; i++) {	// 4 방향 탐색 (상>하>좌>우)
				
				// 벽 또는 구멍을 만날 때까지 to좌표 갱신
				toRedR = fromRedR + dir[i][0]; toRedC = fromRedC + dir[i][1];
				toBlueR = fromBlueR + dir[i][0]; toBlueC = fromBlueC + dir[i][1];
				
				while (map[toRedR][toRedC] != 1 && !(toRedR == hole[0] && toRedC == hole[1]))
					{toRedR += dir[i][0]; toRedC += dir[i][1];}
				
				while (map[toBlueR][toBlueC] != 1 && !(toBlueR == hole[0] && toBlueC == hole[1]))
					{toBlueR += dir[i][0]; toBlueC += dir[i][1];}
				
				// Blue가 구멍이면 que에 offer하지 않기 위해 continue
				// 4 방향 중 Blue가 구멍이 아닌 경우만 BFS 계속 진행
				if (toBlueR == hole[0] && toBlueC == hole[1]) continue;
				
				// Blue가 구멍인 경우는 위에서 continue했기 때문에
				// Red가 구멍이고 Blue가 벽에 박힌 상태 or 구슬 모두 벽에 박혀 있는 상태
				// Red가 구멍인 경우는 그 좌표 그대로 que에 offer하기 위해 그 외의 경우만 두 구슬 모두 한칸 뒤로 back
				if (!(toRedR == hole[0] && toRedC == hole[1]))
					{toRedR -= dir[i][0]; toRedC -= dir[i][1];}
				
				toBlueR -= dir[i][0]; toBlueC -= dir[i][1];
				
				// 2개 구슬이 겹치는 경우를 처리하는 부분 (Red와 Blue의 좌표가 같은 경우)
				// 이동 방향(i)과 두 구슬의 from좌표값 차이(cha)를 이용하여 상대 구슬을 뒤따라간 구슬을 한칸 뒤로 back
				if (toRedR == toBlueR && toRedC == toBlueC) {
					
					cha = (fromRedR-fromBlueR) + (fromRedC-fromBlueC);
					
					if (i == 0 || i == 2) {
						
						if (cha > 0) {toRedR -= dir[i][0]; toRedC -= dir[i][1];}
						else {toBlueR -= dir[i][0]; toBlueC -= dir[i][1];}
						
					} else {
						
						if (cha > 0) {toBlueR -= dir[i][0]; toBlueC -= dir[i][1];}
						else {toRedR -= dir[i][0]; toRedC -= dir[i][1];}
						
					}
				}
				
				// 두 구슬 모두 from좌표값과 to좌표 값이 같은 경우는 더이상 변화가 없으므로 continue
				if (fromRedR == toRedR && fromBlueR == toBlueR &&
					fromRedC == toRedC && fromBlueC == toBlueC) continue;
				
				// 더 이동할 여지가 있거나 Red가 구멍에 들어간 경우만 offer
				que.offer(new Marble(toRedR, toRedC, toBlueR, toBlueC, cnt + 1));
				
			}
		}
	}
	
	private static void print() {
		System.out.println(resCnt);
	}
}

class Marble {
	int redR;
	int redC;
	int blueR;
	int blueC;
	int cnt;
	public Marble(int redR, int redC, int blueR, int blueC, int cnt) {
		this.redR = redR;
		this.redC = redC;
		this.blueR = blueR;
		this.blueC = blueC;
		this.cnt = cnt;
	}
	public String toString() {
		return "Red[" + redR + ", " + redC + "], Blue[" + blueR + ", " + blueC + "], " + cnt;
	}
}
