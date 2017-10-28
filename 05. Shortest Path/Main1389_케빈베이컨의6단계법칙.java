import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/1389
*/

public class Main1389_케빈베이컨의6단계법칙 {

	static User friends[];
	static int n, m, kevin[], kevins[];
	static boolean visit[];

	public static void main(String[] args) throws Exception {

		input();

		kevin();

		print();
	}

	private static void input() throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		kevin = new int[n];
		kevins = new int[n];
		friends = new User[n];
		visit = new boolean[n];

		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			friends[a] = new User(b, 1, friends[a]);
			friends[b] = new User(a, 1, friends[b]);
		}
	}

	private static void kevin() {

		PriorityQueue<User> que = new PriorityQueue<>();

		int start, from, fromKevin, to, toKevin;
		User fromUser, toUser;
		for (int i = 0; i < n; i++) {

			start = i;
			que.clear();
			Arrays.fill(visit, false);
			Arrays.fill(kevin, Integer.MAX_VALUE);

			kevin[start] = 0;
			que.offer(new User(start, kevin[start]));

			while (!que.isEmpty()) {

				fromUser = que.poll();
				from = fromUser.num;
				fromKevin = fromUser.kevin;

				if (visit[from]) continue;
				visit[from] = true;

				toUser = friends[from];
				while (toUser != null) {

					to = toUser.num;
					toKevin = toUser.kevin;

					if (!visit[to] && fromKevin + toKevin < kevin[to]) {

						kevin[to] = fromKevin + toKevin;
						que.offer(new User(to, kevin[to]));

					}
					toUser = toUser.next;
				}
			}
			for (int kevin : kevin)
				kevins[i] += kevin;
		}

	}

	private static void print() {

		int minKevin = Integer.MAX_VALUE;
		for (int kevin : kevins)
			if (kevin < minKevin)
				minKevin = kevin;

		for (int i = 0; i < n; i++) {
			if (kevins[i] == minKevin) {
				System.out.println(i + 1);
				break;
			}
		}

	}
}

class User implements Comparable<User> {
	int num, kevin;
	User next;
	public User(int num, int kevin) {
		this.num = num;
		this.kevin = kevin;
	}
	public User(int num, int kevin, User next) {
		this.num = num;
		this.kevin = kevin;
		this.next = next;
	}
	public int compareTo(User other) {
		return this.kevin - other.kevin;
	}
}
