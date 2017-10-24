import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1309_팩토리얼 {
	
	public static void main(String[] args) throws IOException {
    	
    	// Reader
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
//    	// 주어진 숫자 크기 배열
    	int n = Integer.parseInt(br.readLine().trim());
//    	int[] num = new int[n];
    	
//    	// Tokenizer
//    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
//    	// 숫자 2개
//    	int a = Integer.parseInt(st.nextToken());
//    	int b = Integer.parseInt(st.nextToken());
    	
//    	// 주어진 숫자 배열 입력
//    	for (int i = 0; i < n; i++) {
//    		num[i] = Integer.parseInt(st.nextToken());
//    	}
    	
    	System.out.println(fun(n));
    }
	
	public static long fun(int n) {
		if (n == 0) return 1;
		else if (n == 1) System.out.printf("%d! = %d\n", n, n);
		else System.out.printf("%d! = %d * %d!\n", n, n, n-1);
		return n * fun(--n);
		
	}
}
