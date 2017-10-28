import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1360&sca=50&sfl=wr_hit&stx=2097&sop=and
*/

public class Main2097_지하철_dijkstra {

	public static void main(String[] args) throws IOException {
		
		PriorityQueue<Station> que = new PriorityQueue<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken())-1;
		int[][] distance = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				distance[i][j] = Integer.parseInt(st.nextToken());
		}
		
		// 배열 준비
		int[] path = new int[n];
		int[] dist = new int[n];
		Arrays.fill(dist, 99999);
		boolean[] visit = new boolean[n];
		
		// 시작점 지정
		dist[0] = 0;
		que.offer(new Station(0, dist[0]));
		
		// 처리
		int from = 0;
		int viaCost = 0;
		Station temp = null;
		while (!que.isEmpty()) {
			temp = que.poll();
			from = temp.num;
			if (visit[from]) continue;
			if (from == end) break;
			viaCost = temp.cost;
			visit[from] = true;
			
			
			for (int to = 0; to < n; to++) {
				if (!visit[to]) {
					if (viaCost + distance[from][to] < dist[to]) {
						dist[to] = viaCost + distance[from][to];
						path[to] = from; // 내 자리에 직전에 누구를 거쳐왔는지 기억, 나를 최적화시킨 직전의 노드
						que.offer(new Station(to, dist[to]));
					}
				}
			}
		}
		
		// 출력
		ArrayList<Integer> print = new ArrayList<>();
		print.add(end+1);
		int num = end;
		do {
			num = path[num];
			print.add(0, num+1);
		} while (num != 0);
		System.out.println(dist[end]);
		for (Integer su : print) {
			System.out.print(su + " ");
		}
	}
}

class Station implements Comparable<Station> {
	int num;
	int cost;
	public Station(int num, int dist) {
		this.num = num;
		this.cost = dist;
	}
	public int compareTo(Station other) {
		return this.cost - other.cost;
	}
}
