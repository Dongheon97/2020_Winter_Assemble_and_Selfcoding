
public class Edge {

	// Instance variables
	private int _tailVertex;
	private int _headVertex;
	
	// Public Getter & Setter
	public int tailVertex() {
		return this._tailVertex;
	}
	public void setTailVertex(int newTailVertex) {
		this._tailVertex = newTailVertex;
	}
	public int headVertex() {
		return this._headVertex;
	}
	public void setHeadVertex(int newHeadVertex) {
		this._headVertex = newHeadVertex;
	}	
	
	// Constructor
	public Edge(int givenTailVertex, int givenHeadVertex) {
		this.setTailVertex(givenTailVertex);
		this.setHeadVertex(givenHeadVertex);
	}
	
}
