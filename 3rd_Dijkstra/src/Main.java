import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private static Scanner scanner = new Scanner(System.in);
	private static final int DEFAULT_VALUE = 100000001;
	private static int[] distance;
	private static ArrayList<Edge>[] adjacency;
	
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		// input number
		int numberOfVertices = scanner.nextInt();
		int numberOfEdges = scanner.nextInt();	
		
		// 리스트 초기화
		distance = new int[numberOfVertices+1]; // 거리를 나타내는 배열
		Arrays.fill(distance, DEFAULT_VALUE);
		
		adjacency = new ArrayList[numberOfVertices+1];
		for(int i=1; i<numberOfVertices+1; i++) {
			adjacency[i] = new ArrayList<>();
		}
		// edge 입력
		for(int i=0; i<numberOfEdges; i++) {
			int inputTailVertex = scanner.nextInt();
			int inputHeadVertex = scanner.nextInt();
			int inputCost = scanner.nextInt();
			
			adjacency[inputTailVertex].add(new Edge(inputHeadVertex, inputCost));
		}
		
		int startVertex = scanner.nextInt();
		int endVertex = scanner.nextInt();	
		
		System.out.println(djikstra(startVertex, endVertex));
		
	}
	
	private static int djikstra(int givenStartVertex, int givenEndVertex) {
		PriorityQueue<Edge> edgeQueue = new PriorityQueue<Edge>();
		edgeQueue.offer(new Edge(givenStartVertex, 0));
		distance[givenStartVertex]= 0;
		
		while(!edgeQueue.isEmpty()) {
			Edge removedEdge = edgeQueue.poll();
			
			// distance[목적지] 값이 cost 값을 넣을 때의 경로보다 더 작은 경우
			if(distance[removedEdge.headVertex()] < removedEdge.cost() ) {
				// 어떠한 작업도 하지 않는다.
				continue;
			}
			else {
				// distance[목적지] 값이 cost 값을 넣을 때의 경로보다 더 큰 경우
				// 모두 scan
				for(int i=0; i<adjacency[removedEdge.headVertex()].size(); i++) {
					Edge temp = adjacency[removedEdge.headVertex()].get(i);
					
					// renew
					if(distance[temp.headVertex()] > distance[removedEdge.headVertex()] + temp.cost()) {
						distance[temp.headVertex()] = distance[removedEdge.headVertex()] + temp.cost()	;
						edgeQueue.offer(new Edge(temp.headVertex(), temp.cost()));
					}
				}
			}
		}
		return distance[givenEndVertex];
	}
	
	public static class Edge implements Comparable<Edge>{
		// Private instance variables
//		private int _tailVertex;
		private int _headVertex;
		private int _cost;
		
		// Getter & Setter
/*		public int tailVertex() {
			return this._tailVertex;
		}
		private void setTailVertex(int newTailVertex) {
			this._tailVertex = newTailVertex;
		}
*/		public int headVertex() {
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
		
		public Edge(int givenHeadVertex, int givenCost) {
//			this.setTailVertex(givenTailVertex);
			this.setHeadVertex(givenHeadVertex);
			this.setCost(givenCost);
		}
		
		@Override
		public int compareTo(Edge anEdge) {
			if(this.cost() > anEdge.cost()) {
				return +1;
			}
			else if(this.cost() < anEdge.cost()) {
				return -1;
			}
			else {
				return 0;
			}
		}
	}
}
