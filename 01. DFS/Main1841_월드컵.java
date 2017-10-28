import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1114&sca=50&sfl=wr_hit&stx=1841&sop=and
*/

public class Main1841_월드컵 {

	static final int n = 6; // 팀수(나라 수)
	static final int m = 15; // 6나라 중 2나라씩 치루는 총 경기의 수 6C2
	static int win[], lose[], draw[];
	static int tryWin[], tryLose[], tryDraw[];
	static int player1[], player2[];
	static boolean feasibility;

	// cnt에 해당하는 경기의 두팀에 대해 3가지의 경우로 시도해봄
	// 각 경우가 가능하다면 해당 경우에서 다음 경기로 재귀호출 
	static void recur(int cnt) {
		if (cnt == m) {// 마지막 경기까지 다 가능했었다면 
			feasibility = true;
			return;
		}
		
		int p1 = player1[cnt];
		int p2 = player2[cnt];
		
		
		///////////////////////////////////////////////////////////
		
		
		tryWin[p1]++;// player1팀 이기는 처리
		tryLose[p2]++;//player2팀 지는 처리
		if (tryWin[p1] <= win[p1] && tryLose[p2] <= lose[p2]){ // 위에서 처리한 결과값이 기자가 보내온 결과값보다 작거나 같다면 
														// 다음 경기정보도 계속 재귀호출 하여 시도해봄.
			recur(cnt + 1);
			if(feasibility){ // 마지막 경기까지 try한 결과 가능했다면 기자의결과 가능한 결과이므로 재귀멈춤.
				return;
			}
		}
		tryWin[p1]--; // 위에서 처리한 결과 되돌려놓음
		tryLose[p2]--;// 위에서 처리한 결과 되돌려놓음
		
		
		///////////////////////////////////////////////////////////
		
		
		tryDraw[p1]++;// player1팀 비기는 처리
		tryDraw[p2]++;//player2팀 비기는 처리
		if (tryDraw[p1] <= draw[p1] && tryDraw[p2] <= draw[p2]){
			recur(cnt + 1);
			if(feasibility){
				return;
			}
		}
		tryDraw[p1]--;// 위에서 처리한 결과 되돌려놓음
		tryDraw[p2]--;// 위에서 처리한 결과 되돌려놓음
		
		
		///////////////////////////////////////////////////////////
		
		
		tryLose[p1]++;//player1팀 지는 처리
		tryWin[p2]++;//player2팀 이기는 처리
		if (tryLose[p1] <= lose[p1] && tryWin[p2] <= win[p2]){
			recur(cnt + 1);
			if(feasibility){
				return;
			}
		}
		tryLose[p1]--;// 위에서 처리한 결과 되돌려놓음
		tryWin[p2]--;// 위에서 처리한 결과 되돌려놓음
	}

	static void process() {
		feasibility = false;
		Arrays.fill(tryWin, 0);
		Arrays.fill(tryLose, 0);
		Arrays.fill(tryDraw, 0);
		recur(0);
	}

	public static void main(String[] args) throws IOException {
		win = new int[n];	// 각 팀별 승 무 패 배열
		lose = new int[n];
		draw = new int[n];
		
		tryWin = new int[n];	// 경기를 조합해가면서 승무를 누적하는 배열
		tryLose = new int[n];	// 위의 win lose draw와 대조해나가면서 결과 도출
		tryDraw = new int[n];
		
		player1 = new int[m];	// 모든 경기의 조합 저장
		player2 = new int[m];

		// 경기 조합만들기
		// 0-1,2,3,4,5   1-2,3,4,5  2-3,4,5   3-4,5   4-5
		for (int i = 0, cnt = 0; i < n; i++){
			for (int j = i + 1; j < n; j++) {
				player1[cnt] = i;
				player2[cnt] = j;
				cnt++;
			}
		}
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		L:for (int c = 0; c < 4; c++) {
			st = new StringTokenizer(in.readLine());
			for (int i = 0; i < n; i++) {
				win[i] = Integer.parseInt(st.nextToken());
				draw[i] = Integer.parseInt(st.nextToken());
				lose[i] = Integer.parseInt(st.nextToken());
				
				if (win[i] + lose[i] + draw[i] != n - 1){ // 이기고 지고 비긴 경기의 합이 n-1 즉, 5경기가 아니면 무조건 불가능한 결과임
					System.out.print(0+" ");
					continue L; //다음 케이스로 건너뜀
				}
			}
			process();
			if (feasibility) {
				System.out.print(1+" ");
			} else {
				System.out.print(0+" ");

			}
		}

	}

}
