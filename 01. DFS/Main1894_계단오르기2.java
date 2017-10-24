import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1894_계단오르기2 {
	
	static int n, cnt;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		step(0);
		
		print();
		
	}

	private static void input() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
	}
	
	private static void step(int layer) {
		
		if (layer == n) {
			cnt++;
			return;
		}
		
		if (layer+1 <= n) step(layer+1);
		if (layer+2 <= n) step(layer+2);
	}
	
	private static void print() {
		
		System.out.println(cnt);
		
	}
	
}