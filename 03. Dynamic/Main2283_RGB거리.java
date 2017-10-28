import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/1149
*/

public class Main2283_RGB거리 {
	
	static int n;
	static int[] cost;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine().trim());
		
		cost = new int[3];
		
		StringTokenizer st;
		int tempR, tempG, tempB, beforeR, beforeG, beforeB;
		for (int i = 0; i < n; i++) {
			
			st = new StringTokenizer(in.readLine().trim());
			
			tempR = Integer.parseInt(st.nextToken());
			tempG = Integer.parseInt(st.nextToken());
			tempB = Integer.parseInt(st.nextToken());

			beforeR = cost[0];
			beforeG = cost[1];
			beforeB = cost[2];

			cost[0] = tempR + Math.min(beforeG, beforeB);
			cost[1] = tempG + Math.min(beforeR, beforeB);
			cost[2] = tempB + Math.min(beforeR, beforeG);
		}
		
		System.out.println(Math.min(cost[0], Math.min(cost[1], cost[2])));
	}

}
