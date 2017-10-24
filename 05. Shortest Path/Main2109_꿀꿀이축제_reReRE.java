import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* ¿‘∑¬ µ•¿Ã≈Õ
4 8 2
1 2 4
1 3 2
1 4 7
2 1 1
2 3 5
3 1 2
3 4 4
4 2 3
 */

public class Main2109_≤‹≤‹¿Ã√‡¡¶_reReRE {
	
	static Path paths[];
	static final int MAX_TIME = 99999999;
	static int n, m, end, result, destination[];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		parties();
		
		print();
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken())-1;
		
		paths = new Path[n];
		
		int from, to, time; Path path;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			path = new Path(to-1, time);
			if (paths[from-1] == null) paths[from-1] = path;
			else paths[from-1].add(path);
		}
	}
	
	private static void parties() {
		
		destination = party(end);
		
		int temp = 0;
		for (int i = 0; i < n; i++) {
			if (i == end) continue;
			temp = party(i)[end] + destination[i];
			if (result < temp) result = temp;
		}
		
	}
	
	private static int[] party(int pig) {
		
		PriorityQueue<Vil> que = new PriorityQueue<>();
		int[] distance = new int[n];
		boolean[] visited = new boolean[n];
		Arrays.fill(distance, MAX_TIME);
		
		distance[pig] = 0;
		
		que.offer(new Vil(pig, distance[pig]));
		
		Vil tempVil = null;
		Path tempPath = null;
		while (!que.isEmpty()) {
			
			tempVil = que.poll();
			if (visited[tempVil.no]) continue;
			visited[tempVil.no] = true;
			
			tempPath = paths[tempVil.no];
			while (tempPath !=null) {
				if (!visited[tempPath.to] &&
					tempVil.timeSum + tempPath.time < distance[tempPath.to]) {
					distance[tempPath.to] = tempVil.timeSum + tempPath.time;
					que.offer(new Vil(tempPath.to, distance[tempPath.to]));
				}
				tempPath = tempPath.next;
			}
		}
		return distance;
	}
	
	private static void print() {
		
		System.out.println(result);
		
	}
}

class Vil implements Comparable<Vil> {
	int no;
	int timeSum;
	public Vil(int no, int timeSum) {
		super();
		this.no = no;
		this.timeSum = timeSum;
	}
	public String toString() {
		return "[no=" + no + ", timeSum=" + timeSum + "]";
	}
	public int compareTo(Vil other) {
		return this.timeSum-other.timeSum;
	}
}

class Path {
	int to;
	int time;
	Path next;
	public Path(int to, int time) {
		this.to = to;
		this.time = time;
	}
	public String toString() {
		return "[to=" + to + ", time=" + time + ", next=" + next + "]";
	}
	public void add(Path path) {
		path.next = this.next;
		this.next = path;
	}
}