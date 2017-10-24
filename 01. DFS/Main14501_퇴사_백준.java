import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 입력 데이터
10
5 50
4 40
3 30
2 20
1 10
1 10
2 20
3 30
4 40
5 50

 */

public class Main14501_퇴사_백준 {
	
	static int n, maxBenefit, benefits[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		benefit(0, 0);
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine().trim());
		
		benefits = new int[2][n];
		
		StringTokenizer st;
		for (int j = 0; j < n; j++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < 2; i++)
				benefits[i][j] = Integer.parseInt(st.nextToken());
		}
		
	}

	private static void benefit(int day, int benefit) {
		
		if (day == n) {
			maxBenefit = Math.max(maxBenefit, benefit);
			return;
		}
		
		if (day + benefits[0][day] <= n) benefit(day + benefits[0][day], benefit + benefits[1][day]);
		if (day + 1 <= n) benefit(day + 1, benefit); 
		
	}
	
	private static void print() {
		System.out.println(maxBenefit);
	}
}