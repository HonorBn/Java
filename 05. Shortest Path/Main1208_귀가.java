import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=491&sca=50&sfl=wr_hit&stx=1208&sop=and
*/

public class Main1208_귀가 {
	
	static int leastTime;
	static char leastCow;
	static TreeSet<Character> cows, farms;
	static TreeMap<Character, Road> roads;
	static final int MAX_TIME = 99999;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		goHuts();
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int p = Integer.parseInt(st.nextToken());
		
		int len;
		char a, b;
		leastTime = MAX_TIME;
		cows = new TreeSet<>();
		farms = new TreeSet<>();
		roads = new TreeMap<>();
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			a = st.nextToken().charAt(0);
			b = st.nextToken().charAt(0);
			len = Integer.parseInt(st.nextToken());
			
			farms.add(a);
			farms.add(b);
			if (Character.isUpperCase(a) && a != 'Z') cows.add(a);
			if (Character.isUpperCase(b) && b != 'Z') cows.add(b);
			
			input(a, b, len);
			input(b, a, len);
		}
	}
	
	private static void input(char a, char b, int len) {
		Road road = new Road(b, len);
		if (!roads.containsKey(a)) roads.put(a, road);
		else roads.get(a).add(road);
	}
	
	private static void goHuts() {
		for (char cow : cows) goHut(cow);
	}
	
	private static void goHut(char start) {
		
		PriorityQueue<Cow> que = new PriorityQueue<>();
		HashMap<Character, Boolean> visit = new HashMap<>();
		HashMap<Character, Integer> minTimes = new HashMap<>();
		
		for (char farm : farms) visit.put(farm, false);
		for (char farm : farms) minTimes.put(farm, MAX_TIME);
		
		minTimes.put(start, 0);
		que.offer(new Cow(start, minTimes.get(start)));
		
		Cow cow;
		Road road;
		char fromHut, toHut; 
		int startFrom_Time, fromTo_Time;
		while (!que.isEmpty()) {
			
			cow = que.poll();
			fromHut = cow.hut;
			startFrom_Time = cow.time;
			if (visit.get(fromHut)) continue;
			if (fromHut == 'Z') break;
			visit.put(fromHut, true);
			
			road = roads.get(fromHut);
			
			while (road !=null) {
				toHut = road.hut;
				fromTo_Time = road.time;
				if (!visit.get(toHut) &&
					startFrom_Time + fromTo_Time < minTimes.get(toHut)) {
					minTimes.put(toHut, startFrom_Time + fromTo_Time);
					que.offer(new Cow(toHut, minTimes.get(toHut)));
				}
				road = road.next;
			}
		}
		if (minTimes.get('Z') < leastTime) {
			leastCow = start;
			leastTime = minTimes.get('Z');
		}
	}
	
	private static void print() {
		System.out.println(leastCow + " " + leastTime);
	}
}

class Cow implements Comparable<Cow> {
	char hut;
	int time;
	public Cow(char hut, int time) {
		this.hut = hut;
		this.time = time;
	}
	public String toString() {
		return "[목장 : " + hut + ", 시간=" + time + "]";
	}
	public int compareTo(Cow other) {
		return this.time-other.time;
	}
}

class Road {
	char hut;
	int time;
	Road next;
	public Road(char hut, int time) {
		this.hut = hut;
		this.time = time;
	}
	public String toString() {
		return "[hut=" + hut + ", time=" + time + ", next=" + next + "]";
	}
	public void add(Road road) {
		road.next = this.next;
		this.next = road;
	}
}
