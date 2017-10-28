import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=340&sca=50&sfl=wr_hit&stx=1060&sop=and
*/

public class Main1060_최소비용신장트리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine().trim());
		int[][] input = new int[n][n];
		StringTokenizer st = null;
		
		for(int i=0; i<n; ++i){
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<n; ++j){
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		boolean[] visited = new boolean[n];
		ArrayList<Integer> list = new ArrayList<Integer>();
		int result = 0,min,minIndex=0;
		
		visited[0] = true;
		list.add(0);
		
		while(true){
			min = Integer.MAX_VALUE;
			minIndex=-1;
			
			for (Integer i : list) {//방문정점 인덱스
				for(int j=0; j<n; ++j){// 해당 방문정점 기준의 인접 정점 체크
					if(!visited[j] && input[i][j] != 0 
							&& input[i][j] < min){
						min = input[i][j];
						minIndex = j;
					}
				}
			}
			result += min;
			visited[minIndex] = true;
			list.add(minIndex);
			
			if(list.size() == n){
				break;
			}
			
		}
		
		System.out.println(result);
	}
}











