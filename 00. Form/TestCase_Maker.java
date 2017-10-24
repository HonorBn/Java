import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class TestCase_Maker {

	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter out = new PrintWriter("input.txt");
		
		Random r = new Random();
		
		out.println(1);
		out.println("4 2");
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				out.print(r.nextInt(6)+1);
				out.print(" ");
			}
			out.println();
		}
		
		out.close();
	}

}
