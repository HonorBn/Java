import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1144&sca=50&sfl=wr_hit&stx=1871&sop=and
*/

// Arrays.binarySearch  : -insertionPoint-1

public class Main1871_줄세우기_BinarySearch {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		
		int[] lis = new int[n];
		
		int temp, tempIndex; int size = 0;
		for (int i = 0; i < n; i++ ) {
			temp = Integer.parseInt(br.readLine().trim());
			tempIndex = -1 * (Arrays.binarySearch(lis, 0, size, temp)+1);
			lis[tempIndex] = temp;
			if (tempIndex==size) size++;
		}
		
		System.out.println(n-size);
	}

}
