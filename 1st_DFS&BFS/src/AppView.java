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
		//������ �ƴ� ����� ����ó���� ������ �� : exception throws
		return Integer.parseInt(AppView.scanner.next());
	}
}
