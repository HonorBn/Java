import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/13458
*/

public class Main13458_시험감독 {
	
	static int n, monitor1, monitor2, students[];
	static long monitors;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		test();
		
		print();
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		students = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i <n; i++)
			students[i] = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		monitor1 = Integer.parseInt(st.nextToken());
		monitor2 = Integer.parseInt(st.nextToken());
	}
	
	private static void test() {
		
		int monitor, remainStudent1, remainStudent2;
		for (int i = 0; i < n; i++) {
			
			monitor = 1;
			remainStudent1 = students[i] - monitor1;
			if (remainStudent1 > 0) {
				monitor += remainStudent1 / monitor2;
				remainStudent2 = remainStudent1 % monitor2;
				if (remainStudent2 > 0) monitor += 1;
			}
		
			monitors += monitor;
		}
	}

	private static void print() {
		
		System.out.println(monitors);
		
	}
}
