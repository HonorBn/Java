import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1839&sca=50&sfl=wr_hit&stx=2578&sop=and
*/

public class Main2578_버스갈아타기 {
	
	static int Cnt;
	static Bus[] lines;
	static boolean[] visit;
	static ArrayList<Integer> startBus, endBus;
	
	public static void main(String[] args) throws IOException {
		
		input();

		transfer();

		print();
		
	}

	private static void input() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int m = Integer.parseInt(st.nextToken()), n = Integer.parseInt(st.nextToken());
		
		int k = Integer.parseInt(br.readLine().trim());
		
		lines = new Bus[k];
		visit = new boolean[k];
		int no, former[], latter[];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			
			no = Integer.parseInt(st.nextToken());
			
			former = new int[] {	Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())	};
			latter = new int[] {	Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())	};
			
			lines[no-1] = new Bus(no, former, latter);
		}
		
		startBus = new ArrayList<>();
		endBus = new ArrayList<>();
		st = new StringTokenizer(br.readLine().trim());
		int[] start = new int[] {	Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())	};
		int[] end 	= new int[] {	Integer.parseInt(st.nextToken()),
									Integer.parseInt(st.nextToken())	};
		for (int i = 0; i < k; i++) {
			if (	(start[0] == lines[i].former[0] && start[0] == lines[i].latter[0] &&
					 start[1] >= lines[i].former[1] && start[1] <= lines[i].latter[1]) ||
					(start[1] == lines[i].former[1] && start[1] == lines[i].latter[1] &&
					 start[0] >= lines[i].former[0] && start[0] <= lines[i].latter[0])	) startBus.add(i+1);
			if (	(end[0] == lines[i].former[0] && end[0] == lines[i].latter[0] &&
					 end[1] >= lines[i].former[1] && end[1] <= lines[i].latter[1]) ||
					(end[1] == lines[i].former[1] && end[1] == lines[i].latter[1] &&
					 end[0] >= lines[i].former[0] && end[0] <= lines[i].latter[0])		) endBus.add(i+1);
		}
		k = m + n;
	}

	private static void transfer() {

		Queue<Bus> que = new LinkedList<>();
		
		for (Integer bus : startBus) {
			que.offer(lines[bus-1]);
			visit[bus-1] = true;
		}
		
		Bus fromBus = null;
		int fromNo, toNo;
		
		while (!que.isEmpty()) {
			
			fromBus = que.poll();
			fromNo = fromBus.no;
			if (endBus.contains(fromNo)) break;
			
			for (Bus toBus : lines) {
				toNo = toBus.no;
				if (toNo != fromNo && !visit[toNo-1] && transferable(fromBus, toBus)) {
					toBus.cnt = fromBus.cnt+1;
					que.offer(toBus);
					visit[toNo-1] = true;
				}
			}
		}
		Cnt = fromBus.cnt;
	}

	private static void print() {
		System.out.println(Cnt);
	}

	private static boolean transferable(Bus a, Bus b) {
		int a1row = a.former[0], a1col = a.former[1], a2row = a.latter[0], a2col = a.latter[1];
		int b1row = b.former[0], b1col = b.former[1], b2row = b.latter[0], b2col = b.latter[1];
		
		if (a1row == a2row) {
			if (b1row == b2row) return a1row == b1row && a2col >= b1col && a1col <= b2col;
			else return a1row >= b1row && a1row <= b2row && b1col >= a1col && b1col <= a2col;
		} else {
			if (b1col == b2col) return a1col == b1col && a2row >= b1row && a1row <= b2row;
			else return a1col >= b1col && a1col <= b2col && b1row >= a1row && b1row <= a2row;
		}
	}
}

class Bus {
	int no;
	int[] former;
	int[] latter;
	int cnt;
	public Bus(int no, int[] former, int[] latter) {
		this.no = no;
		this.cnt = 1;
		int chaSum = former[0]-latter[0]+former[1]-latter[1];
		this.former = (chaSum > 0)? latter:former;
		this.latter = (chaSum > 0)? former:latter;
	}
	public String toString() {
		return "[no=" + no + ", former=" + Arrays.toString(former) + ", latter=" + Arrays.toString(latter)
				+ ", cnt=" + cnt + "]";
	}
}
