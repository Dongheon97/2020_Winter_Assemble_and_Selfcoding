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
						AppView.output("[오류] Vertex 수는 0과 1000 사이이어야 합니다.");
					}
					return numberOfVertices;
				}
				catch(NumberFormatException e) {
					AppView.outputLine("[오류] 올바른 Vertex 수가 입력되지 않았습니다.");
				}
			}
	}
	private int inputNumberOfEdges() {		
		while(true) {
			try {
				int numberOfEdges= AppView.inputInt();
				if(numberOfEdges<0 && numberOfEdges>10000) {
					AppView.output("[오류] Edge 수는 0과 10000 사이이어야 합니다.");
				}
				return numberOfEdges;
			}
			catch(NumberFormatException e) {
				AppView.outputLine("[오류] 올바른 Edge 수가 입력되지 않았습니다.");
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
				AppView.outputLine("[오류] 올바른 시작점이 입력되지 않았습니다.");
			}
		}
	}
	
	private Edge inputEdge() {
		do {
			int tailVertex = AppView.inputInt()-1;
			int headVertex = AppView.inputInt()-1;
			if(this.graph().vertexIsValid(tailVertex) && this.graph().vertexIsValid(headVertex)) {
				if(tailVertex == headVertex) {
					AppView.outputLine("[오류] 두 Vertex 번호가 동일합니다.");
				}
				else {
					return (new Edge(tailVertex, headVertex));
				}
			}
			else {
				if(! this.graph().vertexIsValid(tailVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 tail Vertex 입니다: " + tailVertex);
				}
				if(! this.graph().vertexIsValid(headVertex)) {
					AppView.outputLine("[오류] 존재하지 않는 head Vertex 입니다: " + headVertex);
				}
			}
		}while(true);
	}

	
	private void inputAndMakeGraph() {
		// 초기값 설정
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
		this.visited()[givenStartingPoint] = true; // 탐색한 vertex 체크
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
