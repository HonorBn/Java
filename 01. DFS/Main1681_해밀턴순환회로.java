import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=954&sca=50&sfl=wr_hit&stx=1681&sop=and
*/

public class Main1681_해밀턴순환회로 {
	
	static int n, start;
	static int min, finish;
	static int[][] costs;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		Spot spot = new Spot(start, 0, 0); 
		delivery(spot);
		
		print();
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		min = n * 100;
		start = 0;
		finish = n-1;
		costs = new int[n][n];
		visit = new boolean[n];
		
		StringTokenizer st = null;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				costs[i][j] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void delivery(Spot from) {
		
		int backCost;
		int lastCost;
		if (from.cnt == finish) {
			backCost = costs[from.no][start];
			lastCost = from.costSum + backCost;
			if (backCost != 0 && lastCost < min) min = lastCost;
			return;
		}
		
		if (from.costSum > min) return;
		
		Spot temp = null;
		for(int i = 0; i < n; i++) {
			if (i != from.no && i != start && !visit[i] && costs[from.no][i] != 0) {
				visit[i] = true;
				temp = new Spot(i, from.cnt+1, from.costSum+costs[from.no][i]);
				delivery(temp);
				visit[i] = false;
			}
		}
	}
	
	private static void print() {
		System.out.println(min);
	}
}

class Spot {
	int no;
	int cnt;
	int costSum;
	public Spot(int no, int cnt, int costSum) {
		this.no = no;
		this.cnt = cnt;
		this.costSum = costSum;
	}
	public String toString() {
		return "[no=" + no + ", cnt=" + cnt + ", costSum=" + costSum + "]";
	}
}
