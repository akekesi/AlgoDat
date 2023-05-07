import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PrintRules {
	public void print() throws FileNotFoundException {
		Scanner in = new Scanner(new FileInputStream("rules.txt"));
		while (in.hasNext()) {
			System.out.println(in.nextLine());
		}
		in.close();
	}
}
