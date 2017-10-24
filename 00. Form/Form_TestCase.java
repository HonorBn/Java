import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Form_TestCase {
	
	static BufferedReader br;
	static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
		
		result = new StringBuilder();
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		int value;
		
		for (int tc = 1; tc <= T; tc++) {
			
			input();
			
			value = process();
			
			print(tc, value);
			
		}
		
		System.out.println(result.toString());
		
	}

	private static void input() throws IOException {
		
	}

	private static int process() {
		
		return 0;
	}

	private static void print(int tc, int value) {
		
		result.append("#").append(tc).append(" ").append(value).append("\n");
		
	}
	
}
