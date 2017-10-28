import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/10875
*/

public class Main10875_뱀 {
	
	static long life;
	static ArrayList<int[]> body;
	static int l, n, turns[][], move[][];
	static final int MAX_LENGTH = (int) (2 * Math.pow(10, 8));
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		snake();
		
		print();
	}

	private static void input() throws IOException {
		
		body = new ArrayList<int[]>();
		move = new int[][]{	{-1, 0}, {1, 0}, {0, -1}, {0, 1}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		l = Integer.parseInt(br.readLine().trim());
		n = Integer.parseInt(br.readLine().trim());
		
		turns = new int[n][2];
		
		StringTokenizer st;
		for (int i = 0; i < n; i++ ) {
			st = new StringTokenizer(br.readLine().trim());
			turns[i] = new int[]{Integer.parseInt(st.nextToken()), (st.nextToken().equals("L"))? 0:1};
		}
		
		body.add(new int[]{	-l-1,	-l-1,	-l-1,	l		});
		body.add(new int[]{	-l-1,	l+1,	l,		l+1		});
		body.add(new int[]{	l+1,	-l,		l+1,	l+1		});
		body.add(new int[]{	-l,		-l-1,	l+1,	-l-1	});
		
	}
	
	private static void snake() {
		
		int fromR = 0, fromC = 0, dir = 3;
		int toR, toC, time, len, minLen;
		
		for (int i = 0; i <= n; i++) {
			
			if (i < n) {
				toR = fromR + move[dir][0] * turns[i][0];
				toC = fromC + move[dir][1] * turns[i][0];
			} else {
				toR = (dir > 1)? fromR : 
						(dir > 0)? MAX_LENGTH : -MAX_LENGTH;
				toC = (dir < 2)? fromC : 
						(dir < 3)? -MAX_LENGTH : MAX_LENGTH;
			}
			
			time = (fromR == toR)?
						(fromC > toC)? fromC-toC : toC-fromC
						:
						(fromR > toR)? fromR-toR : toR-fromR; 
			
			// 몸 확인 (벽 포함)
			minLen = MAX_LENGTH;
			for (int[] lineYou : body) {
				if (intersect(dir, fromR, fromC, toR, toC, lineYou)) {
					len = toBody(dir, fromR, fromC, lineYou);
					if (len < minLen) minLen = len;
				}
			}
			
			if (minLen < MAX_LENGTH) {
				life += minLen;
				return;
			}
			
			// 생존 : 몸 저장, 위치와 방향 갱신
			body.add(newBody(dir, fromR, fromC, toR, toC));
			
			fromR = toR; fromC = toC; life += time;
			dir = (turns[i][1] == 0)?
						(dir < 2)? 5-(3-dir) : 3-dir
						:
						(dir < 2)? 3-dir : 1-(3-dir);
			
		}
		
	}
	
	private static boolean intersect(int dir, int meFromR, int meFromC, int meToR, int meToC, int[] lineYou) {
		
		int a1row, a1col, a2row, a2col;
		if (dir % 2 == 0) {
			a1row = meToR; a1col = meToC; a2row = meFromR; a2col = meFromC;	}
		else {
			a1row = meFromR; a1col = meFromC; a2row = meToR; a2col = meToC;	}
		
        int b1row = lineYou[0], b1col = lineYou[1], b2row = lineYou[2], b2col = lineYou[3];
         
        if (a1row == a2row) {
            if (b1row == b2row) return a1row == b1row && a2col >= b1col && a1col <= b2col;
            else return a1row >= b1row && a1row <= b2row && b1col >= a1col && b1col <= a2col;	}
        else {
            if (b1col == b2col) return a1col == b1col && a2row >= b1row && a1row <= b2row;
            else return a1col >= b1col && a1col <= b2col && b1row >= a1row && b1row <= a2row;	}
        
    }
	
	private static int toBody(int dir, int fromR, int fromC, int[] intersect) {
		
		switch (dir) {
		case 0:
			return Math.abs(intersect[2] - fromR);
		case 1:
			return Math.abs(intersect[0] - fromR);
		case 2:
			return Math.abs(intersect[3] - fromC);
		default:
			return Math.abs(intersect[1] - fromC);
		}
		
	}
	
	private static int[] newBody(int dir, int fromR, int fromC, int toR, int toC) {
		
		switch (dir) {
		case 0:
			return new int[]{toR+1, toC, fromR, fromC};
		case 1:
			return new int[]{fromR, fromC, toR-1, toC}; 
		case 2:
			return new int[]{toR, toC+1, fromR, fromC};
		default:
			return new int[]{fromR, fromC, toR, toC-1};
		}
		
	}
	
	private static void print() {
		
		System.out.println(life);
		
	}
}
