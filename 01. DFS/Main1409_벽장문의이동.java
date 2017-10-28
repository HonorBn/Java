import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=685&sca=50&sfl=wr_hit&stx=1409&sop=and
*/

public class Main1409_벽장문의이동 {
	
	static int n, k, resCnt, openL, openR, openSeq[];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		open(0, 0, openL, openR);
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		resCnt = 400;	// 벽장 최대 20개 사용 * 벽장문 최대 20회 이동
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		openL = Integer.parseInt(st.nextToken());
		openR = Integer.parseInt(st.nextToken());
		
		k = Integer.parseInt(br.readLine().trim());
		
		openSeq = new int[k];
		for (int i = 0; i < k; i++) openSeq[i] = Integer.parseInt(br.readLine().trim());
		
	}
	
	private static void open(int seqNo, int cnt, int open1, int open2) {
		
		int door = openSeq[seqNo];
		
		int left = cnt +  Math.abs(door-open1);
		int right = cnt +  Math.abs(door-open2);
		int min = (left < right)? left:right;
		
		if (seqNo == k-1) {
			if (min < resCnt) resCnt = min;
			return;
		}
			
		open(seqNo+1, left, door, open2);
		open(seqNo+1, right, open1, door);
		
	}
	
	private static void print() {
		System.out.println(resCnt);
	}
}
