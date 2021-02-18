import java.util.Scanner;
 
public final class AppView {
	
	// Instance Variables
	private static Scanner scanner = new Scanner(System.in); 
	
	// Constructor
	private AppView() { 
	}
	
	
	
	// Public Methods
	public static void outputLine(String aString) {
		System.out.println(aString);
	}
	
	public static void output(String aString) {
		System.out.print(aString);
	}
	
	
	public static int inputInt() throws NumberFormatException {
		//정수가 아닌 경우의 에러처리를 보완할 것 : exception throws
		return Integer.parseInt(AppView.scanner.next());
	}
}
