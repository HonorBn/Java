import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/14499
*/

public class Main14499_주사위굴리기 {
	
	static int r, c, k;
	static int[] dice, start, comms, roundIndex;
	static int[][] map, dir;
	static StringBuilder result;
	
	public static void main(String[] args) throws IOException {
		
		input();
		
		roll(0, 6, start[0], start[1]);
		
		print();
	}

	private static void input() throws IOException {
		
		result = new StringBuilder();
		roundIndex = new int[]{0, 3, 4, 2, 5};
		dice = new int[]{0, 0, 0, 0, 0, 0, 0};
		dir = new int[][]{	{0, 0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}	};
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		start = new int[]{Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken())};
		
		k = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		comms = new int[k];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < k; i++) {
			comms[i] = Integer.parseInt(st.nextToken());
		}
	}

	private static void roll(int commIndex, int presIndex, int fromR, int fromC) {
		
		if (commIndex == k) return;
		
		int dirIndex = comms[commIndex];
		int toR = fromR + dir[dirIndex][0], toC = fromC + dir[dirIndex][1];
		
		int nextIndex = roundIndex[dirIndex];
		
		if (toR >=0 && toC >= 0 && toR < r && toC < c) {
			
			if (map[toR][toC] == 0) {
				map[toR][toC] = dice[nextIndex];
			} else {
				dice[nextIndex] = map[toR][toC];
				map[toR][toC] = 0;
			}
			
			renewDir(presIndex, dirIndex);
			result.append(dice[7-nextIndex]).append("\n");
			roll(commIndex+1, nextIndex, toR, toC);
			
		} else {
			roll(commIndex+1, presIndex, fromR, fromC);
		}
		
	}
	
	private static void renewDir(int presIndex, int dirIndex) {
		
		switch(dirIndex) {
		case 1:
			roundIndex[1] = 7 - presIndex;
			roundIndex[2] = presIndex;
			break;
		case 2:
			roundIndex[1] = presIndex;
			roundIndex[2] = 7 - presIndex;
			break;
		case 3:
			roundIndex[3] = 7 - presIndex;
			roundIndex[4] = presIndex;
			break;
		case 4:
			roundIndex[3] = presIndex;
			roundIndex[4] = 7 - presIndex;
			break;
		}
	}
	
	private static void print() {
		System.out.println(result.toString());
	}
}
