import java.util.Arrays;
import java.util.Scanner;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1144&sca=50&sfl=wr_hit&stx=1871&sop=and
*/

public class Main1871_줄세우기_lis {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		int[] arr = new int[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = s.nextInt();
		}
		s.close();
		int dynamic[] = new int[n+1]; // len[x] : x번째 수를 마지막 원소로 가지는 lis의 길이 


		for (int i = 1; i <= n; i++) {// 기존 증가수열에 덧붙일 대상
			for (int j = 0 ; j < i; j++) { // 맨 처음 원소부터 덧붙일 대상 전 원소 각각과 비교하여 덧불일 수 있는지 체크하고 
											// 덧붙일 수 있다면 덧붙이는 것이 자신의 기존 최장길이보다 유리할 경우만 update 
				// len[j] + 1 > len[i] 비교해야하는 이유
				// len[i] : j처리전까지의 최대길이,  len[j]+1 : j뒤에 i를 덧붙였을때까지의 길이
				// 정렬이 되어 있는 데이터가 아니므로 j처리전까지의 최대길이가 j까지의최대길이+1보다 더 클수 있다.
				// 결국 i전에 세울수 있는 번호들 중 최대길이값을 이용하게 됨 ==> 인덱스트리 활용포인트
				if (arr[j] < arr[i] && dynamic[j] + 1 > dynamic[i]) {
						dynamic[i] = dynamic[j] + 1;
				}
			}
		}
		Arrays.sort(dynamic);//오름차순 정렬하여 가장 큰값이 끝으로 오게함.(max값 : 최장증가수열의 길이)
		System.out.println(n - dynamic[n]); // 이동할 아이의 수이므로 전체인원수에서 최장증가수열의 길이(이동하지 않는 아이의 최대길이)를 뺀다.

	}

}
