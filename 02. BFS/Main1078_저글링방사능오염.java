import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=358&sca=50&sfl=wr_hit&stx=1078&sop=and
*/
 
public class Main1078_저글링방사능오염 {
     
    static int r, c, Sec, Live, startR, startC, map[][], dir[][];
    static boolean visit[][];
     
    public static void main(String[] args) throws IOException {
         
        input();
 
        nuclear();
 
        print();
         
    }
 
    private static void input() throws IOException {
         
        dir = new int[][]{   {-1, 0}, {1, 0}, {0, -1}, {0, 1}   };
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        c = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
         
        map = new int[r][c];
        visit = new boolean[r][c];
        char[] line;
        for (int i = 0; i < r; i++) {
            line = br.readLine().trim().toCharArray();
            for (int j = 0; j < c; j++)
                map[i][j] = Integer.parseInt(String.valueOf(line[j]));
        }
         
        st = new StringTokenizer(br.readLine().trim());
        startC = Integer.parseInt(st.nextToken()) - 1; 
        startR = Integer.parseInt(st.nextToken()) - 1;
    }
 
    private static void nuclear() {
         
        Queue<Zergling> que = new LinkedList<>();
         
        que.offer(new Zergling(startR, startC));
        map[startR][startC] = 3;
        visit[startR][startC] = true;
         
        int fromR, fromC, toR, toC, sec = 0;
        Zergling from;
        while (!que.isEmpty()) {
             
            from = que.poll();
            fromR = from.row;
            fromC = from.col;
            sec = map[fromR][fromC];
             
            for (int i = 0; i < 4; i++) {
                 
                toR = fromR + dir[i][0];
                toC = fromC + dir[i][1];
                 
                if (toR >= 0 && toR < r &&
                    toC >= 0 && toC < c &&
                    !visit[toR][toC] &&
                    map[toR][toC] == 1) {
                     
                    que.offer(new Zergling(toR, toC));
                    map[toR][toC] = sec + 1;
                    visit[toR][toC] = true;
                     
                }
                 
            }
             
        }
        Sec = sec;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] == 1) Live++;
            }
        }
         
    }
 
    private static void print() {
        System.out.println(Sec);
        System.out.println(Live);
    }
 
}
 
class Zergling {
    int row;
    int col;
    public Zergling(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public String toString() {
        return "[" + row + ", " + col + "]";
    }
}
