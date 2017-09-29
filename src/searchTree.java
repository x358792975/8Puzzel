import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * searching class for different methods of searching
 * @author Xiang Cui
 *
 */
public class searchTree {
    private Node root;
    private String goalState;
    
    //getter & setter for root
	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	
	//getter & setter for goalState
	public String getGoalState() {
		return goalState;
	}
	public void setGoalSate(String goalState) {
		this.goalState = goalState;
	}
    
	//constructor with two parameters(root & goalState)
	public searchTree(Node root, String goalState){
		this.root = root;
		this.goalState = goalState;
	}
	
	public void DepthFirstSearch(){
		int cost = 0;
		int time = 0;
		//list for nodes that have been visited
		Set<String> state = new HashSet<String>();
		
		Node myNode = new Node(root.getState());
		
		//a queue for nodes that need to be expanded
		Queues<Node> myQueue = new Queues<>();
		
		//a queue for a expanded node's successors
		Queues<Node> succQueue = new Queues<>();
		
		Node current = myNode;
		
		// looping until the goal state is reached
		while(!current.getState().equals(goalState)){
			state.add(current.getState());
			List<String> nodeSucc = Utility.getSuccessors(current.getState());
			
			for(String s : nodeSucc){
				if(state.contains(s)){
					continue;
				}
				state.add(s);
				Node children = new Node(s);
				current.addChildren(children);
				children.setParent(current);
				succQueue.enqueue(children);
			}
			myQueue.add(succQueue);
			
			succQueue.clear();
			current = myQueue.dequeue();
			time++;
			nodeSucc.clear();
		
		}
		Utility.printSolution(current, state, root, time);
		
		}
}
