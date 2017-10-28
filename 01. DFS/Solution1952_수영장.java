import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* URL
   SW Expert Academy
*/

public class Solution1952_수영장 {
     
    static BufferedReader br;
    static StringBuilder result;
     
    static int minCost, price[], plan[], priceMonth[];
     
    public static void main(String[] args) throws IOException {
         
        result = new StringBuilder();
         
        br = new BufferedReader(new InputStreamReader(System.in));
         
        int T = Integer.parseInt(br.readLine().trim());
         
        int value;
         
        for (int tc = 1; tc <= T; tc++) {
             
            input();
             
            value = process();
             
            print(tc, value);
             
        }
         
        System.out.println(result.toString());
         
    }
 
    private static void input() throws IOException {
         
        minCost = Integer.MAX_VALUE;
         
        price = new int[4];
        plan = new int[12];
        priceMonth = new int[12];
         
        StringTokenizer st;
         
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 4; i++)
            price[i] = Integer.parseInt(st.nextToken());
         
        int yearPrice = 0;
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 12; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
            if (plan[i] > 0)
                yearPrice += priceMonth[i] = Math.min(price[1], plan[i] * price[0]);
        }
         
        minCost = Math.min(price[3], yearPrice);
         
    }
 
    private static int process() {
         
        threeMonth(0, 0);
         
        return minCost;
    }
     
    private static void threeMonth(int month, int cost) {
         
        if (month == 12) {
            minCost = Math.min(cost, minCost);
            return;
        }
         
        if (month+3 <= 12) threeMonth(month+3, cost+price[2]);
        if (month+1 <= 12) threeMonth(month+1, cost+priceMonth[month]);
    }
 
    private static void print(int tc, int value) {
         
        result.append("#").append(tc).append(" ").append(value).append("\n");
         
    }
     
}
