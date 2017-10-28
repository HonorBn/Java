import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1214&sca=50&sfl=wr_hit&stx=1941&sop=and
*/

public class Main1941_최단경로 {
	
	static int n, m;
	static int[] costs;
	static boolean[] visit;
	static Node[] data;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		minRoute();
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		data = new Node[n+1];
		costs = new int[n+1];
		visit = new boolean[n+1];
		Arrays.fill(costs, Integer.MAX_VALUE);
		
		int start, end, cost;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			
			if (data[start] == null) data[start] = new Node(end, cost);
			else data[start].add(new Node(end, cost));
		}
		
	}
	
	private static void minRoute() {
		
		PriorityQueue<Node> que = new PriorityQueue<>();
		
		costs[1] = 0;
		que.offer(new Node(1, costs[1]));
		
		Node fromNode, toNode;
		int from, viaCost, to, toCost;
		while (!que.isEmpty()) {
			
			fromNode = que.poll();
			from = fromNode.num;
			viaCost = fromNode.cost;
			if (visit[from]) continue;
			if (from == n) break;
			visit[from] = true;
			
			toNode = data[from];
			while (toNode != null) {
				
				to = toNode.num;
				toCost = toNode.cost;
				
				if (!visit[to] && to != 1 &&
					viaCost + toCost < costs[to]) {
					costs[to] = viaCost + toCost;
					que.offer(new Node(to, costs[to]));
				}
				
				toNode = toNode.next;
			}
		}
	}
	
	private static void print() {
		
		System.out.println(costs[n]);
		
	}
}

class Node implements Comparable<Node> {
	int num;
	int cost;
	Node next;
	public Node(int num, int cost) {
		this.num = num;
		this.cost = cost;
	}
	public void add(Node node) {
		node.next = this.next;
		this.next = node;
	}
	public int compareTo(Node other) {
		return this.cost-other.cost;
	}
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Go [¸ñÀûÁö : ");
		builder.append(num);
		builder.append(", ºñ¿ë : ");
		builder.append(cost);
		builder.append(", Next : ");
		builder.append(next);
		builder.append("]");
		return builder.toString();
	}
}
