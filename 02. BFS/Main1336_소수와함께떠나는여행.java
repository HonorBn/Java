import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=615&sca=50&sfl=wr_hit&stx=1336&sop=and
*/

public class Main1336_소수와함께떠나는여행 {
	
	static ArrayList<String> primeList;
	
	public static void main(String[] args) throws IOException {
		
		Queue<int[]> que = new LinkedList<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String start = st.nextToken();
		String end = st.nextToken();
		
		int primeSize = prime();
		int startId = primeList.indexOf(start);
		int endId = primeList.indexOf(end);
		boolean[] visit = new boolean[primeSize];
		
		que.offer(new int[]{startId, 0});
		visit[startId] = true;

		int[] from = null;
		int fromId = 0, fromLv = 0;
		while (!que.isEmpty()) {
			
			from = que.poll();
			fromId = from[0];
			fromLv = from[1];
			
			if (fromId == endId) break;
			
			for (int toId = 0; toId < primeSize; toId++) {
				if (!visit[toId] && toId != fromId && transfer(fromId, toId)) {
					que.offer(new int[]{toId, fromLv+1});
					visit[toId] = true;
				}
			}
			
			
		}
		
		System.out.println(fromLv);
	}
	
	// 환승 체크
	private static boolean transfer(int fromId, int toId) {
		char[] fromBus = primeList.get(fromId).toCharArray();
		char[] toBus = primeList.get(toId).toCharArray();
		int cnt = 0;
        for (int i = 0; i < 4; i++) {
            if (fromBus[i] - toBus[i] != 0) cnt++;
            if (cnt >= 2) return false;
        }
        return true;
	}
	
	// 소수 계산 
	private static int prime() {
		primeList = new ArrayList<>();
		int[] numbers = new int[9998];
		boolean[] primeCheck = new boolean[9998];
		for (int i = 0; i < 9998; i++) numbers[i] = i + 2;
		int a = 0;
		for (int i = 0; i < 9998; i++) {
			if (!primeCheck[i]) {
				a = numbers[i];
				if (a > 1000) primeList.add(String.valueOf(a));
				for (int j = i; j < 9998; j++)
					if (numbers[j] % a == 0) primeCheck[j] = true;
			}
		}
		return primeList.size();
	}
}
