import java.util.Queue;
import java.util.LinkedList;

public class AppController {

	// private instance variables
	private Graph _graph;
	private boolean[] _visited;
	
	// Getter & Setter
	private Graph graph() {
		return this._graph;
	}
	private void setGraph(Graph newGraph){
		this._graph = newGraph;
	}
	private boolean[] visited() {
		return this._visited;
	}
	private void setVisited(boolean[] newVisited) {
		this._visited = newVisited;
	}
	
	// Constructor
	public AppController() {
		this.setGraph(null);
		this.setVisited(null);
	}
	
	//private methods
	private int inputNumberOfVertices() {
			while(true){
				try {
					int numberOfVertices = AppView.inputInt();
					if( (numberOfVertices<1) && (numberOfVertices>1000) ) {
						AppView.output("[����] Vertex ���� 0�� 1000 �����̾�� �մϴ�.");
					}
					return numberOfVertices;
				}
				catch(NumberFormatException e) {
					AppView.outputLine("[����] �ùٸ� Vertex ���� �Էµ��� �ʾҽ��ϴ�.");
				}
			}
	}
	private int inputNumberOfEdges() {		
		while(true) {
			try {
				int numberOfEdges= AppView.inputInt();
				if(numberOfEdges<0 && numberOfEdges>10000) {
					AppView.output("[����] Edge ���� 0�� 10000 �����̾�� �մϴ�.");
				}
				return numberOfEdges;
			}
			catch(NumberFormatException e) {
				AppView.outputLine("[����] �ùٸ� Edge ���� �Էµ��� �ʾҽ��ϴ�.");
			}
		}
	}
	private int inputStartingPoint() {		
		while(true) {
			try {
				int startingPoint= AppView.inputInt();
				return startingPoint;
			}
			catch(NumberFormatException e) {
				AppView.outputLine("[����] �ùٸ� �������� �Էµ��� �ʾҽ��ϴ�.");
			}
		}
	}
	
	private Edge inputEdge() {
		do {
			int tailVertex = AppView.inputInt()-1;
			int headVertex = AppView.inputInt()-1;
			if(this.graph().vertexIsValid(tailVertex) && this.graph().vertexIsValid(headVertex)) {
				if(tailVertex == headVertex) {
					AppView.outputLine("[����] �� Vertex ��ȣ�� �����մϴ�.");
				}
				else {
					return (new Edge(tailVertex, headVertex));
				}
			}
			else {
				if(! this.graph().vertexIsValid(tailVertex)) {
					AppView.outputLine("[����] �������� �ʴ� tail Vertex �Դϴ�: " + tailVertex);
				}
				if(! this.graph().vertexIsValid(headVertex)) {
					AppView.outputLine("[����] �������� �ʴ� head Vertex �Դϴ�: " + headVertex);
				}
			}
		}while(true);
	}

	
	private void inputAndMakeGraph() {
		// �ʱⰪ ����
		int numberOfVertices = this.inputNumberOfVertices();
		int numberOfEdges = this.inputNumberOfEdges();
		int startingPoint = this.inputStartingPoint();
		if((startingPoint<=0) || (startingPoint>numberOfVertices)) {
			numberOfVertices = this.inputNumberOfVertices();
			numberOfEdges = this.inputNumberOfEdges();
			startingPoint = this.inputStartingPoint();
		}

		this.setGraph(new Graph(numberOfVertices, numberOfEdges, startingPoint));
		
		int count = 0;
		while(count<this.graph().numberOfEdges()) {
			if(this.graph().addEdge(this.inputEdge())) {
				count++;
			}
		}				
	}
	
	private void recursiveDFS(int givenStartingPoint) {
		this.visited()[givenStartingPoint] = true; // Ž���� vertex üũ
		AppView.output(givenStartingPoint+1 +" ");
		for (int i=0; i<this.graph().numberOfVertices(); i++){ 
			if( (this.graph().adjacency()[givenStartingPoint][i] == 1) && (!this.visited()[i])) {  
				
				this.recursiveDFS(i);
			} 
		}
	}
	
	// DFS
	private void dfs(int givenStartingPoint) {
		// visited list initialization
		this.setVisited(new boolean[this.graph().numberOfVertices()]);
		for(int i=0; i<this.graph().numberOfVertices(); i++) {
			this.visited()[i] = false;
		}
		
		this.recursiveDFS(givenStartingPoint-1);
	}
	
	// BFS
	private void bfs(int givenStartingPoint) {
		// visited list initialization
		this.setVisited(new boolean[this.graph().numberOfVertices()]);
		for(int i=0; i<this.graph().numberOfVertices(); i++) {
			this.visited()[i] = false;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		
		this.visited()[givenStartingPoint-1] = true;
		queue.offer(givenStartingPoint-1);
		AppView.output(givenStartingPoint + " ");
		while(!queue.isEmpty()) {
			int removedFromQueue = queue.poll();
			for(int i=0; i<this.graph().numberOfVertices(); i++) {
				if( (this.graph().adjacency()[removedFromQueue][i]==1) && (!this.visited()[i]) ) {
					AppView.output(i+1 + " ");
					this.visited()[i] = true;
					queue.offer(i);
				}
			}
		}
	}
	
	public void run() {
		this.inputAndMakeGraph();
		
		/*
		for(int i=0; i<this.graph().numberOfVertices(); i++) {
			for(int j=0; j<this.graph().numberOfVertices(); j++) {
				AppView.output(this.graph().adjacency()[i][j]+" ");
			}
			AppView.outputLine("");
		}
		AppView.outputLine("");
		*/
		
		this.dfs(this.graph().startingPoint());
		AppView.outputLine("");
		
		this.bfs(this.graph().startingPoint());
		
	}
}
