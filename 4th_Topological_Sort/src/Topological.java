import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

public class Topological {

	private static Scanner scanner = new Scanner(System.in);
	private static int numberOfStudents;	// 1 <= numberOfStudents <= 32,000
	private static int counts;		// 1 <= compareCounts <= 100,000
	private static Queue<Integer> queue;
	private int[] predecessor;
	//private static LinkedList<Integer> adjacency;	// graph : ¸ñÀûÁö vertex
	private static ArrayList<Integer>[] graph;
	
	@SuppressWarnings("unchecked")
	
	public void main(String[] args) {
		// input
		numberOfStudents = scanner.nextInt();
		counts = scanner.nextInt();
		
		// Initialization		
		// graph
		graph = new ArrayList[numberOfStudents+1];
		for(int i=0; i<numberOfStudents+1; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		// predecessor
		predecessor = new int[numberOfStudents+1];
		
		// input & make graph
		for(int i=0; i<counts; i++) {
			int shorter = scanner.nextInt();
			int taller = scanner.nextInt();
			graph[shorter].add(taller);
			predecessor[taller]++;
		}
		
		System.out.println("d");
		
		// Topological Sort
		queue = new LinkedList<Integer>();
		
		for(int i=1; i<numberOfStudents+1; i++) {
			if(predecessor[i] == 0) {
				queue.offer(i);
			}
		}
		

		System.out.println("d");
		
		while(!queue.isEmpty()) {
			System.out.print(queue.peek()+ " ");
			int removedVertex = queue.poll();
			
			// iteration
			for(int i=0; i<graph[removedVertex].size(); i++) {
				int iterator = graph[removedVertex].get(i);
				predecessor[iterator]--;
				if(predecessor[iterator] == 0) {
					queue.offer(iterator);
				}
			}
		}

		System.out.println("d");
	}
}
