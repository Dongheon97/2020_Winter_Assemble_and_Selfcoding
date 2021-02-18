import java.util.Scanner;
import java.util.PriorityQueue;

public class _Main_Kruskal {

	private static Scanner scanner = new Scanner(System.in);
	static int[] rootArray;
	static PriorityQueue<Edge> edgeQueue;
	
	public static void main(String[] args) {
		
		// input number
		int numberOfVertex = scanner.nextInt();
		int numberOfEdge = scanner.nextInt();
		rootArray = new int[numberOfVertex+1];
	
		// 배열 초기화(single set)
		for(int i=1; i<rootArray.length; i++) {
			rootArray[i] = i;
		}
		edgeQueue = new PriorityQueue<Edge>(numberOfEdge);
		
		for(int i=0; i<numberOfEdge; i++) {
			int inputTailVertex = scanner.nextInt();
			int inputHeadVertex = scanner.nextInt();
			int inputCost = scanner.nextInt();
			edgeQueue.add(new Edge(inputTailVertex, inputHeadVertex, inputCost));
		}
		
		int sumOfCost = 0;
		
		while(!edgeQueue.isEmpty()) {
			Edge removedEdge = edgeQueue.remove();
			int removedTail = removedEdge.tailVertex();
			int removedHead = removedEdge.headVertex();
			
			// find 한 결과가 같으면 cycle 형성, 
			// 그렇지 않으면 cycle을 형성하지 않으므로 union한다.
			if(find(removedTail) != find(removedHead)) {
				union(removedTail, removedHead);
				sumOfCost += removedEdge.cost();
			}
		}
		
		System.out.println(sumOfCost);
	}
	
	public static void union(int tailVertex, int headVertex) {
		int tail = find(tailVertex);
		int head = find(headVertex);
		
		if(tail != head) {
			if(tail > head) {
				rootArray[tail] = head;
			}
			else {
				rootArray[head] = tail;
			}
		}
	}
	
	public static int find(int givenVertex) {
		if(givenVertex == rootArray[givenVertex]) {
			return givenVertex;
		}
		else {
			return (rootArray[givenVertex] = find(rootArray[givenVertex]));
		}
	}
		
	public static class Edge implements Comparable<Edge>{
		// private instance variables
		private int _tailVertex;
		private int _headVertex;
		private int _cost;
		
		// Getter & Setter
		public int tailVertex() {
			return this._tailVertex;
		}
		private void setTailVertex(int newTailVertex) {
			this._tailVertex = newTailVertex;
		}
		public int headVertex() {
			return this._headVertex;
		}
		private void setHeadVertex(int newHeadVertex) {
			this._headVertex = newHeadVertex;
		}
		public int cost() {
			return this._cost;
		}
		private void setCost(int newCost) {
			this._cost = newCost;
		}
		
		
		// Constructor
		public Edge(int givenTailVertex, int givenHeadVertex, int givenCost) {
			this.setTailVertex(givenTailVertex);
			this.setHeadVertex(givenHeadVertex);
			this.setCost(givenCost);
			/*
			 * this._headVertex = givenHeadVertex;
			this._tailVertex = givenTailVertex;
			this._cost = givenCost;
			*/
		}

		@Override
		public int compareTo(Edge anEdge) {
			if(this._cost >anEdge._cost) {
				return 1;
			}
			else if(this._cost < anEdge._cost) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
	
}

