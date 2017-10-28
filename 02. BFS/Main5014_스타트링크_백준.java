import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/5014
*/

public class Main5014_스타트링크 {
	
	static int F, S, G, U, D;
	static StringBuilder res;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		startLink();
		
		print();
		
	}
	
	public static void input() throws IOException {
		
		res = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
	}
	
	public static void startLink() {
		
		int[] buttons = new int[F+1];
		Queue<Integer> que = new LinkedList<>();
		
		que.offer(S);
		buttons[S] = 1;
		
		int to, newCnt, temp;
		while (!que.isEmpty()) {
			
			temp = que.poll();
			
			if (temp == G) {
				res.append(buttons[temp] - 1);
				return;
			}
			
			newCnt = buttons[temp] + 1;
			
			to = temp + U;
			if (to <= F && buttons[to] == 0) {
				que.offer(to);
				buttons[to] = newCnt;
			}
			
			to = temp - D;
			if (to >= 1 && buttons[to] == 0) {
				que.offer(to);
				buttons[to] = newCnt;
			}
		}
		
		res.append("use the stairs");
		
	}
	
	public static void print() {
		
		System.out.println(res.toString());
		
	}
}
