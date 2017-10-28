import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/* URL
   http://www.jungol.co.kr/bbs/board.php?bo_table=pbank&wr_id=1522&sca=50&sfl=wr_hit&stx=2261&sop=and
*/

public class Main2261_경로찾기 {
      
    static int n;
    static int k;
     
    public static void main(String[] args) throws IOException {
          
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
          
        String[] list = new String[n+1];
        boolean[] check = new boolean[n+1];
          
        for (int i = 1; i < n+1; i++) {
            list[i] = br.readLine();
        }
          
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
          
        Queue<ArrayList<Integer>> que = new LinkedList<>();
          
        ArrayList<Integer> path = new ArrayList<>();
        path.add(start);
        check[start] = true;
        que.offer(path);
          
        int lastIndex = 0;
        boolean result = false;
        ArrayList<Integer> temp1 = null;
        ArrayList<Integer> temp2 = null;
          
        while (!que.isEmpty()) {
              
            temp1 = que.poll();
            lastIndex = temp1.size()-1;
              
            if (temp1.contains(new Integer(end))) {
                result = true;
                break;
            }
              
            for (int i = 1; i < n+1; i++) {
                  
                if (!temp1.contains(new Integer(i)) && !check[i] && find(list[temp1.get(lastIndex)], list[i])) {
                    temp2 = new ArrayList<>();
                    temp2.addAll(temp1);
                    temp2.add(i);
                    check[i] = true;
                    que.add(temp2);
                }
            }
        }
          
        StringBuilder sb = new StringBuilder();     
        if (!result) System.out.println(-1);
        else {
            for (int i : temp1)
                sb.append(i).append(" ");
            System.out.println(sb);
        }
    }
      
    public static boolean find(String a, String b) {
          
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();
          
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (aa[i] - bb[i] != 0) cnt++;
            if (cnt >= 2) {
                return false;
            }
        }
        return true;
    }
}
