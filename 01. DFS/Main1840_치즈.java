import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1113&sca=50&sfl=wr_hit&stx=1840&sop=and
*/

public class Main1840_ {
     
    static int n, m, cnt, time;
    static int[][] dir = { {-1,0}, {1,0}, {0,-1}, {0,1} };
    static int[][] board;
     
    public static void main(String[] args) throws IOException {
         
        input();
         
        int cnt = 0;
        int time = 0;
         
        while (true) {
             
            setOuter(0, 0);
             
            findCheese();
             
            cnt = lastCheese(); 
             
            melt();
             
            time++;
             
            if (check()) break;
        }
         
        System.out.println(time);
        System.out.println(cnt);
    }
     
    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
         
        int su = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                su = Integer.parseInt(st.nextToken());
                board[i][j] = su;
            }
        }
    }
     
    private static void setOuter(int row, int col) {
         
        board[row][col] = 9;
         
        int newR = 0;
        int newC = 0;
         
        for (int i = 0; i < dir.length; i++) {
 
            newR = row + dir[i][0];
            newC = col + dir[i][1];
             
            if (newR >= 0 && newR < n &&
                newC >= 0 && newC < m &&
                board[newR][newC] == 0)
                setOuter(newR, newC);
        }
         
    }
 
    private static void findCheese() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 1 &&
                        (board[i-1][j] == 9 || board[i+1][j] == 9 ||
                         board[i][j-1] == 9 || board[i][j+1] == 9))
                    board[i][j] = 2;
            }
        }
    }
     
    private static int lastCheese() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 2) cnt++;
            }
        }
        return cnt;
    }
     
    private static void melt() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 9 || board[i][j] == 2)
                    board[i][j] = 0;
            }
        }
    }
     
    private static boolean check() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += board[i][j];
            }
        }
        return sum == 0;
    }
}
