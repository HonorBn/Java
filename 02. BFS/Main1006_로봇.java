import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=285&sca=50&sfl=wr_hit&stx=1006&sop=and
*/
 
public class Main1006_로봇 {
 
    static int r, c, start[], end[], startPole, endPole, minCnt, dir[][], map[][][];
    static final int EAST = 1, WEST = 2, SOUTH = 3, NORTH = 4;
 
    public static void main(String[] args) throws IOException {
 
        input();
 
        robot();
         
        print();
    }
 
    private static void input() throws IOException {
         
        minCnt = Integer.MAX_VALUE;
        dir = new int[][] { { 0, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
         
        int su;
        map = new int[r][c][];
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                su = Integer.parseInt(st.nextToken());
                if (su == 0) map[i][j] = new int[5];
            }
        }
         
        st = new StringTokenizer(br.readLine().trim());
        start = new int[]{  Integer.parseInt(st.nextToken()) - 1,
                            Integer.parseInt(st.nextToken()) - 1    };
        startPole = Integer.parseInt(st.nextToken());
         
        st = new StringTokenizer(br.readLine().trim());
        end =   new int[]{  Integer.parseInt(st.nextToken()) - 1,
                            Integer.parseInt(st.nextToken()) - 1    };
        endPole = Integer.parseInt(st.nextToken());
    }
 
    private static void robot() {
 
        Queue<int[]> que = new LinkedList<>();
 
        que.offer(start);
        writeCnt(start[0], start[1], startPole, 0);
         
        int cnt, from[], fromR, fromC, toR, toC;
        while (!que.isEmpty()) {
 
            from = que.poll();
            fromR = from[0];
            fromC = from[1];
             
            if (fromR == end[0] && fromC == end[1]) {
                minCnt = map[fromR][fromC][endPole];
                break;
            }
             
            for (int pole = EAST; pole <= NORTH; pole++) {
                 
                cnt = map[fromR][fromC][pole];
                 
                for (int j = 1; j <= 3; j++) {
                 
                    toR = fromR + dir[pole][0] * j;
                    toC = fromC + dir[pole][1] * j;
                     
                    if ((toR != start[0] || toC != start[1]) &&
                        toR >= 0 && toR < r &&
                        toC >= 0 && toC < c &&
                         map[toR][toC] != null &&
                        (map[toR][toC][pole] == 0 ||
                         map[toR][toC][pole] >= cnt + 1)) {
                         
                        que.offer(new int[]{toR, toC});
                        writeCnt(toR, toC, pole, cnt+1);
                         
                    } else break;
                }
            }
        }
    }
     
    private static void print() {
        System.out.println(minCnt);
    }
     
    private static void writeCnt(int row, int col, int pole, int cnt) {
         
        switch(pole) {
        case EAST:
            if (map[row][col][EAST] == 0 || map[row][col][EAST] > cnt) map[row][col][EAST] = cnt;
            if (map[row][col][WEST] == 0 || map[row][col][WEST] > cnt+2) map[row][col][WEST] = cnt+2;
            if (map[row][col][SOUTH] == 0 || map[row][col][SOUTH] > cnt+1) map[row][col][SOUTH] = cnt+1;
            if (map[row][col][NORTH] == 0 || map[row][col][NORTH] > cnt+1) map[row][col][NORTH] = cnt+1;
            break;
        case WEST:
            if (map[row][col][EAST] == 0 || map[row][col][EAST] > cnt+2) map[row][col][EAST] = cnt+2;
            if (map[row][col][WEST] == 0 || map[row][col][WEST] > cnt) map[row][col][WEST] = cnt;
            if (map[row][col][SOUTH] == 0 || map[row][col][SOUTH] > cnt+1) map[row][col][SOUTH] = cnt+1;
            if (map[row][col][NORTH] == 0 || map[row][col][NORTH] > cnt+1) map[row][col][NORTH] = cnt+1;
            break;
        case SOUTH:
            if (map[row][col][EAST] == 0 || map[row][col][EAST] > cnt+1) map[row][col][EAST] = cnt+1;
            if (map[row][col][WEST] == 0 || map[row][col][WEST] > cnt+1) map[row][col][WEST] = cnt+1;
            if (map[row][col][SOUTH] == 0 || map[row][col][SOUTH] > cnt) map[row][col][SOUTH] = cnt;
            if (map[row][col][NORTH] == 0 || map[row][col][NORTH] > cnt+2) map[row][col][NORTH] = cnt+2;
            break;
        case NORTH:
            if (map[row][col][EAST] == 0 || map[row][col][EAST] > cnt+1) map[row][col][EAST] = cnt+1;
            if (map[row][col][WEST] == 0 || map[row][col][WEST] > cnt+1) map[row][col][WEST] = cnt+1;
            if (map[row][col][SOUTH] == 0 || map[row][col][SOUTH] > cnt+2) map[row][col][SOUTH] = cnt+2;
            if (map[row][col][NORTH] == 0 || map[row][col][NORTH] > cnt) map[row][col][NORTH] = cnt;
            break;
        }
    }
}
