import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=466&sca=50&sfl=wr_hit&stx=1183&sop=and
*/

public class Main1183_동전자판기_하 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int value = Integer.parseInt(br.readLine().trim());
		
		// 500, 100, 50, 10, 5, 1
		int max = 0;
		int[] num = new int[6];
		int[] answer = new int[6];
		int[] coin = {500, 100, 50, 10, 5, 1};
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 6; i++) {
			num[i] = Integer.parseInt(st.nextToken());
			max += coin[i] * num[i];
		}
		
		int sum = 0;
		int reverse = max - value;
		for (int i = 0; i < 6; i++) {
			answer[i] = (reverse / coin[i] >= num[i])? num[i] : reverse / coin[i];
			reverse = reverse - answer[i] * coin[i];
			answer[i] = num[i] - answer[i];
			sum += answer[i];
		}
		
		System.out.println(sum);
		for (int i : answer) System.out.print(i + " ");
	}
}
