import java.util.ArrayList;

/**
 * Node class for the project
 * @author Xiang Cui
 *
 */
public class Node {

	private String state;
	private ArrayList<Node> children;
	private Node parent;
	private int depth, estimatedCost, totalCost,Cost;
	
	private boolean visited;

	//getter and setter for state
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	//getter and setter for children
	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}

	//getter and setter for parent
	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	//getter and setter for depth
	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	//getter and setter for estimated cost
	public int getEstimatedCost() {
		return estimatedCost;
	}

	public void setEstimatedCost(int estimatedCost) {
		this.estimatedCost = estimatedCost;
	}

	//getter and setter for total cost
	public int getTotalCost() {
		return totalCost;
	}
	// setter for total cost with two paras
    public void setTotalCost(int cost, int estimatedCost) {
        this.totalCost = cost + estimatedCost;
    }

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}

	//getter and setter for cost
	public int getCost() {
		return Cost;
	}

	public void setCost(int cost) {
		Cost = cost;
	}

	//getter and setter for visited
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	//constructor for 1 parameter--state
	public Node(String state){
		this.state=state;
		children = new ArrayList<Node>();
	}
	
	public void addChildren(Node newChildren){
		children.add(newChildren);
	}
}
