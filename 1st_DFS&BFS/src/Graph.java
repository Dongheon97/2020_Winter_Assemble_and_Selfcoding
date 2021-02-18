
public class Graph {
	private int _numberOfVertices;
	private int _numberOfEdges;
	private int _startingPoint;
	private int[][] _adjacency;
	
	// Getter & Setter
	public int startingPoint() {
		return this._startingPoint;
	}
	private void setStartingPoint(int newStartingPoint) {
		this._startingPoint = newStartingPoint;
	}
	public int numberOfVertices(){
		return this._numberOfVertices;
	}
	private void setNumberOfVertices(int newNumberOfVertices) {
		this._numberOfVertices = newNumberOfVertices;
	}
	public int numberOfEdges(){
		return this._numberOfEdges;
	}
	private void setNumberOfEdges(int newNumberOfEdges) {
		this._numberOfEdges = newNumberOfEdges;
	}
	public int[][] adjacency(){
		return this._adjacency;
	}
	private void setAdjacency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	
	
	// Constructor
	public Graph(int givenNumberOfVertices, int givenNumberOfEdges, int givenStartingPoint) {
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(givenNumberOfEdges);
		this.setStartingPoint(givenStartingPoint);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for(int tailVertex=0; tailVertex<givenNumberOfVertices; tailVertex++) {
			for(int headVertex=0; headVertex<givenNumberOfVertices; headVertex++) {
				this.adjacency()[tailVertex][headVertex]=0;
			}
		}
	}
	
	public boolean vertexIsValid(int givenVertex) {
		if(givenVertex<1 && givenVertex>this.numberOfVertices()) {
			return false;
		}
		return true;
	}
	
	/*public boolean addEdge(Edge givenEdge) {
		int tailVertex = givenEdge.tailVertex();
		int headVertex = givenEdge.headVertex();
		if(this.isValid(tailVertex) && this.isValid(headVertex)) {
			this.adjacency()[tailVertex][headVertex]=1;
			this.adjacency()[headVertex][tailVertex]=1;
			return true;
		}
		return false;
	}*/
	
	private boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacency()[tailVertex][headVertex] != 0);
	}
	
	public boolean edgeDoesExist(Edge anEdge) {
		if(anEdge != null) { // edge �Է��� ���� ��,
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			if(this.vertexIsValid(tailVertex) && this.vertexIsValid(headVertex)) {
				return(adjacencyOfEdgeDoesExist(tailVertex, headVertex));
			}
		}
		return false;
	}
	public boolean addEdge(Edge anEdge) { 
		//anEdge�� �����ϴ��� �˻�
		if(anEdge != null) { 
			int tailVertex = anEdge.tailVertex();
			int headVertex = anEdge.headVertex();
			//������ anEdge�� vertex���� ��ȿ���� �˻�
			if(this.vertexIsValid(tailVertex) && this.vertexIsValid(headVertex)) {
				//������ anEdge�� �̹� �����ϴ��� �˻�
				if(!this.adjacencyOfEdgeDoesExist(tailVertex, headVertex)) {
					//Undirected Graph�̱� ������ �ֹ����� edge �߰�
					this.adjacency()[tailVertex][headVertex] = 1;
					this.adjacency()[headVertex][tailVertex] = 1;
					return true;
				}
			}
		}
		return false;
	}
}
