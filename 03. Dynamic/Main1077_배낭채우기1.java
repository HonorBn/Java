import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1077_¹è³¶Ã¤¿ì±â1 {
	
	static int n, w, dynamic[], data[][];
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		bag();
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		
		data = new int[n+1][2];
		dynamic = new int[w+1];
		
		for (int i = 1; i <= n; i++ ) {
			st = new StringTokenizer(br.readLine().trim());
			data[i][0] = Integer.parseInt(st.nextToken());
			data[i][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	private static void bag() {
		
		for (int i = 1; i <= n; i++)
			for (int j = data[i][0]; j <= w; j++)
				dynamic[j] = Math.max(dynamic[j], dynamic[j-data[i][0]] + data[i][1]);
				
	}
	
	private static void print() {
		
		System.out.println(dynamic[w]);
		
	}
}