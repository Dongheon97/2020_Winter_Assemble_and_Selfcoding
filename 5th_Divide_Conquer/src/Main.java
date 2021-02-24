import java.util.Scanner;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static int inputNumber;			// 2의 배수
	private static char[][] graph;			// graph
	
	public static void main(String[] args) {
		inputNumber = scanner.nextInt();
		
		// 그래프 생성
		graph = new char[inputNumber][inputNumber];
		
		for(int i=0; i<inputNumber; i++) {
			// 단어 하나씩 분리
			graph[i] = scanner.next().toCharArray();	
		}
		
		System.out.print("(");
		divide(0, 0, inputNumber);
		System.out.print(")");
		
	}
	
	private static boolean areSame(int row, int col, int givenNumber) {
		for(int i = row; i < row+givenNumber; i++) {
			for(int j = col; j < col+givenNumber; j++) {
				if(graph[row][col] != graph[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static void divide(int row, int col, int givenNumber) {
		if(areSame(row, col, givenNumber)) {
			System.out.print(graph[row][col]);
		}
		else {
			int half = givenNumber/2;
			System.out.print("(");
			for(int i=0; i<2; i++) {
				for(int j=0; j<2; j++) {
					divide( (row+(i*half)), (col+(j*half)), half);
				}
			}
			System.out.print(")");
		}
	}
}
