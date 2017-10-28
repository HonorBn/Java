import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=391&sca=50&sfl=wr_hit&stx=1111&sop=and
*/

public class Main1111_등산로찾기 {
	
	static final int MAX_FORCE = 500000; // 100 * 100 * 50
	static int n, peekR, peekC, minForce, dir[][], mountain[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		climbs();
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		peekR = Integer.parseInt(st.nextToken());
		peekC = Integer.parseInt(st.nextToken());
		minForce = MAX_FORCE;
		
		dir = new int[][]{ {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
		
		mountain = new int[n+2][n+2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				mountain[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	private static void climbs() {
		
		for (int i = 0; i <= n+1; i++) {
			for (int j = 0; j <= n+1; j++) {
				if (i == 0 || i == n+1 || j == 0 || j == n+1) {
					climb(i, j);
				}
			}
		}
	}
	
	private static void climb(int startR, int startC) {
		
		PriorityQueue<Pos> que = new PriorityQueue<>();
		int[][] force = new int[n+2][n+2];
		boolean[][] visit = new boolean[n+2][n+2];
		for (int i = 0; i <= n+1; i++) Arrays.fill(force[i], MAX_FORCE);
		force[startR][startC] = mountain[startR][startC];
		
		que.offer(new Pos(startR, startC, force[startR][startC]));
		
		Pos fromPos;
		int fromR, fromC, toR, toC, startFrom_force, fromTo_force, startTo_force;
		while (!que.isEmpty()) {
			
			fromPos = que.poll();
			fromR = fromPos.row;
			fromC = fromPos.col;
			startFrom_force = fromPos.force;
			if (visit[fromR][fromC]) continue;
			if (fromR == peekR && fromC == peekC) break;
			visit[fromR][fromC] = true;
			
			for (int i = 0; i < dir.length; i++) {
				
				toR = fromR + dir[i][0];
				toC = fromC + dir[i][1];
				
				if (!(toR > 0 && toR <= n) || !(toC > 0 && toC <= n) || visit[toR][toC]) continue;
				
				startTo_force = force[toR][toC];
				
				int cha = mountain[toR][toC] - mountain[fromR][fromC];
				int chaAbs = Math.abs(cha);
				if (cha > 0) fromTo_force = (int) Math.pow(chaAbs, 2);
				else if (cha < 0) fromTo_force = chaAbs; 
				else fromTo_force = 0; 
				
				if (startFrom_force + fromTo_force < startTo_force) {
					force[toR][toC] = startFrom_force + fromTo_force;
					que.offer(new Pos(toR, toC, force[toR][toC]));
				}
				
			}
		}
		
		int onceForce = force[peekR][peekC];
		if (onceForce < minForce) minForce = onceForce;
		
	}
	
	private static void print() {
		System.out.println(minForce);
	}
}

class Pos implements Comparable<Pos> {
	int row;
	int col;
	int force;
	public Pos(int row, int col, int force) {
		this.row = row;
		this.col = col;
		this.force = force;
	}
	public String toString() {
		return "[" + row + ", " + col + "] [" + force + "]";
	}
	public int compareTo(Pos other) {
		return this.force-other.force;
	}
}
