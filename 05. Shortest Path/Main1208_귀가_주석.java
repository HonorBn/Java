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

/* 문제 요약
목장 : a~z, A~Y
헛간 : Z
 - 헛간을 제외한 대문자 목장에 소 한마리씩
 - 각 목장끼리 길이 연결이 안된 경우도 있고, 두 목장 사이의 길이 2개 이상인 경우도 있음 (여기서 Dijstra로 PriorityQueue 사용 결정)
 - 적어도 한 개 이상의 목장에서 헛간으로 가는 길이 존재 (헛간으로 가지 못하는 경우는 없음)
 - 길은 일방통행이 아니고, 모든 소의 속도는 동일
결과 : 가장 먼저 헛간에 도착하는 소가 원래 있는 목장 번호, 그 소가 걷는 거리(시간)
*/

public class Main1208_귀가_주석 {
	
	static char leastCow;	// 가장 먼저 도착한 소
	static int leastTime;	// 가장 먼저 도착한 소가 걸린 시간
	static TreeSet<Character> cows, farms;	// cows: 길이 있는 대문자 목장, farms: 길이 있는 모든 목장
	static TreeMap<Character, Road> roads;	// 인접 리스트 Map<출발목장번호, 길(도착목장번호, 소요시간)>
	static final int MAX_TIME = 99999;		// 최대 소요 시간 설정 : 1000 * 52 (길의 최대 길이 * 길 경유 최대 개수)
	
	public static void main(String[] args) throws IOException {
		
		input();	// cows, farms, roads 준비
					// cows : 소가 헛간으로 가는 최소시간을 구하기 위해서, 소가 있는 목장만 고르는 작업 필요
					// farms : 소가 없는 목장은 경유지로 쓰이기 때문에, visited와 distance 자료 생성을 위해 필요
					// roads : 각 목장마다 연결된 목장과 소요시간을 저장하는 인접 리스트
		
		goHuts();	// 길이 있는 대문자 목장(즉, 소가 있는 목장)마다 헛간 도착 시간 계산 및 최소값 갱신
		
		print();
		
	}
	
	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		int p = Integer.parseInt(st.nextToken());
		
		int len;
		char a, b;
		leastTime = MAX_TIME;
		cows = new TreeSet<>();		// cows, farms 목장의 리스트이므로
		farms = new TreeSet<>();	// 중복 저장을 피하기 위해, 알파벳 순서 정렬을 위해, TreeSet 사용
		roads = new TreeMap<>();	// 목장 인덱스를 char 타입으로 관리해야 하므로 Map<Character, Road> 사용
		for (int i = 0; i < p; i++) {
			st = new StringTokenizer(br.readLine());
			a = st.nextToken().charAt(0);
			b = st.nextToken().charAt(0);
			len = Integer.parseInt(st.nextToken());
			
			// 길에 방향이 없으므로 양방향 모두 고려
			input(a, b, len);
			input(b, a, len);
			
			farms.add(a);
			farms.add(b);
			if (Character.isUpperCase(a) && a != 'Z') cows.add(a);
			if (Character.isUpperCase(b) && b != 'Z') cows.add(b);
		}
	}
	
	private static void input(char a, char b, int len) {
		Road road = new Road(b, len);
		if (!roads.containsKey(a)) roads.put(a, road);
		else roads.get(a).add(road);
	}
	
	private static void goHuts() {
		for (char cow : cows) goHut(cow);	// 길이 있는 대문장 목장마다 헛간으로 가는 시간 계산
	}
	
	private static void goHut(char start) {
		
		PriorityQueue<Cow> que = new PriorityQueue<>();
		HashMap<Character, Boolean> visit = new HashMap<>();
		HashMap<Character, Integer> minTimes = new HashMap<>();
		
		for (char farm : farms) visit.put(farm, false);			// 모든 목장 방문 false 처리
		for (char farm : farms) minTimes.put(farm, MAX_TIME);	// 모든 목장 초기값 설정
		
		minTimes.put(start, 0);		// 시작 목장 소요시간 0 설정
		que.offer(new Cow(start, minTimes.get(start)));		// queue에 담고 시작
		
		Cow cow;
		Road road;
		char fromHut, toHut; 				// fromHut : 경유 목장 번호, toHut : 목표 목장 번호
		int startFrom_Time, fromTo_Time;	// startFrom_Time : "시작 목장 -> 경유 목장" 최소 소요 시간
		while (!que.isEmpty()) {			// fromTo_Time : "시작 목장 -> 목표 목장" 최소 소요 시간
			
			cow = que.poll();
			fromHut = cow.hut;
			startFrom_Time = cow.time;
			if (visit.get(fromHut)) continue;
			if (fromHut == 'Z') break;
			visit.put(fromHut, true);
			
			road = roads.get(fromHut);		// 경유 목장에서 갈 수 있는 목표 목장이 담긴 인접 리스트
			
			while (road !=null) {
				toHut = road.hut;
				fromTo_Time = road.time;
				if (!visit.get(toHut) &&
					startFrom_Time + fromTo_Time < minTimes.get(toHut)) {
					minTimes.put(toHut, startFrom_Time + fromTo_Time);
					que.offer(new Cow(toHut, minTimes.get(toHut)));
				}
				road = road.next;	// 다음 목표 목장 갱신
			}
		}
		if (minTimes.get('Z') < leastTime) {	// 해당 시작 목장의 헛간 도착 시간 계산이 끝났으므로
			leastCow = start;					// 최소 도착 시간과 그 시작 목장 갱신
			leastTime = minTimes.get('Z');
		}
	}
	
	private static void print() {
		System.out.println(leastCow + " " + leastTime);
	}
}

//class Cow implements Comparable<Cow> {
//	char hut;
//	int time;
//	public Cow(char hut, int time) {
//		this.hut = hut;
//		this.time = time;
//	}
//	public String toString() {
//		return "[목장 : " + hut + ", 시간=" + time + "]";
//	}
//	public int compareTo(Cow other) {
//		return this.time-other.time;
//	}
//}
//
//class Road {
//	char hut;
//	int time;
//	Road next;
//	public Road(char hut, int time) {
//		this.hut = hut;
//		this.time = time;
//	}
//	public String toString() {
//		return "[hut=" + hut + ", time=" + time + ", next=" + next + "]";
//	}
//	public void add(Road road) {
//		road.next = this.next;
//		this.next = road;
//	}
//}
