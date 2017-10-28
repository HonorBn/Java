import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/4307
*/

public class Main4307_°³¹Ì_¹éÁØ {
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine().trim());
		
		StringTokenizer st;
		int n, pos, min, max, tempMin, tempMax, length;
		for (int i = 0; i < tc; i++) {
			
			st = new StringTokenizer(br.readLine().trim());
			
			length = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			
			min = 0; max = 0;
			
			for (int j = 0; j < n; j++) {
				
				pos = Integer.parseInt(br.readLine().trim());
				
				tempMin = Math.min(pos, length - pos);
				tempMax = Math.max(pos, length - pos);
				
				min = Math.max(min, tempMin);
				max = Math.max(max, tempMax);
				
			}
			
			sb.append(min).append(" ").append(max).append("\n");
			
		}
		
		System.out.println(sb.toString());
		
	}
}
