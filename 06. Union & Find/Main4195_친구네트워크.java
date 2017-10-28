import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/* URL
   https://www.acmicpc.net/problem/4195
*/

public class Main4195_친구네트워크 {
	
	static int network[];
	
    public static void main(String[] args) throws Exception {
    	
    	StringBuilder result = new StringBuilder();
    	HashMap<String, Integer> naming = new HashMap<>();
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		
		int m, id, rootA, rootB;
		String friend1, friend2;
		StringTokenizer st;
		
		for (int i = 0; i < n; i++) {
			
			m = Integer.parseInt(br.readLine().trim());
			
			id = 0;
			network = new int[2*m];
			Arrays.fill(network, -1);
			
			for (int j = 0; j < m; j++) {
				
				st = new StringTokenizer(br.readLine().trim());
				
				friend1 = st.nextToken();
				friend2 = st.nextToken();
				if (!naming.containsKey(friend1)) naming.put(friend1, id++);
				if (!naming.containsKey(friend2)) naming.put(friend2, id++);
				
				rootA = find(naming.get(friend1));
				rootB = find(naming.get(friend2));
				
				if (rootA != rootB) {
					network[rootA] = network[rootA] + network[rootB];
					network[rootB] = rootA;
				}
				
				result.append(Math.abs(network[rootA])).append("\n");
			}
			
			naming.clear();
		}
		
		System.out.println(result.toString());
		
    }
    
    private static int find(int son) {
    	
    	if (network[son] < 0) return son;
    	
    	int parent = find(network[son]);
    	
    	if (network[son] < 0) {
    		network[parent] = network[parent] + network[son];
    		network[son] = parent;
    	}
    	
    	return parent;
    	
    }
    
}
