import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=388&sca=50&sfl=wr_hit&stx=1108&sop=and
*/

public class Main1108_페이지전환 {
	
	static Page[] pages;
	static int[][] distance;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		changePage();
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		StringTokenizer st = null;
		String[] lines = new String[n];
		for (int i = 0; i < n; i++ ) lines[i] = br.readLine();
		
		int temp, size = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(lines[i]);
			temp = Math.max(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			if (size < temp) size = temp;
		}
		
		distance = new int[size+1][size+1];
		
		int start, end;
		pages = new Page[size+1];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(lines[i]);
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			if (pages[start] == null) pages[start] = new Page(start, new Page(end, null)); 
			else pages[start].add(new Page(end, null));
		}
	}
	
	private static void changePage() {
		
		int size = pages.length;
		boolean[] visit = new boolean[size];
		Queue<Page> que = new LinkedList<>();
		
		Page page = null;
		Page from = null;
		Page to = null;
		int level, visitCnt;
		for (int i = 1; i < size; i++) {
			visitCnt = 0;
			page = pages[i];
			Arrays.fill(visit, true);
			
			que.offer(page);
			visit[page.no] = false;
			
			while (!que.isEmpty()) {
				
				from = que.poll();
				level = distance[i][from.no];
				
				if (visitCnt == size-2) {
					que.clear();
					break;
				}
				
				while (true) {
					to = from.next;
					if (to != null) {
						if (visit[to.no] && from.no != to.no) {
							que.offer(pages[to.no]);
							visit[to.no] = false;
							distance[i][to.no] = level + 1;
							visitCnt++;
						}
						from = to;
					} else break;
				}
			}
		}
	}
	
	private static void print() {
		int sum = 0;
		int size = pages.length;
		double total = (size-1)*(size-2); 
		for (int i = 1; i < size; i++) {
			for (int j = 1; j < size; j++) {
				sum += distance[i][j];
			}
		}
		System.out.printf("%.3f", sum/total);
	}
}

class Page {
	int no;
	Page next;
	public Page(int no, Page next) {
		this.no = no;
		this.next = next;
	}
	public String toString() {
		return "[번호=" + no + ", 다음=" + next + "]";
	}
	public void add(Page page) {
		page.next = this.next;
		this.next = page;
	}
}
