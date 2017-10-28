import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=427&sca=50&sfl=wr_hit&stx=1147&sop=and
*/

public class Main1147_주사위쌓기 {
	
	static int n;
	static int sum;
	static int[][] num;
	static int[] result;
	
	public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine().trim());
    	num = new int[n][6];
    	StringTokenizer st = null;
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < 6; j++) {
    			num[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	result = new int[6];
    	for (int i = 0; i < 6; i++) {
    		sum = 0;
    		fun(0, num[0][i]);  // 1번 주사위, 1번 주사위의 아랫면
    		result[i] = sum;
    	}
    	
    	Arrays.sort(result);
    	System.out.println(result[5]);
    	
    }
	
	public static void fun(int seq, int lower) { // seq : 현재 주사위 층수, upper : 현재 주사위의 아랫면 
		
		int max = 0;
		for (int j = 0; j < 6; j++) {
			int su = num[seq][j];
			if (su == lower || su == findUpper(seq, lower)) continue;
			if (max < su) max = su; 
		}
		sum += max;
				
		if (seq+1 < n)
			fun(seq+1, findUpper(seq, lower));  // 다음 주사위, 현재 주사위의 윗면
	}
	
	public static int findUpper(int presSeq, int presLower) { 
		// presSeq : 현재 주사위 층수, prevUpper : 현재 주사위 아랫면 숫자  >  현재 주사위 윗면 숫자
		int presLowerIndex = -1;
		int presUpperIndex = -1;
		for (int j = 0; j < 6; j++) {
			if (num[presSeq][j] == presLower) {
				presLowerIndex = j;
				break;
			}
		}
		switch (presLowerIndex) {
		case 0: presUpperIndex = 5;break;
		case 1: presUpperIndex = 3;break;
		case 2: presUpperIndex = 4;break;
		case 3: presUpperIndex = 1;break;
		case 4: presUpperIndex = 2;break;
		case 5: presUpperIndex = 0;break;
		}
		return num[presSeq][presUpperIndex];
	}
}
