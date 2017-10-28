import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=300&sca=50&sfl=wr_hit&stx=1021&sop=and
*/

public class Main1021_ {
	
	static int n, m;		// 부품 종류 수, 부품 조합 가짓수
	static int[][] parts;		// 부품별 제작에 필요한 부품 종류와 개수
	static int[] cntBasic;		// 완성품 제작에 필요한 기본 부품 종류와 개수 (최종 출력 배열)
	static boolean[] ifBasic;	// 기본 부품 : true, 중간부품 or 완성품 : false
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		parts = new int[n][n]; 
		cntBasic = new int[n];
		ifBasic = new boolean[n];
		
		StringTokenizer st;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			parts	[Integer.parseInt(st.nextToken())-1]
					[Integer.parseInt(st.nextToken())-1]
				=	 Integer.parseInt(st.nextToken());	// i행 : 부품 번호, j열 : 하위 부품 번호, 값 : 개수
		}
		
		int rowSum;
		for (int i = 0; i < n; i++) {		// parts의 각 행에서 모든 열의 합이 0이면 기본 부품
			rowSum = 0;
			for (int j = 0; j < n; j++) rowSum += parts[i][j];
			if (rowSum == 0) ifBasic[i] = true; 
		}
		
//		for (int[] line : parts)
//			System.out.println(Arrays.toString(line));
		
		fun(n);	// 완성품 번호 입력
		
		for (int i = 0; i < cntBasic.length; i++) {		// 기본 부품만 그 개수 출력
			if (ifBasic[i]) System.out.printf("%d %d\n", i+1, cntBasic[i]);
		}
		
	}
	
	public static void fun(int partNumber) {	// 매개변수로 부품 번호 전달
		
		if (ifBasic[partNumber-1]) {
			cntBasic[partNumber-1]++;	// 해당 부품이 기본 부품이면 그 개수 증가
			return;
		}
		
		int[] part = parts[partNumber-1];	 // 해당 부품 필요한 부품 종류와 개수 추출
		for (int i = 0; i < part.length; i++) {
			if (part[i] > 0) {	// 필요 부품 수가 1개 이상일 경우
				for (int j = 0; j < part[i]; j++) { // 필요 부품 번호를 매개변수로 그 개수만큼 반복 호출
					fun(i+1);
				}
			}
		}
	}
}
