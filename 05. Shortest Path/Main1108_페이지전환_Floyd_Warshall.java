import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=388&sca=50&sfl=wr_hit&stx=1108&sop=and
*/

public class Main1108_페이지전환_Floyd_Warshall {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int count = Integer.parseInt(in.readLine().trim());
        final int INFINITY = 500;
        int [][] info = new int[count][2];
         
        StringTokenizer st = null;
        
        int page1 = 0, page2 = 0, endPage = 0, max = 0;
        for(int i=0; i<count; ++i){
        	st = new StringTokenizer(in.readLine().trim());
        	page1 = Integer.parseInt(st.nextToken());
        	page2 = Integer.parseInt(st.nextToken());
        	
        	// 마지막 페이지 찾기
        	if((max=Math.max(page1, page2))>endPage){
        		endPage = max;
        	}

        	info[i][0] = page1;
        	info[i][1] = page2;
        }
        
         
        int [][] matrix = new int[endPage+1][endPage+1];
         
        for (int i = 0; i <= endPage; i++) {
            for (int j = 0; j <= endPage; j++) {
            	if (i==j){
            		continue;
            	}
                matrix[i][j] = INFINITY;
            }
        }
        
        
        // 이동가능한 페이지는 1로.
        for(int[] a : info){
        	matrix[a[0]][a[1]] = 1;
        }       
        // 갈수없는 곳은 INFINITY로. 갈수 있는 곳은 1로, I==J인 경우는 0으로 셋팅완료
/*  
        for(int i=0; i<matrix.length; ++i){
        	System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println("==================================");*/
        
        // 출발지-->경유지-->목적지로 3중 루프 돌리면 오답
        // 경유지-->출발지-->목적지로 3중 루프 돌려야 정답
		for (int k = 1; k <= endPage; k++) { // 경유지
	START_LOOP:	for (int i = 1; i <= endPage; i++) { // 출발지
				for (int j = 1; j <= endPage; j++) { // 목적지
					
					if(i==k){//출발지와 경유지가 같으면
							 //경유하는 것으로 인한 UPDATE가 필요없으므로 다음 출발지로.
						continue START_LOOP;
					}
					if(i==j || j==k){ //출발지와 목적지가 같다면 자기자신으로임
									 // 경유지와 도착지가 같으면 skip
						continue;
					}
					// 출발지(i)에서 목적지(j)까지 오는 기존거리가 출발지(i)에서 목적지(j)까지 경유지(k)를 거쳐
					// 오는 거리보다 크다면 갱신(즉, 경유지를 거쳐 오는것이 작다면 갱신)
					if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}
/*		
        for(int i=0; i<matrix.length; ++i){
        	System.out.println(Arrays.toString(matrix[i]));
        }
        */
         
        double sum = 0;
        //어떤 페이지에서 다른 페이지로 갈 수 없는 경우는 없도록 데이터가 입력된다고 했으므로
        //결국 다른 페이지들을 모두 경우지로 고려했을 경우 matrix의 모든 값은 0과 0이 아닌값으로 채워짐
        for (int i = 1; i <= endPage; i++) {
            for (int j = 1; j <= endPage; j++) {
                sum+=matrix[i][j];
            }
        }
//        System.out.println(sum);
        System.out.printf("%.3f",sum/((double)endPage*(endPage-1)));
    }
}
